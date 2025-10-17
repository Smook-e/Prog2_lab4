/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerProductDatabase extends DataBase {

    //Privatevariables 
    private ArrayList<CustomerProduct> records;

    //constructor
    public CustomerProductDatabase(String filename) {
        super(filename);
    }
@Override
   //Read from file
    public void readFromFile() {
        records.clear();//law 3amalna 2aktar men call fy el run el wa7d temsa7 el 2adim ely met5azen fy el arraylist
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();//te5azen line by line in variable string line
                if (!line.isEmpty()) {
                    CustomerProduct record = createRecordFrom(line);//abstarct method in database class w 3amlalha override ta7et dih 3ala tool//mafroud eni 3ayza 2a7awel el line leh object no3o customerproduct
                        records.add(record);//ba7ot el object dah fy el arraylist
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
@Override
   // create a record object from the line no3 el record custome product
//7845345678,P2568,12-02-2022,true.
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");// mafroud 3andy 4 items law 2a2al yeb2a fy 8alat fy el line m4 kamel
            if (parts.length < 4) return null;

            String customerSSN = parts[0];
            String productID = parts[1];
            LocalDate purchaseDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));//beya5od el date w ye5oto fy el format dih in case 2no m4 ma7tout
            boolean paid = Boolean.parseBoolean(parts[3]);

            return new CustomerProduct(customerID, productID, purchaseDate, paid);//benegama3 kol el data dih w ne3mel object w nerega3o
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    // return all records
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records; //refrence to the arraylist
    }
   @Override
public String getSearchKey() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return customerSSN+ "," + productID + "," + purchaseDate.format(formatter);
}
    // 4️⃣ Check if a record exists by key
    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {//access the 
                return true;
            }
        }
        return false;
    }
w


    // 5️⃣ Get a record by key
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {//
                return record;
            }
        }
        return null;
    }

    // 6️⃣ Insert a new record (if not exists)
    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Record already exists!");
        }
    }

    // 7️⃣ Delete a record by key
    public void deleteRecord(String key) {
        CustomerProduct toRemove = null;
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                toRemove = record;
                break;
            }
        }
        if (toRemove != null) {
            records.remove(toRemove);
            System.out.println("Record with key [" + key + "] deleted successfully.");
        } else {
            System.out.println("No record found with key [" + key + "].");
        }
    }

    // 8️⃣ Save all records to file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filename, true)) {
            for (CustomerProduct record : records) {
                writer.write(record.lineRepresentation() + "\n");
            }
            System.out.println("All records saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
}

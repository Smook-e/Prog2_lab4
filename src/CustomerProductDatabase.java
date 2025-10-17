package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class CustomerProductDatabase extends DataBase{
     private ArrayList<CustomerProduct> records;//records is an ArrayList that holds Record references,
//but each element is actually a real object — like a Product or CustomerProduct.
     
    public CustomerProductDatabase(String filename) {
        super(filename);
        this.records = new ArrayList<>();//

    }

@override
    // read from file
    public void readFromFile() {
        records.clear();//law 3amalna call 2aktar men mara fy nafs el run 2amsa7 kol el 2adim
        File file = new File(filename);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (!line.isEmpty()) {
                    CustomerProduct record = createRecordFrom(line);//abstract method in DataBase class w override ta7et dih betreturn object tyoe costumor product
                    if (record != null) {
                        records.add(record);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
@override
    //create a record from a line
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 4) return null;//lazem 4 2agza2 (data) 8eir keda el satr m4 kamel

            String customerSSN = parts[0];
            String productID = parts[1];
            LocalDate purchaseDate = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            boolean paid = Boolean.parseBoolean(parts[3]);

            return new CustomerProduct(customerSSN, productID, purchaseDate, paid);// bey3mel object gedid w ye7ot fih kol el data
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    // return all records
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;//refrence to the arraylist
    }
//a method in record interface
    @Override
public String getSearchKey() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
}

    
    // check if record exists by key
    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // get a record by key
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    // insert a new record if not exist
    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Record already exists!");
        }
    }

    // delete a record by key
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

    // save all records in file
    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filename, false)) {
            for (CustomerProduct record : records) {
                writer.write(record.lineRepresentation() + "\n");
            }
            System.out.println("All records saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
}


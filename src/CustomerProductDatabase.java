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
     private ArrayList<CustomerProduct> records;
     
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

@override
    // 1️⃣ Read from file
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
    // 2️⃣ Create a record from a line
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

    // 3️⃣ Return all records
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;//refrence to the arraylist
    }

    @Override
public String getSearchKey() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
}

    
    // 4️⃣ Check if a record exists by key
    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // 5️⃣ Get a record by key
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
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


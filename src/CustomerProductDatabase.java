import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CustomerProductDatabase extends DataBase {
  //no needed to filename or arraylist already in parent class 
    public CustomerProductDatabase(String filename) {
        super(filename);            
    }
<<<<<<< HEAD
    // Read from file and fill the list
    //no need in parent class
//customerproductdatabase object.readfromfile()
    // 🔹 2. Create a CustomerProduct object from one line
=======

    //  Read from file and fill the list
    @Override
    public void readFromFile() {
        records.clear();
        File file = new File(filename);

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (!line.isEmpty()) {
                    CustomerProduct record = createRecordFrom(line);
                    if (record != null) {
                        records.add(record);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    // Create a CustomerProduct object from one line
>>>>>>> 5017a35ad758ff065d7ba60cf924376300d86f44
    @Override
    public Record createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 4) return null;
            String customerSSN = parts[0];
            String productID = parts[1];
            LocalDate date = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            boolean paid = Boolean.parseBoolean(parts[3]);
            return new CustomerProduct(customerSSN, productID, date, paid);
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

<<<<<<< HEAD
=======
    // Return all CustomerProduct records
    public ArrayList<CustomerProduct> returnAllRecords() {
        return records;
    }

    //  Check if a record exists by key
    public boolean contains(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    // . Get a record by key
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    //. Insert a record
    public void insertRecord(CustomerProduct record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Record already exists!");
        }
    }

    //  Delete a record by key
    public void deleteRecord(String key) {
        CustomerProduct toRemove = getRecord(key);
        if (toRemove != null) {
            records.remove(toRemove);
        } else {
            System.out.println("No record found with key: " + key);
        }
    }

    // Save all records to file
    @Override
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
>>>>>>> 5017a35ad758ff065d7ba60cf924376300d86f44
}

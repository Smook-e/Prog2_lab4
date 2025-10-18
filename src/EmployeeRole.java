import java.time.format.DateTimeFormatter;


public class EmployeeRole {
    private productsDatabase productsDatabase;
    private CustomerProductDatabase CustomerProductDatabase;
    public EmployeeRole() {
    productsDatabase = new ProductDatabase("Products.txt");//public constructor in productdatabase class
    customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
}

public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
  Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity);
        //public constructor in product class
        // Check if product already exists
        if (!productsDatabase.contains(productID)) {
            productsDatabase.insertRecord(newProduct);
            productsDatabase.saveToFile();
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Product already exists.");
        }
    }

//Returns an array that contains all the products stored in the file named Products.txt
//read and store in arraylist of product
@Override
public void readFromFile() {
    records.clear();
    File file = new File(filename);

    try (Scanner input = new Scanner(file)) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (!line.isEmpty()) {
                Product product = (Product) createRecordFrom(line);
                if (product != null) {
                    records.add(product);
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filename);
    }
}
 // 
    public Product[] getListOfProducts() {
        //"I make an object named productsDatabase that contains the file name, and I use it here to read this file."
        productsDatabase.readFromFile();//ely fo2
        //call it,it reads the file and fills that internal ArrayList(records) with Product objects
        ArrayList<Product> allProducts = productsDatabase.returnAllRecords();//return refrence 3ala el arraylist(records)
        Product[] productArray = new Product[allProducts.size()];//beye3mel array no3a product el size nafes beta3 el arraylist
        return allProducts.toArray(productArray);
    }
  //customerproduct  
 @Override
public void readFromFile() {
    records.clear();
    File file = new File(filename);

    try (Scanner input = new Scanner(file)) {
        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (!line.isEmpty()) {
                CustomerProduct record = (CustomerProduct) createRecordFrom(line);
                if (record != null) {
                    records.add(record);
                }
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filename);
    }
}   
  //get the array  
public CustomerProduct[] getListOfPurchasingOperations() {
    customerProductDatabase.readFromFile();
    ArrayList<CustomerProduct> allPurchases = customerProductDatabase.returnAllRecords();//its arraylist is private 
    //i access it with this method
    CustomerProduct[] purchasesArray = new CustomerProduct[allPurchases.size()];
    return allPurchases.toArray(purchasesArray);
}
    
 //purchase action
public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
    // load data
    productsDatabase.readFromFile();
    customerProductDatabase.readFromFile();
    // find the product
    Product product = (Product) productsDatabase.getRecord(productID);//from the para.
//it returns a reference to one of the objects inside the records list 
//the compiler only knows that this returns a Record, because that’s the return type in the parent class.
//the records list in this subclass contains Product objects 
    // check if exists
    if (product == null) {
        System.out.println("Product not found.");
        return false;
    }
    if (product.getQuantity() == 0) {
        System.out.println("Product out of stock.");
        return false;
    }
    product.setQuantity(product.getQuantity() - 1);//that change is auto. in the arraylist call by refrence
    // new purchase recorde
    CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate, true);

    // put this record in the arraylist of the class
    customerProductDatabase.insertRecord(newPurchase);

    productsDatabase.saveToFile();
    customerProductDatabase.saveToFile();

    System.out.println("Purchase successful!");
    return true;
}
//refund
public double returnProduct(String customerSSN, String productID,LocalDate purchaseDate, LocalDate returnDate) {
    productsDatabase.readFromFile();
    customerProductDatabase.readFromFile();

    //Check if returnDate < purchaseDate
    if (returnDate.isBefore(purchaseDate)) {
        System.out.println("Return date is earlier than purchase date.");
        return -1;
    }
    Product product = (Product) productsDatabase.getRecord(productID);
    if (product == null) {
        System.out.println("Product not found in Products.txt.");
        return -1;
    }
        @Override
public String getSearchKey() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    return customerSSN + "," + productID + "," + purchaseDate.format(formatter);
}

    CustomerProduct purchase = (CustomerProduct) customerProductDatabase.getRecord(key);
    if (purchase == null) {
        System.out.println("No matching purchase record found.");
        return -1;
    }

    long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(purchaseDate, returnDate);
    if (daysBetween > 14) {
        System.out.println("Return period expired. More than 14 days have passed.");
        return -1;
    }
     //everything valid
    product.setQuantity(product.getQuantity() + 1);

    // remove the record
    customerProductDatabase.deleteRecord(key);
    productsDatabase.saveToFile();
    customerProductDatabase.saveToFile();

    System.out.println("Return successful. Refund = " + product.getPrice());
    return product.getPrice();
}


public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
    customerProductDatabase.readFromFile();

    // array of customer product ta5od el refrence ely fy el object arraylist
    ArrayList<CustomerProduct> allRecords = customerProductDatabase.returnAllRecords();//public method in class customerproduct return objects
    for (CustomerProduct record : allRecords) {
        // Check for same customer SSN and same date
        if (record.getCustomerID().equals(customerSSN) &&
            record.getPurchaseDate().equals(purchaseDate)) {

            if (record.isPaid()) {
                System.out.println("This purchase is already marked as paid.");
                return false;
            }

            record.setPaid(true);

            customerProductDatabase.saveToFile();

            System.out.println("Payment applied successfully for customer: " + customerSSN);
            return true;
        }
    }
    System.out.println("No matching unpaid purchase found for this customer and date.");
    return false;
}




public void logout(){
productsDatabase.saveToFile();
    customerProductDatabase.saveToFile();
        }
}




    

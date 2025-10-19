import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    public EmployeeRole() {
        //public methods in other classes
        productsDatabase = new ProductDatabase("Files\\Products.txt");
        customerProductDatabase = new CustomerProductDatabase("Files\\CustomerProducts.txt");
    }

    //  new product
    public void addProduct(String productID, String productName, String manufacturerName,String supplierName, int quantity , float price) {

        //tries to insert a record and returns true if inserted and false if already exists
        if (productsDatabase.insertRecord(new Product(productID, productName, manufacturerName, supplierName, quantity, price))) {

            System.out.println("Product added successfully!");
        }
    }


    public Product[] getListOfProducts() {
        return productsDatabase.returnAllRecords().toArray(new Product[0]);
    }


    //  Return all purchasing operations as an array
    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    //  Purchase a product
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product product = (Product) productsDatabase.getRecord(productID);
        if(product == null) {
            System.out.println("Product not found!");
            return false;
        }
        if (product.getQuantity() == 0) {
            System.out.println("️ Product out of stock.");
            return false;
        }
        // Decrease quantity by 1
        product.setQuantity(product.getQuantity() - 1);

        // Create purchase record and add it
        customerProductDatabase.insertRecord(new CustomerProduct(customerSSN, productID, purchaseDate));



        System.out.println("Purchase successful!");
        return true;
    }

    // return
    public double returnProduct(String customerSSN, String productID,LocalDate purchaseDate, LocalDate returnDate) {


        // Invalid dates
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println(" Return date is earlier than purchase date.");
            return -1;
        }

        Product product = (Product) productsDatabase.getRecord(productID);
        if (product == null) {
            System.out.println(" Product not found!");
            return -1;
        }

        // Build key for purchase record
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String key = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        CustomerProduct purchase = (CustomerProduct) customerProductDatabase.getRecord(key);
        if (purchase == null) {
            System.out.println(" No matching purchase record found.");
            return -1;
        }

        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            System.out.println("️ Return period expired (more than 14 days).");
            return -1;
        }

        // Valid return
        product.setQuantity(product.getQuantity() + 1);

        customerProductDatabase.deleteRecord(key);




        System.out.println(" Return successful. Refund = " + product.getPrice());
        return product.getPrice();

    }

    
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<Record> allRecords = customerProductDatabase.returnAllRecords();

        for (Record r : allRecords) {

                CustomerProduct record = (CustomerProduct) r;
                if (record.getCustomerSSN().equals(customerSSN)
                        && record.getPurchaseDate().equals(purchaseDate)) {

                    if (record.isPaid()) {
                        System.out.println("This purchase is already marked as paid.");
                        return false;
                    }

                    record.setPaid(true);

                    System.out.println("Payment applied successfully for " + customerSSN);
                    return true;
                }



        }

        System.out.println("️ No matching unpaid purchase found for this customer and date.");
        return false;
    }

 
    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        System.out.println("All data saved successfully. Logged out.");
    }
}
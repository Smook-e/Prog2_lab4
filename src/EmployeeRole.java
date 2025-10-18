import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;
    public EmployeeRole() {
        //public methods in other classes
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    //  new product
    public void addProduct(String productID, String productName, String manufacturerName,String supplierName, int quantity) {
        productsDatabase.readFromFile();
        Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
//net2aked eno m4 mawgoud fi el arraylist beta3t el object no3o productdatabase
        if (!productsDatabase.contains(productID)) {
            productsDatabase.insertRecord(newProduct);//pulic method in dtabase class
            productsDatabase.saveToFile();
            System.out.println("Product added successfully!");
        } else {
            System.out.println(" Product already exists.");
        }
    }


    public Product[] getListOfProducts() {
    productsDatabase.readFromFile();//hat2ra el filename ely gowa el poductdatabase object w ne7ot kol el 
    //lines fy arraylist 3ala hay2et ecords
    ArrayList<Product> allProducts = productsDatabase.returnAllRecords();
    Product[] productArray = new Product[allProducts.size()];
    return allProducts.toArray(productArray);
}


    // 🔹 5. Return all purchasing operations as an array
    public CustomerProduct[] getListOfPurchasingOperations() {
        customerProductDatabase.readFromFile();
        ArrayList<CustomerProduct> allPurchases = customerProductDatabase.returnAllRecords();
        CustomerProduct[] purchaseArray = new CustomerProduct[allPurchases.size()];
        return allPurchases.toArray(purchaseArray);
    }

    //  Purchase a product
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();

        Product product = (Product) productsDatabase.getRecord(productID);

        if (product == null) {
            System.out.println(" Product not found.");
            return false;
        }
        if (product.getQuantity() == 0) {
            System.out.println("️ Product out of stock.");
            return false;
        }

        // Decrease quantity by 1
        product.setQuantity(product.getQuantity() - 1);

        // Create purchase record and add it
        CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate, false);
        customerProductDatabase.insertRecord(newPurchase);

        // Save updates
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        System.out.println("Purchase successful!");
        return true;
    }

    // return
    public double returnProduct(String customerSSN, String productID,LocalDate purchaseDate, LocalDate returnDate) {

        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();

        // Invalid dates
        if (returnDate.isBefore(purchaseDate)) {
            System.out.println(" Return date is earlier than purchase date.");
            return -1;
        }

        Product product = (Product) productsDatabase.getRecord(productID);
        if (product == null) {
            System.out.println(" Product not found in Products.txt.");
            return -1;
        }

        // Build key for purchase record
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String key = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        CustomerProduct purchase = customerProductDatabase.getRecord(key);
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

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        System.out.println(" Return successful. Refund = " + product.getPrice());
        return product.getPrice();
    }

    
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        customerProductDatabase.readFromFile();
        ArrayList<CustomerProduct> allRecords = customerProductDatabase.returnAllRecords();

        for (CustomerProduct record : allRecords) {
            if (record.getCustomerSSN().equals(customerSSN)
                    && record.getPurchaseDate().equals(purchaseDate)) {

                if (record.isPaid()) {
                    System.out.println(" This purchase is already marked as paid.");
                    return false;
                }

                record.setPaid(true);
                customerProductDatabase.saveToFile();

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

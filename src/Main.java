import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EmployeeUserDatabase eDatabase = new EmployeeUserDatabase("Files\\Employees.txt");


//        LocalDate date = LocalDate.of(2020, 1, 1);
//        System.out.println(date);
//        EmployeeUser e = (EmployeeUser) eDatabase.createRecordFrom("E1210,Joe,Joe@gmail.com,Cairo,01138877345");
//        System.out.println(e.lineRepresentation());

        ProductDatabase pDatabase = new ProductDatabase("Files\\Products.txt");
//        Product p = new Product("t9762", "iPhone", "Apple", "TechSupplier",14,800);
//        pDatabase.insertRecord(p);
//        pDatabase.saveToFile();
//        System.out.println(pDatabase);
        CustomerProductDatabase cpDatabase = new CustomerProductDatabase("Files\\CustomerProducts.txt");
//        ArrayList<Record> r = cpDatabase.returnAllRecords();
//        for(Record r1 : r) {
//            System.out.println(r1.lineRepresentation());
//        }
//        CustomerProduct c = (CustomerProduct) cpDatabase.createRecordFrom("2315345678,p1233,10-11-2025");
//        System.out.println(c.lineRepresentation());

        EmployeeRole er = new EmployeeRole();
//        er.addProduct("p1233", "Computer", "Apple", "MacStore", 9, 1100);
//        Product[] products = er.getListOfProducts();
//        for(Product p : products){
//            System.out.println(p.lineRepresentation());;
//        }
//            CustomerProduct[] customerProducts = er.getListOfPurchasingOperations();
//            for(CustomerProduct p : customerProducts){
//                System.out.println(p.lineRepresentation());
//            }
//        er.purchaseProduct("1928736", "I9894", LocalDate.parse("19-10-2025", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//       er.returnProduct("1928736", "t1233", LocalDate.parse("19-10-2025" , formatter), LocalDate.parse("19-10-2025" , formatter) );
//        er.applyPayment("1928736", LocalDate.parse("19-10-2025", formatter));
    }
}
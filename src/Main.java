import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        EmployeeUserDatabase eDatabase = new EmployeeUserDatabase("Files\\Employees.txt");


        LocalDate date = LocalDate.of(2020, 1, 1);

        ProductDatabase pDatabase = new ProductDatabase("Files\\Products.txt");
        Product p = new Product("t9762", "iPhone", "Apple", "TechSupplier",14,800);
        pDatabase.insertRecord(p);
        pDatabase.saveToFile();
        System.out.println(pDatabase);

    }
}
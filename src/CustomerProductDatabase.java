
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomerProductDatabase extends DataBase {
  //no needed to filename or arraylist already in parent class 
    public CustomerProductDatabase(String filename) {
        super(filename);            
    }
    // Read from file and fill the list
    //no need in parent class
//customerproductdatabase object.readfromfile()
    // 🔹 2. Create a CustomerProduct object from one line
    @Override
    public Record createRecordFrom(String line){

//            System.out.println(line);
            String[] parts = line.split(",");
            if (parts.length > 4){
                for(String str : parts){
                    System.out.println(str);
                }
                return null;
            }
            String customerSSN = parts[0];
            String productID = parts[1];

            LocalDate date = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd'-'MM'-'yyyy"));
            CustomerProduct p = new CustomerProduct(customerSSN, productID, date);
            if(parts.length < 4){
                p.setPaid(false);
            }
            else{
                p.setPaid(Boolean.parseBoolean(parts[3]));
            }
            return p;
    }
}
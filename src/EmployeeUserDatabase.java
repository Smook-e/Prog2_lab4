import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeUserDatabase extends DataBase {

    public EmployeeUserDatabase(String filename) {
        //adds file name to parent Database
        super(filename);
    }



    @Override
    public Record createRecordFrom(String line) {
        String[] data = line.split(",");
        Record record;
        record = new EmployeeUser(data[0], data[1], data[2], data[3], data[4]);


        return record;
    }
}

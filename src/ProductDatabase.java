import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ProductDatabase extends DataBase{
    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            if(!records.isEmpty()) {
                records.clear();
            }
            while ((line = br.readLine()) != null) {
                Record r = createRecordFrom(line);
                if(r != null) {
                    records.add(r);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Record createRecordFrom(String line) {
        String[] data = line.split(",");
        Record record;
        record = new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]),Float.parseFloat(data[5]));
        return record;
    }
}

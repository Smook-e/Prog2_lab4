import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class DataBase {
    ArrayList<Record> records;
    String filename;
    public DataBase(String filename) {
        this.filename = filename;
    }
    public abstract void readFromFile();

    public abstract Record createRecordFrom(String line);
    public ArrayList<Record> returnAllRecords(){
        return records;
    }
    public boolean contains(String key){
        return getRecord(key) != null;
    }
    public Record getRecord(String key){
        for(Record r : records){
            if(r.getSearchKey().equals(key)){
                return r;
            }
        }
        return null;
    }
    public void insertRecord(Record record){
        if(!contains(record.getSearchKey())){
            records.add(record);
        }
        else {
            System.out.println("Record already exists");
        }
    }
    public void deleteRecord(String key){
        records.remove(getRecord(key));
    };
    public void saveToFile(){
        try(FileWriter file = new FileWriter(filename)) {
            for(Record record : records){
                file.write(record.toString() + "\n");

            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Error writing to file");
            throw new RuntimeException(e);
        }
    }

}

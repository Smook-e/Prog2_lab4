import java.io.*;
import java.util.ArrayList;

public abstract class DataBase {
    protected ArrayList<Record> records = new ArrayList<>();
    protected String filename;

    public DataBase(String filename) {
        this.filename = filename;
        this.readFromFile();
    }
    public void readFromFile(){
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
        if(contains(key)){
            records.remove(getRecord(key));
        }
        else  {
            System.out.println("Record does not exist");
        }

    };
    public void saveToFile(){
        try(FileWriter file = new FileWriter(filename)) {
            for(Record record : records){
                file.write(record.lineRepresentation() + "\n");

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

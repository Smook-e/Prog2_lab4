public class CustomerProductDatabase extends DataBase{
    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {

    }

    @Override
    public Record createRecordFrom(String line) {
        return null;
    }
}

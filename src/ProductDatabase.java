public class ProductDatabase extends DataBase{
    public ProductDatabase(String filename) {
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

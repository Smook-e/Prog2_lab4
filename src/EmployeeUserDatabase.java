public class EmployeeUserDatabase extends DataBase {

    public EmployeeUserDatabase(String filename) {
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

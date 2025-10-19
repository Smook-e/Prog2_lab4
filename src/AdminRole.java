public class AdminRole {
    private EmployeeUserDatabase database;
    public AdminRole()
    {
       database = new EmployeeUserDatabase("Files\\Employees.txt"); 

    }
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        database.insertRecord( new EmployeeUser(employeeId,name,email,address,phoneNumber));
        logout();  
    }
    public Record[] getListOfEmployees()
    {
        return database.returnAllRecords().toArray(new Record[0]);
    }
    public void removeEmployee(String Key)
    {
       database.deleteRecord(Key);
       logout();
    }
    public void logout()
    {
        database.saveToFile();
    }
}

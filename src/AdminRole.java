public class AdminRole {
    private EmployeeUserDatabase database;
    public AdminRole()
    {
       database = new EmployeeUserDatabase("Employees.txt"); 
       database.readFromFile();
    }
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        if(database.contains(employeeId))
        {
            System.out.print("\nEmployee already saved.");
            return;
        }
        EmployeeUser employee=new EmployeeUser(employeeId,name,email,address,phoneNumber);
        database.insertRecord(employee);
        logout();  
    }
    public EmployeeUser[] getListOfEmployees()
    {
        int x=database.returnAllRecords().size();
        EmployeeUser[] employees = new EmployeeUser[x];
        database.returnAllRecords().toArray(employees);
        return employees;
    }
    public void removeEmployee(String Key)
    {  
        if(!database.contains(Key))
        {
            System.out.print("\nEmployee id doesn't exist.");
            return;
        }
       database.deleteRecord(Key);
       logout();
    }
    public void logout()
    {
        database.saveToFile();
    }
}

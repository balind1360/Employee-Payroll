public class Employee {
    String name;
    String employeeID;
    String birthday;
    String workExperience;
    boolean hourly;
    double salary;

    public Employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
    }

    public Employee (){
        name = "";
        employeeID = "";
        birthday = "";
        workExperience = "";
        hourly = True;
        salary = 0.0;
    }

    public String toString (){
        return name;
    }

    public static Employee get_file(String id){
        Employee e = new Employee();

        return e;
    }
}

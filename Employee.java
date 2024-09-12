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

    public String toString (){
        return name;
    }
}

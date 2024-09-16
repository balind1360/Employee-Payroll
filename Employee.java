public class Employee {
    String name;
    String employeeID;
    String birthday;
    String workExperience;
    boolean hourly;
    double salary;
    boolean clockedIn;
    //hoursWorked

    public employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary, boolean clockedIn){
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

    public String getEmployeeID (){
        return employeeID;
    }

    public void setEmployeeID (String newID){
        employeeID = newID;
    }

    public String getBirthday (){
        return birthday;
    }

    public void setBrithday (String newBirthday){
        birthday = newBirthday;
    }

    public String getWorkExperience (){
        return workExperience;
    }

    public void editWorkExperience (String newWorkExperience){
        workExperience = newWorkExperience;
    }

    public boolean getHourly (){
        return hourly;
    }

    public void setHourly (boolean newHourly){
        hourly = newHourly;
    }

    public double getSalary (){
        return salary;
    }

    public void setSalary (double newSalary){
        salary = newSalary;
    }

    public boolean getClockedIn (){
        return clockedIn;
    }

    public void setClockedIn (boolean newClockedIn){\
        clockedIn = newClockedIn;
    }


    public double calculatePay (){
        
    }
}

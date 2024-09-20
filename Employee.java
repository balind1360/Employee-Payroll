import java.time.LocalDate; //import class to get current date
import java.util.ArrayList;

public class Employee {
    //stores information about the employee
    String name;
    String employeeID;
    String birthday;
    String workExperience;
    boolean hourly;
    double salary;
    boolean clockedIn;
    double hoursWorked;
    ArrayList <String> hoursAndDays;
    LocalDate day;
    Double time;
    Timer t;

    public Employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
        hoursWorked = 0.0; //start hours at 0
        hoursAndDays = new ArrayList <String> (); //add to this list to log the days and hours worked
        clockedIn = false;
        time = 0.0;
    }
    //prints name
    public String toString (){
        return name;
    }
    //setter for name
    public void setName (String newName){
        name = newName;
    }
    //getter for name 
    public String getName(){
    	return name;
    }
    //getter for employee ID
    public String getEmployeeID (){
        return employeeID;
    }
    //setter for employee ID
    public void setEmployeeID (String newID){
        employeeID = newID;
    }
    //getter birthday
    public String getBirthday (){
        return birthday;
    }
    //setter birthday
    public void setBrithday (String newBirthday){
        birthday = newBirthday;
    }
    //getter work experience
    public String getWorkExperience (){
        return workExperience;
    }
    //setter work experience
    public void editWorkExperience (String newWorkExperience){
        workExperience = newWorkExperience;
    }
    //getter hourly or not
    public boolean getHourly (){
        return hourly;
    }
    //setter hourly or not
    public void setHourly (boolean newHourly){
        hourly = newHourly;
    }
    //getter salary
    public double getSalary (){
        return salary;
    }
    //setter salary
    public void setSalary (double newSalary){
        salary = newSalary;
    }
    //getter clocked in
    public boolean getClockedIn (){
        return clockedIn;
    }
    //setter clocked in
    public void setClockedIn (boolean newClockedIn){
        clockedIn = newClockedIn;
    }
    //getter list of hours and days
    public ArrayList <String> getHoursAndDays (){
        return hoursAndDays;
    }

    //clocks in emplyee
    public boolean clockIn (){
        if (!clockedIn){
            t = new Timer ();
            time = 0.0;
            t.startClock();
            day = LocalDate.now (); //gets current date for payroll
            return true;
        }
        return false;
    }

    //clocks out employee and calculates hours worked
    public boolean clockOut (){
        if (clockedIn){
            t.stopClock ();
            time += t.timeElapsed();
            hoursWorked += time;
            hoursAndDays.add(day + " " + time); //adds date and hours worked for the payroll
            return true;
        }
        return false;
    }
    //puts ours and days worked back to zero
    public void resetPayPeriod (){
        hoursWorked = 0.0;
        hoursAndDays.clear ();
    }

    //calculates the pay for the employee (not including overtime or deductions)
    public double calculatePay (){
        double pay = 0.0;
        //pay without overtime for hourly workers
        if (hourly){
            pay = hoursWorked * salary;
        }
        //pay for salary employees
        if (!hourly){
            pay = hoursAndDays.size() * salary;
        }
        return pay;
    }
}

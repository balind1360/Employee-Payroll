import java.time.LocalDate; //import class to get current date

public class Employee {
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

    public employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary, boolean clockedIn){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
        hoursWorked = 0.0;
        hoursAndDays = new ArrayList <String> ();
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

    public void setClockedIn (boolean newClockedIn){
        clockedIn = newClockedIn;
    }

    public ArrayList <String> getHoursAndDays (){
        return hoursAndDays;
    }

    //clocks in emplyee
    public boolean clockIn (){
        if (!clockedIn){
            Timer t = new Timer ();
            double time = 0.0;
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
            time = t.timeElapsed();
            hoursWorked += time;
            hoursAndDays.add(day + time + ""); //adds date and hours worked for the payroll
            return true;
        }
        return false;
    }
    //puts ours and days worked back to zero
    public void resetPayPeriod (){
        hoursWorked = 0.0;
        hoursAndDays.clear ():
    }


    public double calculatePay (){
        double pay = 0.0;
        //pay without overtime for hourly workers
        if (hourly){
            pay = hoursWorked * salary;
        }
        //pay for salary employees
        if (salary){
            pay = hoursAndDay.size() * salary;
        }
        return pay;
    }
}

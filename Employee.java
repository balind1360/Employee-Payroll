import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Name: Employee                               Last Updated: 11/18/24
 * 
 * Creator: D. Balin, E. Blocke, J. Heintz
 * 
 * Purpose: Class to make Employee objects that have all of the necessary information of the employee 
 *  and have the ability to calculate payroll, check in and out, etc
 */


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
    Double time;
    Timer t;

    // constructor that takes in Employee's name (String newName), id (String newEmployeeID), birthday (newBirthday), work experience (String newWorkExperience), hourly wage (boolean newHourly), and salary (newSalary)
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
        day = null;
        this.save();
    }

    // uses the (assumed to already be created) text file by the name of (String id).txt to get an Employee object with all the saved information
    public Employee (String id){
        FileReader fr = null;
        Scanner in = null;
        
        try {
            fr = new FileReader("/Users/danabalin/Documents/AT2-Employee-Payroll/" + id + ".txt");
            in = new Scanner (fr);
        } catch (IOException e){
            return;
        }

        String line = null;
        Dictionary<String,String> values = new Hashtable<String,String>();
        while(in.hasNext()){
            line = in.nextLine();
            String[] words = line.split(": ");
            values.put(words[0], words[1]);
        }

        name = values.get("name");
        employeeID = id;
        birthday = values.get("birthday");
        workExperience = values.get("work_experience");
        hourly = Boolean.valueOf(values.get("hourly"));
        salary = Double.valueOf(values.get("salary"));
        hoursWorked = Double.valueOf(values.get("hours_worked"));
        hoursAndDays = ArrayListParser.parseArrayListString(values.get("hours_and_days"));
    
        try{
            fr.close();
            in.close();
        } catch(IOException e){
            System.out.println("Error: could not close file reader");
        }
    }


    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for employeeID
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    // Getter and Setter for birthday
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    // Getter and Setter for workExperience
    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    // Getter and Setter for hourly
    public boolean isHourly() {
        return hourly;
    }

    public void setHourly(boolean hourly) {
        this.hourly = hourly;
    }

    // Getter and Setter for salary
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // A toString returning the employee's name
    public String toString (){
        return name;
    }

    // saves all of the Employee information into a txt file by the name of the the employee's ID
    public void save(){
        FileWriter fw = null;
        PrintWriter out = null;

        try{
            fw = new FileWriter("/Users/danabalin/Documents/AT2-Employee-Payroll/" + employeeID + ".txt");
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }

        out.println("name: " + name);
        out.println("birthday: " + birthday);
        out.println("work_experience: " + workExperience);
        out.println("hourly: " + hourly);
        out.println("salary: " + salary);
        out.println("hours_worked: " + hoursWorked);
        out.println("hours_and_days: " + hoursAndDays);

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing files " + e);
        }
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

    //puts hours and days worked back to zero
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

    // returns an ArrayList<LocalDate> representing every day that will experience a 1.5 times increase in pay for working past 9 days in a row
    public ArrayList<LocalDate> checkNine(ArrayList<LocalDate> holidays){
        
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();

        
        for (String dateTime : hoursAndDays) {
            String datePart = dateTime.split(" ")[0];
            LocalDate date = LocalDate.parse(datePart);
            dates.add(date);
        }
        
        int count = 0;
        LocalDate curr;
        boolean restart;

        ArrayList<LocalDate> bonusDays = new ArrayList<LocalDate>();

        // starts at first entry
        curr = dates.get(0);
        restart = false;
        count = 1;

        while(!restart){
            if(count >= 9){
                bonusDays.add(curr);
            }

            if(!dates.contains(curr.plusDays(1)) && !holidays.contains(curr.plusDays(1))){
                restart = false;
                count = 1;
            }else{
                curr = curr.plusDays(1);
                count = count + 1;
            }
            
        }

        return bonusDays;
        
    }

    
    

}


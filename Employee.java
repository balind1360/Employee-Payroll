import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Name: Employee                               Last Updated: 11/18/24
 * 
 * Creator: D. Balin, E. Block, J. Heintz
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
    //finds all the possible start and end dates for a 7 day period
    public ArrayList<startsAndEnds> startAndEnds (){
        
    }

    //finds all the start and end dates of the 7 day periods
    public ArrayList<String> startsAndEnds (LocalDate startDate, ArrayList <LocalDate> holidays){
        //arraylist stores the start/end of periods
        ArrayList <String> startsAndEnds = new ArrayList <String> ();
        LocalDate end = LocalDate.now ();
        int val = 0; //tracks the number of days passed
        LocalDate start = startDate;
        LocalDate current = startDate;
        //goes through until you reach current day
        while (!current.equals (end)){
            //goes through until reaching 7 days or current day
            while (val < 7 && !current.equals (end)){
                System.out.println (current);
                if (!holidays.contains (current)){
                    val++;
                }
                System.out.println ("value: " + val);
                current = current.plusDays (1);
            }
            //once it reaches a seve day period, that period is added to the arraylist
            if (val >= 7){
                startsAndEnds.add (start + " " + current.minusDays(1)); //adds values to the arraylist
            }
            //goes until the next non-holiday and sets new start date
            while (holidays.contains(current)){
                current = current.plusDays(1);
            }
            start = current;
            val = 0;
        }
        return startsAndEnds;
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
        startsAndEnds ();
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
        if(checkNine()){
            pay = pay*1.5;
        }
        if(checkForty()){
            pay = pay*1.5;
        }
        return pay;
    }

    public boolean checkNine(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Store the LocalDates only
        ArrayList<LocalDate> dates = new ArrayList<>();
        
        for (String dateTime : hoursAndDays) {
            String datePart = dateTime.split(" ")[0]; // Get the LocalDate part before the space (ignoring hours)
            LocalDate date = LocalDate.parse(datePart, formatter);
            dates.add(date);
        }
        
        // Sort the dates in chronological order
        Collections.sort(dates);
        
        // Check for nine consecutive days
        int streak = 1;
        for (int i = 1; i < dates.size(); i++) {
            
            if (dates.get(i).equals(dates.get(i - 1).plusDays(1))) {
                streak++;
            } else {
                streak = 1;
            }

            if (streak == 9) {
                return true;
            }
        }
        
        // If no streak of 9 days is found, return false
        return false;
    }

    public boolean checkForty(){
        // Formatter for parsing LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Store LocalDate and hours worked
        ArrayList<WorkEntry> workEntries = new ArrayList<>();
        
        for (String dateTime : hoursAndDays) {
            String[] parts = dateTime.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            long hoursWorked = Long.parseLong(parts[1]);
            
            workEntries.add(new WorkEntry(date, hoursWorked));
        }
        
        // Sort the workEntries by date
        Collections.sort(workEntries, Comparator.comparing(WorkEntry::getDate));
        
        long fullHours = 0;
        
        for (int i = 0; i < workEntries.size(); i++) {
            long totalHours = 0;
            LocalDate startDate = workEntries.get(i).getDate();
            
            for (int j = i; j < workEntries.size(); j++) {
                LocalDate currentDate = workEntries.get(j).getDate();
                
                // If the current date is within seven days of the start date, add hours worked
                if (!currentDate.isAfter(startDate.plusDays(6))) {
                    totalHours += workEntries.get(j).getHoursWorked();
                    
                    if (totalHours > 40) {
                        return true;// replace with multiply newest hours by 1.5
                    }
                } else {
                    fullHours = fullHours + totalHours;
                    break;
                }
            }
        }

        return false;
    }

}


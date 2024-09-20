import java.io.*;
import java.time.*;
import java.time.temporal.*;
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
    LocalDateTime clockedIn;
    ArrayList <String> hoursAndDays;

    // constructor that takes in Employee's name (String newName), id (String newEmployeeID), birthday (newBirthday), work experience (String newWorkExperience), hourly wage (boolean newHourly), and salary (newSalary)
    public Employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
        hoursAndDays = new ArrayList <String> (); //add to this list to log the days and hours worked
        clockedIn = null;
        this.save();
    }

    // uses the (assumed to already be created) text file by the name of (String id).txt to get an Employee object with all the saved information
    public Employee (String id){
        FileReader fr = null;
        Scanner in = null;
        
        try {
            fr = new FileReader("Employees/" + id + ".txt");
            in = new Scanner (fr);
        } catch (IOException e){
            return;
        }

        String line = null;
        Dictionary<String,String> values = new Hashtable<String,String>();
        while(in.hasNext()){
            line = in.nextLine();
            if(!line.equals("")){
                String[] words = line.split(": ");
                if(words.length > 1){
                    values.put(words[0], words[1]);
                }
            }
        }

        if(values.get("name") != null){
            name = values.get("name");
        }else{
            name = " ";
        }

        employeeID = id;

        if(values.get("birthday") != null){
            birthday = values.get("birthday");
        }else{
            birthday = " ";
        }
        if(values.get("work_experience") != null){
            workExperience = values.get("work_experience");
        }else{
            workExperience = " ";
        }
        if(values.get("hourly") != null){
            hourly = Boolean.valueOf(values.get("hourly"));
        }else{
            hourly = false;
        }
        if(values.get("salary") != null){
            salary = Double.valueOf(values.get("salary"));
        }else{
            salary = 0.0;
        }
        if(values.get("hours_and_days") != null){
            hoursAndDays = ArrayListParser.parseArrayListString(values.get("hours_and_days"));
        }else{
            hoursAndDays = ArrayListParser.parseArrayListString("[]");
        }
        if(values.get("clocked_in").equals("null")){
            clockedIn = null;
        }else{
            clockedIn = LocalDateTime. parse(values.get("clocked_in"));
        }
    
        try{
            fr.close();
            in.close();
        } catch(IOException e){
            System.out.println("Error: could not close file reader");
        }
    }

    // returns true if the employee is currently working and false otherwise
    public boolean clockedIn(){
        return !(clockedIn == null);
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
        String rtrnr = "\n\n--------------------------------------------------------------\n";
        rtrnr += name + "\t\t\t\t\t" + employeeID;
        if(hourly){
            rtrnr += "\nHourly: ";
        }else{
            rtrnr += "\nSalary: ";
        }
        rtrnr += salary;
        
        for(String s: hoursAndDays){
            rtrnr += "\n\t" + s;
        }

        return rtrnr;
    }

    // saves all of the Employee information into a txt file by the name of the the employee's ID
    public void save(){
        FileWriter fw = null;
        PrintWriter out = null;

        try{
            fw = new FileWriter("Employees/" + employeeID + ".txt");
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
        out.println("hours_and_days: " + hoursAndDays);
        out.println("clocked_in: " + clockedIn);

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing files " + e);
        }
    }

    //getter clocked in
    public LocalDateTime getClockedIn (){
        return clockedIn;
    }
    //setter clocked in
    public void setClockedIn (LocalDateTime newClockedIn){
        clockedIn = newClockedIn;
    }

    //getter list of hours and days
    public ArrayList <String> getHoursAndDays (){
        return hoursAndDays;
    }

    //clocks in employee
    public boolean clockIn (){
        if (clockedIn == null){
            clockedIn = LocalDateTime.now();
            save();
            return true;
        }
        return false;
    }

    //clocks out employee and calculates hours worked ** CHANGE TO WORK WITH 
    public boolean clockOut (){
        if (clockedIn != null){
            double time =  Double.valueOf(ChronoUnit.HOURS.between(clockedIn, LocalDateTime.now()));
            LocalDate day = LocalDate.now();
            hoursAndDays.add(day + " " + time); //adds date and hours worked for the payroll
            clockedIn = null;
            save();
            return true;
        }
        return false;
    }

    //puts hours and days worked back to zero
    public void resetPayPeriod (){
        hoursAndDays.clear ();
    }

    //finds all the start and end dates of the 7 day periods
    public ArrayList<String> startsAndEnds (LocalDate startDate, ArrayList <LocalDate> holidays){
        //arraylist stores the start/end of periods
        
        ArrayList <String> weeks = new ArrayList <String> ();
        LocalDate end = LocalDate.now ();
        int val = 0; //tracks the number of days passed
        LocalDate start = startDate;
        LocalDate current = startDate;

        //goes through until you reach current day
        while (current.isBefore (end)){
            
            //goes through until reaching 7 days or current day
            while (val < 7 && current.isBefore(end)){
                if (!holidays.contains (current)){
                    val++;
                }
                current = current.plusDays (1);
            }
            //once it reaches a seven day period, that period is added to the arraylist
            if (val >= 7){
                weeks.add (start + " " + current.minusDays(1)); //adds values to the arraylist
            }else{
                weeks.add(start + " " + end);
            }
            
            //goes until the next non-holiday and sets new start date
            while (holidays.contains(current)){
                current = current.plusDays(1);
            }
            start = current;
            val = 0;
        }
        return weeks;
    }

    //calculates pay for the employee
    public double calculatePay(LocalDate start, ArrayList<LocalDate> holidays){
        double pay;
        if(hourly){
            pay = hourlyPay(start, holidays);
        }else{
            pay = nonHourlyPay(start, holidays);
        }
        return pay;
    }

    //calculates the pay for the employee (not including overtime or deductions) for nonhourly employees
    public double nonHourlyPay (LocalDate start, ArrayList<LocalDate> holidays){
        double totalPay = 0.0;

        ArrayList<String> weeks = startsAndEnds(start, holidays);

        HashMap<LocalDate, Double> hours = new HashMap<LocalDate, Double>();
        
        LocalDate curr = null;
        LocalDate end = LocalDate.now();

        // cycle through all hours and days, setting up hours
        for(String dateTime: hoursAndDays){
            // adds hours to day if day has already been inputted, or inputs day with hours
            if(hours.get(LocalDate.parse(dateTime.split(" ")[0])) != null){
                hours.put(LocalDate.parse(dateTime.split(" ")[0]), hours.get(LocalDate.parse(dateTime.split(" ")[0])) + (Double.valueOf(dateTime.split(" ")[1])));
                
            }else{
                hours.put(LocalDate.parse(dateTime.split(" ")[0]), Double.valueOf(dateTime.split(" ")[1]));
            }
        }

        double currHours;
        // increase paydoc for every week that they work less than 40 hours (+1 for every 8 hours they miss)
        int paydoc = 0;
        for(String week: weeks){
            currHours = 40.0;
            curr = LocalDate.parse(week.split(" ")[0]);
            end = LocalDate.parse(week.split(" ")[1]);

            while(curr.isBefore(end) || curr.isEqual(end)){
                if(hours.get(curr) != null){
                    currHours = currHours - hours.get(curr);
                }
                curr = curr.plusDays(1);
            }

            if(currHours > 0){
                paydoc = paydoc + ( ((int)(currHours) - ((int)currHours % 8)) /8 + 1 );
            }
        }
        
        totalPay = (salary/365) * (end.compareTo(start) - paydoc);

        if(totalPay < 0.0){
            return 0.0;
        }

        return Math.round(totalPay * 100)/100;
    }
    


    public double hourlyPay (LocalDate start, ArrayList<LocalDate> holidays){
        
        if(hoursAndDays.size() == 0){
            return 0.0;
        }

        ArrayList<LocalDate> bonus_nine = checkNine(holidays);

        ArrayList<String> weeks = startsAndEnds(start, holidays);

        HashMap<LocalDate, Double> hours = new HashMap<LocalDate, Double>();
        HashMap<LocalDate, Double> pay = new HashMap<LocalDate, Double>();
        
        // cycle through all hours and days, setting up hours
        for(String dateTime: hoursAndDays){
            // adds hours to day if day has already been inputted, or inputs day with hours
            if(hours.get(LocalDate.parse(dateTime.split(" ")[0])) != null){
                hours.put(LocalDate.parse(dateTime.split(" ")[0]), hours.get(LocalDate.parse(dateTime.split(" ")[0])) + (Double.valueOf(dateTime.split(" ")[1])));
                
            }else{
                hours.put(LocalDate.parse(dateTime.split(" ")[0]), Double.valueOf(dateTime.split(" ")[1]));
            }
        }

        
        LocalDate curr = null;
        LocalDate end = null;

        double currHours;
        double totalPay = 0.0;

        // cycles through every week in the period and calculates pay for each day
        for(String week: weeks){
            currHours = 40.0;
            curr = LocalDate.parse(week.split(" ")[0]);
            end = LocalDate.parse(week.split(" ")[1]);

            while(curr.isBefore(end) || curr.isEqual(end)){
                /**
                 * if 40 hours already completed, 1.5x pay for all remaining hours in that day
                 * else if 40 will be completed that day, plus more, 1.5x extra hours and 1x hours till forty
                 * else if 40 will not be completed, 1x that days pay
                 */
                if(hours.get(curr) != null){
                    if(currHours <= 0){
                        pay.put(curr, hours.get(curr) * salary * 1.5);
                        currHours = currHours - hours.get(curr);
                    }else{
                        currHours = currHours - hours.get(curr);
                        if(currHours <= 0){
                            pay.put(curr, ((currHours * -1.5) + (hours.get(curr) + currHours)) * salary); //change to fit with an equation
                        }else{
                            pay.put(curr, hours.get(curr) * salary);
                        }
                    }
                }
                curr = curr.plusDays(1);
            }
        }

        // adds all nine day bonuses
        for(LocalDate d: bonus_nine){
            pay.put(d, pay.get(d) * 1.5);
        }


        // sums all pay to one salary
        for(Map.Entry<LocalDate, Double> entry: pay.entrySet()){
            totalPay = totalPay + entry.getValue();
        }

        if(totalPay < 0.0){
            return 0.0;
        }

        return totalPay;
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
            // if they did not work on the date, and the date was not a holiday, restart counter
            if(!dates.contains(curr.plusDays(1)) && !holidays.contains(curr.plusDays(1))){
                restart = true;
                count = 1;
            }else{
                curr = curr.plusDays(1);
                count = count + 1;
            }
            
        }

        return bonusDays;
        
    }

    
    

}


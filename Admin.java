import java.io.*;
import java.util.*;
import java.time.LocalDate;

/**
 * Name: Admin                               Last Updated: 11/18/24
 * 
 * Creator: D. Balin
 * 
 * Purpose: Class to make Admin objects that can find all employees, add employees, save all information changes, and get a payroll text file
 */


public class Admin{

    // gets a list of all employees
    public static ArrayList<String> allEmployees(String path){
        File emp_fold = new File(path);  
        File[] emp_files = emp_fold.listFiles();
        ArrayList<String> list = new ArrayList<String>();

        for (File file : emp_files) {
            if (file.isFile() && file.getName().contains(".txt")) {
                list.add(file.getName().replace(".txt", ""));
            }
        }

        return list;
    }

    // returns an ArrayList<String> of all the holidays in the current pay period
    public static ArrayList<String> getHolidays(){

        // FILE READING -------------------------------------------------------------
        
        FileReader fr = null;
        Scanner in = null;
        
        try {
            fr = new FileReader("Current_Payroll.txt");
            in = new Scanner (fr);
        } catch (IOException e){
            System.exit(0);
        }

        //skip start date
        in.nextLine();

        ArrayList<String> holidays = new ArrayList<String>();

        while (in.hasNext()){
            holidays.add(in.nextLine());
        }

        try {
            fr.close();
            in.close();
        } catch (IOException e){
            System.exit(0);
        }

        return holidays;
    }

    // adds an Employee with given values, returns said Employee
    public static Employee addEmployee(String name, String id, String birthday, String experience, boolean hourly, double salary){
        if(!allEmployees("Employees/").contains(id)){
            return (new Employee(name, id, birthday, experience, hourly, salary));
        }
        else{
            return null;
        }
    }

    // deletes file for employee with given id (String id)
    public static void removeEmployee(String id){
        File file = new File("Employees/" + id + ".txt");
 
        if (file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }

    // saves all employees by parsing through the current files
    public static void saveAll(){
        ArrayList<String> emps = allEmployees("Employees/");
        Employee emp;
        for (String e: emps){
            emp = new Employee(e);
            emp.clockOut();
            emp.save();
        }
    }

    // creates a file with each line being in the format (employee_id), $(pay)
    public static void getPayroll(){
        ArrayList<String> emps = allEmployees("Employees/");
        Employee emp = null;
        ArrayList<String> pay = new ArrayList<String>();

        // FILE READING -------------------------------------------------------------
        
        FileReader fr = null;
        Scanner in = null;
        
        try {
            fr = new FileReader("Current_Payroll.txt");
            in = new Scanner (fr);
        } catch (IOException e){
            return;
        }

        LocalDate start = LocalDate.parse(in.nextLine());

        ArrayList<LocalDate> holidays = new ArrayList<LocalDate>();

        while (in.hasNext()){
            holidays.add(LocalDate.parse(in.nextLine()));
        }

        try {
            fr.close();
            in.close();
        } catch (IOException e){
            return;
        }


        // CALCULATIONS -------------------------------------------------------------
        
        for (String e: emps){
            if(!e.contains("Payroll")){
                emp = new Employee(e);
                //clocks out employees
                emp.clockOut();
                pay.add(new Employee(e) + "\nFull Pay: $" +  emp.calculatePay(start, holidays));
                // resets their pay period
                emp.resetPayPeriod();
                emp.save();
            }
        }


        // FILE WRITING -------------------------------------------------------------

        FileWriter fw = null;
        PrintWriter out = null;

        // rewrites the file consisting of all holidays and the start of the payroll to be for the newest payroll 
        try{
            fw = new FileWriter("Current_Payroll.txt", false);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }

        out.println(LocalDate.now());

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing files " + e);
        }

        // writes salary of all employess to file named Newest_Payroll.txt
        try{
            fw = new FileWriter("Payroll.txt", false);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }

        for(String e: pay){
            out.println(e);
        }

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing files " + e);
        }

    }

    // edits the employee with the given id (String id) to have new values for each, respective to the constructor
    public static void editEmployee(String newName, String id, String newBirthday, String newWorkExperience, boolean newHourly, String newSalary){
        Employee e = new Employee(id);
        if(newName != ""){
            e.setName(newName);
        }
        if(newBirthday != ""){
            e.setBirthday(newBirthday);
        }
        if(newWorkExperience != ""){
            e.setWorkExperience(newWorkExperience);
        }
        
        e.setHourly(newHourly);
        
        if(newSalary != ""){
            e.setSalary(Double.valueOf(newSalary));
        }
        e.save();
    }

    // adds a date (LocalDate day) to the list of holidays in the current pay period
    public static void addHoliday(LocalDate day){
        FileWriter fw = null;
        PrintWriter out = null;
        
        // writes information to file
        try{
            fw = new FileWriter("Current_Payroll.txt", true);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }

        out.println(day);

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing files " + e);
        }

    }
}

import java.io.*;
import java.util.*;

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

    // adds an Employee with given values, returns said Employee
    public Employee addEmployee(String name, String id, String birthday, String experience, boolean hourly, double salary){
        if(allEmployees("/Users/danabalin/Documents/AT2-Employee-Payroll/").contains(id)){
            return (new Employee(name, id, birthday, experience, hourly, salary));
        }
        else{
            return null;
        }
    }

    // deletes file for employee with given id (String id)
    public void removeEmployee(String id){
        File file = new File("/Users/danabalin/Documents/AT2-Employee-Payroll/" + id + ".txt");
 
        if (file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }
    }

    // saves all employees by parsing through the current files
    public void clockOut(){
        ArrayList<String> emps = allEmployees("/Users/danabalin/Documents/AT2-Employee-Payroll/");
        Employee emp;
        for (String e: emps){
            emp = new Employee(e);
            emp.clockOut();
            emp.save();
        }
    }

    // creates a file with each line being in the format (employee_id), $(pay)
    public void getPayroll(){
        ArrayList<String> emps = allEmployees("/Users/danabalin/Documents/AT2-Employee-Payroll/");
        Employee emp;
        ArrayList<String> pay = new ArrayList<String>();
        for (String e: emps){
            emp = new Employee(e);
            //clocks out employees
            emp.clockOut();
            pay.add(e + ", $" +  emp.calculatePay());
            // resets their pay period
            emp.resetPayPeriod();
            emp.save();
        }

        FileWriter fw = null;
        PrintWriter out = null;

        // writes information to file
        try{
            fw = new FileWriter("/Users/danabalin/Documents/AT2-Employee-Payroll/Newest_Payroll.txt");
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
}

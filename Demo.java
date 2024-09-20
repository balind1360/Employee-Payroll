import java.io.*;

/**
 * Name: Demo                        Last Updated: 11/20/24
 * 
 * Creator: D. Balin
 * 
 * Purpose: File to create example files
 */

public class Demo {
    public static void main(String[] args){
        // create Current_Payroll.txt starting three weeks ago, with several holidays
        FileWriter fw = null;
        PrintWriter out = null;

        try{
            fw = new FileWriter("Current_Payroll.txt", false);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }
        //START DATE: September 1st
        out.println("2024-09-05");

        //HOLIDAYS: September 10th, September 11th
        out.println("2024-09-10");
        out.println("2024-09-11");

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing output file: " + e);
            System.exit(1);
        }

        // create first employee file
        try{
            fw = new FileWriter("Employees/00000.txt", false);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }
        
        out.println("name: Peter Parker");
        out.println("birthday: 01/01/2006");
        out.println("work_experience: Intern");
        out.println("hourly: true");
        out.println("salary: 18.00");
        out.println("hours_and_days: [2024-09-06 8.0, 2024-09-07 9.0, 2024-09-08 8.0, 2024-09-09 8.0, 2024-09-12 9.0, 2024-09-14 7.0, 2024-09-15 7.0, 2024-09-16 6.0, 2024-09-17 5.0, 2024-09-18 7.0, 2024-09-19 6.0, 2024-09-20 5.0]");
        out.println("clocked_in: null");

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing output file: " + e);
            System.exit(1);
        }

        // create second employee file

        try{
            fw = new FileWriter("Employees/54321.txt", false);
            out = new PrintWriter(fw);
        } catch(IOException e){
            System.out.println("Error opening output file: " + e);
            System.exit(1);
        }
        
        out.println("name: Moon Girl");
        out.println("birthday: 12/12/2012");
        out.println("work_experience: Superhero");
        out.println("hourly: false");
        out.println("salary: 200000.00");
        out.println("hours_and_days: [2024-09-06 8.0, 2024-09-09 8.0, 2024-09-12 9.0, 2024-09-14 7.0, 2024-09-15 7.0, 2024-09-17 5.0, 2024-09-18 7.0, 2024-09-19 6.0, 2024-09-20 5.0]");
        out.println("clocked_in: null");

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing output file: " + e);
            System.exit(1);
        }

    }
}

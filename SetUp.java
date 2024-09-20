import java.io.*;

/**
 * Name: SetUp                        Last Updated: 11/18/24
 * 
 * Creator: D. Balin
 * 
 * Purpose: One-time run function to set up necessary files on computer
 */

import java.time.LocalDate;

public class SetUp {
    public static void main(String[] args){

        new File("Employees").mkdirs();

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
        out.println(LocalDate.now());

        try{
            fw.close();
            out.close();
        } catch(IOException e){
            System.out.println("Error closing output file: " + e);
            System.exit(1);
        }

        
    }
}

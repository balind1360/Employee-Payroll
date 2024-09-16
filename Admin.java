import java.io.*;
import java.util.*;

public class Admin{

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

    public void save(){
        ArrayList<String> emps = allEmployees("/Users/danabalin/Documents/AT2-Employee-Payroll/");
        Employee emp;
        for (String e: emps){
            emp = new Employee(e);
            emp.save();
        }
    }
}

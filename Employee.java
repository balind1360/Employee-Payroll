import java.io.*;
import java.util.*;

public class Employee {
    String name;
    String employeeID;
    String birthday;
    String workExperience;
    boolean hourly;
    double salary;

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

    public Employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
    }

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
        employeeID = values.get("id");
        birthday = values.get("birthday");
        workExperience = values.get("work_experience");
        hourly = Boolean.valueOf(values.get("hourly"));
        salary = Double.valueOf(values.get("salary"));
    
        try{
            fr.close();
            in.close();
        } catch(IOException e){
            return;
        }
    }

    public String toString (){
        return name;
    }
}

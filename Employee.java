import java.io.*;
import java.util.*;

public class Employee {
    String name;
    String employeeID;
    String birthday;
    String workExperience;
    boolean hourly;
    double salary;

    public Employee (String newName, String newEmployeeID, String newBirthday, String newWorkExperience, boolean newHourly, double newSalary){
        name = newName;
        employeeID = newEmployeeID;
        birthday = newBirthday;
        workExperience = newWorkExperience;
        hourly = newHourly;
        salary = newSalary;
    }

    public Employee (){
        name = "";
        employeeID = "";
        birthday = "";
        workExperience = "";
        hourly = true;
        salary = 0.0;
    }

    public String toString (){
        return name;
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
}

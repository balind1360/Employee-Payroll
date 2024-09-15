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

    public String toString (){
        return name;
    }
}

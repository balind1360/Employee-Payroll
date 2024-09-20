/* 
Julia Heintz
date created: 9.12.2024
last edited: 9.20.2024 

description: GUI welcome window for employee manager 
*/ 

//used geeks4geeks & oracle to learn boxlayout
//import packages for box layout 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;
import java.util.*;
import java.time.LocalDate;


public class Main{ 
	//create private variables that can be accesses in listener class
    
	private static JPanel panel; 

    private static JFrame addFrame;

    private static JTextField name;
    private static JTextField id;
    private static JTextField newId;
    private static JTextField birthday;
    private static JTextField history;
    private static JTextField emPay;
    private static boolean hourly;
    private static JTextField date;


	public static void main(String[] args) {
		home();

	}

    

	// create buttonClicked event listener 
	public static class ButtonClickedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			if(command.equals("cClicked")){
				//read & print to file 
				//calculate payroll
                addFrame.setVisible(false);
                payroll();
                Admin.getPayroll();

			} else if(command.equals("Choose Employee")){
                // goes to screen where user can choose an employee file to view
                addFrame.setVisible(false);
                chooseEmployee();

            }else if(command.contains("view")){
				//view employee - use Employee class
                viewEmployee(id.getText());

			} else if(command.equals("aClicked")){ 
				//add an employee
				addFrame.setVisible(false);
				addEmployee();

			} else if(command.equals("hClicked")){
				//set hourly pay
                hourly = true;

			} else if(command.equals("sClicked")){
				//set salary pay
                hourly = false;

            // goes to screen to put in information for a new employee and create said employee
			} else if(command.equals("add")){
                Employee curr = Admin.addEmployee(name.getText(), newId.getText(), birthday.getText(), history.getText(), hourly, Double.valueOf(emPay.getText()));
                if(curr != null){
                    curr.save();
                }
                addFrame.setVisible(false);
                adminHome();

            // returns to admin home
			} else if(command.equals("exit")){
                addFrame.setVisible(false);
                adminHome();

            // goes to screen to edit the employee with the id in the id textbox
            } else if(command.equals("edit")){
                addFrame.setVisible(false);
                editEmployee(id.getText());

            // submits all edits and saves them to the employee file
            } else if(command.equals("confirm edit")){
                String newName;
                try{
                    newName = name.getText();
                }catch(NumberFormatException e1){
                    newName = "";
                }
                String bday;
                try{
                    bday = birthday.getText();
                }catch(NumberFormatException e2){
                    bday = "";
                }
                String hist;
                try{
                    hist = history.getText();
                }catch(NumberFormatException e3){
                    hist = "";
                }
                String pay;
                try{
                    pay = emPay.getText();
                }catch(NumberFormatException e4){
                    pay = "";
                }
                
                Admin.editEmployee(newName, id.getText(), bday, hist, hourly, pay);
                addFrame.setVisible(false);
                adminHome();

            // returns back to start screen
            } else if(command.equals("sign out")){
                addFrame.setVisible(false);
                home();

            // signs in to either the admin account or a specific employee account and goes to the respective home pages
            } else if(command.equals("sign in")){
                if(id.getText().equals("000100")){
                    addFrame.setVisible(false);
                    adminHome();
                }else if(Admin.allEmployees("Employees/").contains(id.getText())){
                    Employee emp = new Employee(id.getText());
                    if(emp != null){
                        addFrame.setVisible(false);
                        empHome(emp);
                    }
                }

            // clocks in a given employee
            } else if(command.equals("clock in")){
                addFrame.setVisible(false);
                Employee emp = new Employee(id.getText());
                emp.clockIn();
                empHome(emp);

            // clocks out a given employee
            } else if(command.equals("clock out")){
                addFrame.setVisible(false);
                Employee emp = new Employee(id.getText());
                emp.clockOut();
                empHome(emp);

            //adds holiday according to the value in the date textbox, then returns to admin home
            } else if(command.equals("add holiday confirm")){
                Admin.addHoliday(LocalDate.parse(date.getText()));
                addFrame.setVisible(false);
                adminHome();
            
            //enters screen to add a holiday
            } else if(command.equals("add holiday")){
                addFrame.setVisible(false);
                addHoliday();
            }
		}
	}

    public static void home(){
        addFrame = new JFrame("SIGN IN");
		addFrame.setSize(700,700);

		//create panel of components
		panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));

		//create boxlayout for JPanel panel 
		// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

        JLabel title = new JLabel("Enter Employee ID");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

		id = new JTextField();
		id.setAlignmentX(Component.CENTER_ALIGNMENT);
		id.setHorizontalAlignment(JTextField.CENTER);
		id.setBackground(Color.PINK);
		id.setMaximumSize(new Dimension(250,40));
		id.setEditable(true);
		panel.add(id);

        //create action buttons & add them to the panel
		JButton sign_in = new JButton("SIGN IN");
		sign_in.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(sign_in);

	    //button pressed events for each action button 
		sign_in.setActionCommand("sign in");
		sign_in.addActionListener(new ButtonClickedListener());
		
	    //close admin frame
		addFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

	//make visible 
		addFrame.add(panel);
		addFrame.pack();
		addFrame.setVisible(true);
    }

    // screen to add a new holiday to the current pay period
    public static void addHoliday(){
        addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));

		//create boxlayout for JPanel panel 
		// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

        JLabel title = new JLabel("Enter Holiday (YYYY-mm-dd)");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

		date = new JTextField();
		date.setAlignmentX(Component.CENTER_ALIGNMENT);
		date.setHorizontalAlignment(JTextField.CENTER);
		date.setBackground(Color.PINK);
		date.setMaximumSize(new Dimension(250,40));
		date.setEditable(true);
		panel.add(date);

        JLabel holidayList = new JLabel("Current Holidays: ");
        holidayList.setSize(400,400);
        holidayList.setAlignmentX(Component.CENTER_ALIGNMENT);
        holidayList.setFont(new Font(holidayList.getFont().getName(), Font.PLAIN, 15));
        panel.add(holidayList);
        
        space = new JLabel("  ");
		panel.add(space);

        ArrayList<String> holidays = Admin.getHolidays();
        for(String h: holidays){
            holidayList = new JLabel(h);
            holidayList.setSize(400,400);
            holidayList.setAlignmentX(Component.CENTER_ALIGNMENT);
            holidayList.setFont(new Font(holidayList.getFont().getName(), Font.PLAIN, 15));
            panel.add(holidayList);

            space = new JLabel("  ");
		    panel.add(space);
        }

        //create action buttons & add them to the panel
		JButton go = new JButton("ENTER");
		go.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(go);
	    //button pressed events for each action button 
		go.setActionCommand("add holiday confirm");
		go.addActionListener(new ButtonClickedListener());

        //exit button 
        JButton confirm = new JButton("BACK");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setActionCommand("exit");
        confirm.addActionListener(new ButtonClickedListener());
        confirm.setOpaque(true);
        confirm.setBackground(Color.GREEN);
        panel.add(confirm);
		
	    //close admin frame
		addFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

	//make visible 
		addFrame.add(panel);
		addFrame.pack();
		addFrame.setVisible(true);

    }

    // creates and shows a home screen for any employee account
    public static void empHome(Employee e){
        addFrame = new JFrame("Employee Manager: Employee");
        addFrame.setSize(700,700);
        
        //create panel of components
        JPanel panel = new JPanel();
        //set layout for panel (box layout for Y axis)
        panel.setPreferredSize(new Dimension(700,700));
        //create boxlayout for JPanel panel 
            // allows components to be aligned in the center & stacked along Y axis (Page axis)
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(boxlayout);

        JLabel title = new JLabel("Employee Information");
        title.setSize(400,400);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        panel.add(title);

        JLabel space = new JLabel("  ");
        panel.add(space);

        //create new employee information 
        //employee name
        JLabel name = new JLabel("Name: " + e.getName());
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setFont(new Font(name.getFont().getName(), Font.PLAIN, 18));
        panel.add(name);


        JLabel space1 = new JLabel("  ");
        panel.add(space1);

        //employee id
        JLabel ID = new JLabel("Employee ID: " + e.getEmployeeID());
        ID.setAlignmentX(Component.CENTER_ALIGNMENT);
        ID.setFont(new Font(ID.getFont().getName(), Font.PLAIN, 18));
        panel.add(ID);

        JLabel space2 = new JLabel("  ");
        panel.add(space2);

        //employee birthday 
        JLabel bday = new JLabel("Birthday: " + e.getBirthday());
        bday.setAlignmentX(Component.CENTER_ALIGNMENT);
        bday.setFont(new Font(bday.getFont().getName(), Font.PLAIN, 18));
        panel.add(bday);

        JLabel space3 = new JLabel("  ");
        panel.add(space3);

        //employee pay plan (button - hourly vs salary)
        JLabel payPlan; 
        if(e.isHourly()){
            payPlan = new JLabel("Pay Plan: HOURLY");
        }else{
            payPlan = new JLabel("Pay Plan: SALARY");
        }
        payPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
        payPlan.setFont(new Font(payPlan.getFont().getName(), Font.PLAIN, 18));
        panel.add(payPlan);
        

        JLabel space4 = new JLabel("  ");
        panel.add(space4);

        //enter pay (whether per hour or yearly)
        JLabel pay = new JLabel("Pay: " + e.getSalary());
        pay.setAlignmentX(Component.CENTER_ALIGNMENT);
        pay.setFont(new Font(pay.getFont().getName(), Font.PLAIN, 18));
        panel.add(pay);
        

        JLabel space5 = new JLabel("  ");
        panel.add(space5);

        //enter employees work history 
        JLabel hist = new JLabel("Work History: " + e.getWorkExperience());
        hist.setAlignmentX(Component.CENTER_ALIGNMENT);
        hist.setFont(new Font(hist.getFont().getName(), Font.PLAIN, 18));
        panel.add(hist);

        JLabel space6 = new JLabel("  ");
        panel.add(space6);

        //confirmation button 
        if(!e.clockedIn()){
            JButton cIn = new JButton("CLOCK IN");
            cIn.setAlignmentX(Component.CENTER_ALIGNMENT);
            cIn.setActionCommand("clock in");
            cIn.addActionListener(new ButtonClickedListener());
            cIn.setOpaque(true);
            cIn.setBackground(Color.GREEN);
            panel.add(cIn);
        }else{
            JButton cIn = new JButton("CLOCK OUT");
            cIn.setAlignmentX(Component.CENTER_ALIGNMENT);
            cIn.setActionCommand("clock out");
            cIn.addActionListener(new ButtonClickedListener());
            cIn.setOpaque(true);
            cIn.setBackground(Color.GREEN);
            panel.add(cIn);
        }
        

        //confirmation button 
        JButton confirm = new JButton("SIGN OUT");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setActionCommand("sign out");
        confirm.addActionListener(new ButtonClickedListener());
        confirm.setOpaque(true);
        confirm.setBackground(Color.GREEN);
        panel.add(confirm);

        //add panel to frame 
        addFrame.add(panel);
        addFrame.pack();

        //make frame visible 
        addFrame.setVisible(true);

        //close admin frame
        addFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
                //PROGRAM TO SAVE
            }
        });

    }

    // creates and shows a home screen for the admin account
    public static void adminHome(){
        addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

        //create action buttons & add them to the panel
		JButton calc = new JButton("END PAY PERIOD");
		calc.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton view = new JButton("VIEW EMPLOYEE");
		view.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton add = new JButton("ADD EMPLOYEE");
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton sOut = new JButton("SIGN OUT");
        sOut.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton holiday = new JButton("ADD HOLIDAY");
        holiday.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(calc);
		panel.add(view);
		panel.add(add);
        panel.add(holiday);
        panel.add(sOut);


	//button pressed events for each action button 
		calc.setActionCommand("cClicked");
		calc.addActionListener(new ButtonClickedListener());
		view.setActionCommand("Choose Employee");
		view.addActionListener(new ButtonClickedListener());
		add.setActionCommand("aClicked");
		add.addActionListener(new ButtonClickedListener());
		sOut.setActionCommand("sign out");
		sOut.addActionListener(new ButtonClickedListener());
		holiday.setActionCommand("add holiday");
		holiday.addActionListener(new ButtonClickedListener());


	//close admin frame
		addFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

	//make visible 
		addFrame.add(panel);
		addFrame.pack();
		addFrame.setVisible(true);

    }

    // a screen where the user can edit employee information fields
    public static void editEmployee(String emp_id){
        
        addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		JPanel panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

		JLabel title = new JLabel("New Employee Information");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

        Employee e = new Employee(emp_id);

		//employee name
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, 18));
		panel.add(nameLabel);

		name = new JTextField(e.getName());
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setBackground(Color.PINK);
		name.setMaximumSize(new Dimension(250,40));
		name.setEditable(true);
		panel.add(name);

		JLabel space1 = new JLabel("  ");
		panel.add(space1);


		//employee birthday 
		JLabel bday = new JLabel("Birthday (mm/dd/yyyy): ");
		bday.setAlignmentX(Component.CENTER_ALIGNMENT);
		bday.setFont(new Font(bday.getFont().getName(), Font.PLAIN, 18));
		panel.add(bday);
		
        birthday = new JTextField(e.getBirthday());
		birthday.setAlignmentX(Component.CENTER_ALIGNMENT);
		birthday.setHorizontalAlignment(JTextField.CENTER);
		birthday.setBackground(Color.PINK);
		birthday.setMaximumSize(new Dimension(250,40));
		birthday.setEditable(true);
		panel.add(birthday);


		JLabel space3 = new JLabel("  ");
		panel.add(space3);

		//employee pay plan (button - hourly vs salary)
		JLabel payPlan = new JLabel("Pay Plan: ");
		payPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
		payPlan.setFont(new Font(payPlan.getFont().getName(), Font.PLAIN, 18));
		panel.add(payPlan);

		//buttons to select plan
		JButton hourlyB = new JButton("HOURLY");
		hourlyB.setAlignmentX(Component.CENTER_ALIGNMENT);
		hourlyB.setActionCommand("hClicked");
		hourlyB.addActionListener(new ButtonClickedListener());
		panel.add(hourlyB);

		JButton salB = new JButton("SALARY");
		salB.setAlignmentX(Component.CENTER_ALIGNMENT);
		salB.setActionCommand("sClicked");
		salB.addActionListener(new ButtonClickedListener());
		panel.add(salB);

		JLabel space4 = new JLabel("  ");
		panel.add(space4);

		//enter pay (whether per hour or yearly)
		JLabel pay = new JLabel("Pay (hourly or yearly depending on pay plan): ");
		pay.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay.setFont(new Font(pay.getFont().getName(), Font.PLAIN, 18));
		panel.add(pay);
		

		emPay = new JTextField(String.valueOf(e.getSalary()));
		emPay.setAlignmentX(Component.CENTER_ALIGNMENT);
		emPay.setHorizontalAlignment(JTextField.CENTER);
		emPay.setBackground(Color.PINK);
		emPay.setMaximumSize(new Dimension(250,40));
		emPay.setEditable(true);
		panel.add(emPay);

		JLabel space5 = new JLabel("  ");
		panel.add(space5);

		//enter employees work history 
		JLabel hist = new JLabel("Work History: ");
		hist.setAlignmentX(Component.CENTER_ALIGNMENT);
		hist.setFont(new Font(hist.getFont().getName(), Font.PLAIN, 18));
		panel.add(hist);
		
        history = new JTextField(e.getWorkExperience());
		history.setAlignmentX(Component.CENTER_ALIGNMENT);
		history.setHorizontalAlignment(JTextField.CENTER);
		history.setBackground(Color.PINK);
		history.setMaximumSize(new Dimension(325,80));
		history.setEditable(true);
		panel.add(history);

		JLabel space6 = new JLabel("  ");
		panel.add(space6);


        //edit button 
        JButton edit = new JButton("CONFIRM");
        edit.setAlignmentX(Component.CENTER_ALIGNMENT);
        edit.setActionCommand("confirm edit");
        edit.addActionListener(new ButtonClickedListener());
        edit.setOpaque(true);
        edit.setBackground(Color.GREEN);
        panel.add(edit);

        //exit button 
        JButton confirm = new JButton("BACK");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setActionCommand("exit");
        confirm.addActionListener(new ButtonClickedListener());
        confirm.setOpaque(true);
        confirm.setBackground(Color.GREEN);
        panel.add(confirm);

        //add panel to frame 
        addFrame.add(panel);
        addFrame.pack();

        //make frame visible 
        addFrame.setVisible(true);

        //close admin frame
        addFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
                //PROGRAM TO SAVE
            }
        });

    }

    // screen to calculate payroll, and give instructions
    public static void payroll(){
        addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		JPanel panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

		JLabel title = new JLabel("GO TO FILES AND VIEW 'Payroll.txt'\nSAVE FILE UNDER DIFFERENT NAME TO KEEP");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 10));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);


        JButton exit = new JButton("OK");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setActionCommand("exit");
		exit.addActionListener(new ButtonClickedListener());
		exit.setOpaque(true);
		exit.setBackground(Color.GREEN);
		panel.add(exit);

        //add panel to frame 
        addFrame.add(panel);
        addFrame.pack();

        //make frame visible 
        addFrame.setVisible(true);


        //close admin frame
        addFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
                //PROGRAM TO SAVE
            }
        });
    }

    // screen to enter an employee id to view them after submission
    public static void chooseEmployee(){
        addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		JPanel panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

		JLabel title = new JLabel("Enter Employee ID");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

		id = new JTextField();
		id.setAlignmentX(Component.CENTER_ALIGNMENT);
		id.setHorizontalAlignment(JTextField.CENTER);
		id.setBackground(Color.PINK);
		id.setMaximumSize(new Dimension(250,40));
		id.setEditable(true);
		panel.add(id);

        //confirmation button 
        JButton confirm = new JButton("CONFIRM");
        confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirm.setActionCommand("view");
        confirm.addActionListener(new ButtonClickedListener());
        confirm.setOpaque(true);
        confirm.setBackground(Color.GREEN);
        panel.add(confirm);

        JButton exit = new JButton("BACK");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setActionCommand("exit");
		exit.addActionListener(new ButtonClickedListener());
		exit.setOpaque(true);
		exit.setBackground(Color.GREEN);
		panel.add(exit);

        //add panel to frame 
        addFrame.add(panel);
        addFrame.pack();

        //make frame visible 
        addFrame.setVisible(true);


        //close admin frame
        addFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
                //PROGRAM TO SAVE
            }
        });

    }

    // screen to view the employee information of the employee with the given id (String emp_id)
    public static void viewEmployee(String emp_id){
            addFrame = new JFrame("Employee Manager: Admin");
            addFrame.setSize(700,700);

            Employee e = new Employee(emp_id);
            
            //create panel of components
            JPanel panel = new JPanel();
            //set layout for panel (box layout for Y axis)
            panel.setPreferredSize(new Dimension(700,700));
            //create boxlayout for JPanel panel 
                // allows components to be aligned in the center & stacked along Y axis (Page axis)
            BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
            panel.setLayout(boxlayout);
    
            JLabel title = new JLabel("New Employee Information");
            title.setSize(400,400);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
            panel.add(title);
    
            JLabel space = new JLabel("  ");
            panel.add(space);
    
        //create new employee information 
            //employee name
            JLabel name = new JLabel("Name: " + e.getName());
            name.setAlignmentX(Component.CENTER_ALIGNMENT);
            name.setFont(new Font(name.getFont().getName(), Font.PLAIN, 18));
            panel.add(name);

    
            JLabel space1 = new JLabel("  ");
            panel.add(space1);
    
            //employee id
            JLabel ID = new JLabel("Employee ID: " + emp_id);
            ID.setAlignmentX(Component.CENTER_ALIGNMENT);
            ID.setFont(new Font(ID.getFont().getName(), Font.PLAIN, 18));
            panel.add(ID);
    
            JLabel space2 = new JLabel("  ");
            panel.add(space2);
    
            //employee birthday 
            JLabel bday = new JLabel("Birthday: " + e.getBirthday());
            bday.setAlignmentX(Component.CENTER_ALIGNMENT);
            bday.setFont(new Font(bday.getFont().getName(), Font.PLAIN, 18));
            panel.add(bday);
    
            JLabel space3 = new JLabel("  ");
            panel.add(space3);
    
            //employee pay plan (button - hourly vs salary)
            JLabel payPlan; 
            if(e.isHourly()){
                payPlan = new JLabel("Pay Plan: HOURLY");
            }else{
                payPlan = new JLabel("Pay Plan: SALARY");
            }
            payPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
            payPlan.setFont(new Font(payPlan.getFont().getName(), Font.PLAIN, 18));
            panel.add(payPlan);
            
    
            JLabel space4 = new JLabel("  ");
            panel.add(space4);
    
            //enter pay (whether per hour or yearly)
            JLabel pay = new JLabel("Pay: " + e.getSalary());
            pay.setAlignmentX(Component.CENTER_ALIGNMENT);
            pay.setFont(new Font(pay.getFont().getName(), Font.PLAIN, 18));
            panel.add(pay);
            
    
            JLabel space5 = new JLabel("  ");
            panel.add(space5);
    
            //enter employees work history 
            JLabel hist = new JLabel("Work History: " + e.getWorkExperience());
            hist.setAlignmentX(Component.CENTER_ALIGNMENT);
            hist.setFont(new Font(hist.getFont().getName(), Font.PLAIN, 18));
            panel.add(hist);
    
            JLabel space6 = new JLabel("  ");
            panel.add(space6);
    
            //edit button 
            JButton edit = new JButton("EDIT");
            edit.setAlignmentX(Component.CENTER_ALIGNMENT);
            edit.setActionCommand("edit");
            edit.addActionListener(new ButtonClickedListener());
            edit.setOpaque(true);
            edit.setBackground(Color.GREEN);
            panel.add(edit);

            //confirmation button 
            JButton confirm = new JButton("EXIT");
            confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
            confirm.setActionCommand("exit");
            confirm.addActionListener(new ButtonClickedListener());
            confirm.setOpaque(true);
            confirm.setBackground(Color.GREEN);
            panel.add(confirm);
    
            //add panel to frame 
            addFrame.add(panel);
            addFrame.pack();
    
            //make frame visible 
            addFrame.setVisible(true);

            //close admin frame
            addFrame.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                    //PROGRAM TO SAVE
                }
            });
    }


    // screen to fill in fields for a new employee and then create said employee
	public static void addEmployee(){
		addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		JPanel panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

		JLabel title = new JLabel("New Employee Information");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

	//create new employee information 
		//employee name
		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, 18));
		panel.add(nameLabel);

		name = new JTextField();
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setHorizontalAlignment(JTextField.CENTER);
		name.setBackground(Color.PINK);
		name.setMaximumSize(new Dimension(250,40));
		name.setEditable(true);
		panel.add(name);

		JLabel space1 = new JLabel("  ");
		panel.add(space1);

		//employee id
		JLabel ID = new JLabel("Employee ID: ");
		ID.setAlignmentX(Component.CENTER_ALIGNMENT);
		ID.setFont(new Font(ID.getFont().getName(), Font.PLAIN, 18));
		panel.add(ID);

		newId = new JTextField();
		newId.setAlignmentX(Component.CENTER_ALIGNMENT);
		newId.setHorizontalAlignment(JTextField.CENTER);
		newId.setBackground(Color.PINK);
		newId.setMaximumSize(new Dimension(250,40));
		newId.setEditable(true);
		panel.add(newId);

		JLabel space2 = new JLabel("  ");
		panel.add(space2);

		//employee birthday 
		JLabel bday = new JLabel("Birthday (mm/dd/yyyy): ");
		bday.setAlignmentX(Component.CENTER_ALIGNMENT);
		bday.setFont(new Font(bday.getFont().getName(), Font.PLAIN, 18));
		panel.add(bday);
		
        birthday = new JTextField();
		birthday.setAlignmentX(Component.CENTER_ALIGNMENT);
		birthday.setHorizontalAlignment(JTextField.CENTER);
		birthday.setBackground(Color.PINK);
		birthday.setMaximumSize(new Dimension(250,40));
		birthday.setEditable(true);
		panel.add(birthday);


		JLabel space3 = new JLabel("  ");
		panel.add(space3);

		//employee pay plan (button - hourly vs salary)
		JLabel payPlan = new JLabel("Pay Plan: ");
		payPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
		payPlan.setFont(new Font(payPlan.getFont().getName(), Font.PLAIN, 18));
		panel.add(payPlan);
		//buttons to select plan
		JButton hourlyB = new JButton("HOURLY");
		hourlyB.setAlignmentX(Component.CENTER_ALIGNMENT);
		hourlyB.setActionCommand("hClicked");
		hourlyB.addActionListener(new ButtonClickedListener());
		panel.add(hourlyB);

		JButton salB = new JButton("SALARY");
		salB.setAlignmentX(Component.CENTER_ALIGNMENT);
		salB.setActionCommand("sClicked");
		salB.addActionListener(new ButtonClickedListener());
		panel.add(salB);

		JLabel space4 = new JLabel("  ");
		panel.add(space4);

		//enter pay (whether per hour or yearly)
		JLabel pay = new JLabel("Pay (hourly or yearly depending on pay plan): ");
		pay.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay.setFont(new Font(pay.getFont().getName(), Font.PLAIN, 18));
		panel.add(pay);
		
        emPay = new JTextField();
		emPay = new JTextField();
		emPay.setAlignmentX(Component.CENTER_ALIGNMENT);
		emPay.setHorizontalAlignment(JTextField.CENTER);
		emPay.setBackground(Color.PINK);
		emPay.setMaximumSize(new Dimension(250,40));
		emPay.setEditable(true);
		panel.add(emPay);

		JLabel space5 = new JLabel("  ");
		panel.add(space5);

		//enter employees work history 
		JLabel hist = new JLabel("Work History: ");
		hist.setAlignmentX(Component.CENTER_ALIGNMENT);
		hist.setFont(new Font(hist.getFont().getName(), Font.PLAIN, 18));
		panel.add(hist);
		
        history = new JTextField();
		history.setAlignmentX(Component.CENTER_ALIGNMENT);
		history.setHorizontalAlignment(JTextField.CENTER);
		history.setBackground(Color.PINK);
		history.setMaximumSize(new Dimension(325,80));
		history.setEditable(true);
		panel.add(history);

		JLabel space6 = new JLabel("  ");
		panel.add(space6);

		//confirmation button 
		JButton confirm = new JButton("CONFIRM");
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setActionCommand("add");
		confirm.addActionListener(new ButtonClickedListener());
		confirm.setOpaque(true);
		confirm.setBackground(Color.GREEN);
		panel.add(confirm);

        JButton exit = new JButton("BACK");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setActionCommand("exit");
		exit.addActionListener(new ButtonClickedListener());
		exit.setOpaque(true);
		exit.setBackground(Color.GREEN);
		panel.add(exit);



		//add panel to frame 
		addFrame.add(panel);
		addFrame.pack();

		//make frame visible 
		addFrame.setVisible(true);

		//close admin frame
		addFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});
	}
}

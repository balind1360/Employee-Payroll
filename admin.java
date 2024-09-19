/* 
Julia Heintz 
date created: 9.12.2024
last edited: 9.18.2024 

description: GUI welcome window for employee manager 
*/ 

import java.io.*; 
import javax.swing.*;
import java.awt.event.*; 
import java.awt.*;

//used geeks4geeks & oracle to learn boxlayout
//import packages for box layout 
import javax.swing.BoxLayout; 
import javax.swing.Box; 
import java.awt.Component;


public class admin{ 
	//create private variables that can be accesses in listener class
	private static JFrame aFrame;
	private static JPanel panel; 

	public static void main(String[] args) {
		aFrame = new JFrame("Employee Manager: Admin");
		aFrame.setSize(700,700);

		//create panel of components
		panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

//create action buttons & add them to the panel
		JButton calc = new JButton("calculate payroll");
		calc.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton view = new JButton("view employee");
		view.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton add = new JButton("add employee");
		add.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(calc);
		panel.add(view);
		panel.add(add);


	//button pressed events for each action button 
		calc.setActionCommand("cClicked");
		calc.addActionListener(new ButtonClickedListener());
		view.setActionCommand("vClicked");
		view.addActionListener(new ButtonClickedListener());
		add.setActionCommand("aClicked");
		add.addActionListener(new ButtonClickedListener());


	//close admin frame
		aFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

	//make visible 
		aFrame.add(panel);
		aFrame.pack();
		aFrame.setVisible(true);

	}

	// create buttonClicked event listener 
	public static class ButtonClickedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			if(command.equals("cClicked")){
				//read & print to file 
				//calculate payroll
			} else if(command.equals("vClicked")){
				//view employee - use Employee class
			} else if(command.equals("aClicked")){ 
				aFrame.setVisible(false);
				addEmployee();
				//add an employee
			} else if(command.equals("hClicked")){
				//set hourly pay
			} else if(command.equals("sClicked")){
				//set salary pay
			} else if(command.equals("confirm")){
				//confirm & save new employee files 
				//return to admin main screen 
			}
		}
	}

	public static void addEmployee(){
		JFrame addFrame = new JFrame("Employee Manager: Admin");
		addFrame.setSize(700,700);

		//create panel of components
		JPanel panel = new JPanel();
		//set layout for panel (box layout for Y axis)
		panel.setPreferredSize(new Dimension(700,700));
		//create boxlayout for JPanel panel 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(boxlayout);

		JLabel title = new JLabel("Create a New Employee");
		title.setSize(400,400);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
		panel.add(title);

		JLabel space = new JLabel("  ");
		panel.add(space);

	//create new employee information 
		//employee name
		JLabel name = new JLabel("new employee name: ");
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		name.setFont(new Font(name.getFont().getName(), Font.PLAIN, 18));
		panel.add(name);

		JTextField newName = new JTextField();
		newName.setAlignmentX(Component.CENTER_ALIGNMENT);
		newName.setHorizontalAlignment(JTextField.CENTER);
		newName.setBackground(Color.PINK);
		newName.setMaximumSize(new Dimension(250,40));
		newName.setEditable(true);
		panel.add(newName);

		JLabel space1 = new JLabel("  ");
		panel.add(space1);

		//employee id
		JLabel id = new JLabel("create employee ID: ");
		id.setAlignmentX(Component.CENTER_ALIGNMENT);
		id.setFont(new Font(id.getFont().getName(), Font.PLAIN, 18));
		panel.add(id);

		JTextField newID = new JTextField();
		newID.setAlignmentX(Component.CENTER_ALIGNMENT);
		newID.setHorizontalAlignment(JTextField.CENTER);
		newID.setBackground(Color.PINK);
		newID.setMaximumSize(new Dimension(250,40));
		newID.setEditable(true);
		panel.add(newID);

		JLabel space2 = new JLabel("  ");
		panel.add(space2);

		//employee birthday 
		JLabel bday = new JLabel("employee birthday (mm/dd/yyyy): ");
		bday.setAlignmentX(Component.CENTER_ALIGNMENT);
		bday.setFont(new Font(bday.getFont().getName(), Font.PLAIN, 18));
		panel.add(bday);
		JTextField newBday = new JTextField();
		newBday.setAlignmentX(Component.CENTER_ALIGNMENT);
		newBday.setHorizontalAlignment(JTextField.CENTER);
		newBday.setBackground(Color.PINK);
		newBday.setMaximumSize(new Dimension(250,40));
		newBday.setEditable(true);
		panel.add(newBday);


		JLabel space3 = new JLabel("  ");
		panel.add(space3);

		//employee pay plan (button - hourly vs salary)
		JLabel payPlan = new JLabel("employee pay plan: ");
		payPlan.setAlignmentX(Component.CENTER_ALIGNMENT);
		payPlan.setFont(new Font(payPlan.getFont().getName(), Font.PLAIN, 18));
		panel.add(payPlan);
		//buttons to select plan
		JButton hourly = new JButton("hourly");
		hourly.setAlignmentX(Component.CENTER_ALIGNMENT);
		hourly.setActionCommand("hClicked");
		hourly.addActionListener(new ButtonClickedListener());
		panel.add(hourly);
		JButton sal = new JButton("salary");
		sal.setAlignmentX(Component.CENTER_ALIGNMENT);
		sal.setActionCommand("sClicked");
		sal.addActionListener(new ButtonClickedListener());
		panel.add(sal);

		JLabel space4 = new JLabel("  ");
		panel.add(space4);

		//enter pay (whether per hour or yearly)
		JLabel pay = new JLabel("pay (hourly or yearly depending on pay plan): ");
		pay.setAlignmentX(Component.CENTER_ALIGNMENT);
		pay.setFont(new Font(pay.getFont().getName(), Font.PLAIN, 18));
		panel.add(pay);
		JTextField emPay = new JTextField();
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
		JLabel hist = new JLabel("employee's work history: ");
		hist.setAlignmentX(Component.CENTER_ALIGNMENT);
		hist.setFont(new Font(hist.getFont().getName(), Font.PLAIN, 18));
		panel.add(hist);
		JTextField history = new JTextField();
		history.setAlignmentX(Component.CENTER_ALIGNMENT);
		history.setHorizontalAlignment(JTextField.CENTER);
		history.setBackground(Color.PINK);
		history.setMaximumSize(new Dimension(325,80));
		history.setEditable(true);
		panel.add(history);

		JLabel space5 = new JLabel("  ");
		panel.add(space5);

		//confirmation button 
		JButton confirm = new JButton("CONFIRM");
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setActionCommand("confirm");
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
}

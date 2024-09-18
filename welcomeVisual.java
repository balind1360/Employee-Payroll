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


public class EMgui{ 
	//create private variables that can be accesses in listener class
	private static JTextField textWindow;
	private static String id = "";
	private static JFrame iFrame;
	private static JFrame aFrame;
	private static JFrame eFrame;
	

	public static void main(String args[]){

		//declare & set size of initial frame
		iFrame = new JFrame("Employee Manager");
		iFrame.setSize(500,300);

		aFrame = new JFrame("Employee Manager: Admin");
		aFrame.setSize(700,700);

		eFrame = new JFrame("Employee Manager: Employee");
		eFrame.setSize(700,700);

		//create panel of components
		JPanel comp = new JPanel();
		//set layout for panel (box layout for Y axis)
		comp.setPreferredSize(new Dimension(500,300));
		//create boxlayout for JPanel comp 
			// allows components to be aligned in the center & stacked along Y axis (Page axis)
		BoxLayout boxlayout = new BoxLayout(comp, BoxLayout.PAGE_AXIS);
		comp.setLayout(boxlayout);


		//initialize label
		JLabel label = new JLabel("Enter Employee ID: ");
		label.setSize(200,200);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		comp.add(label);

		//added a text window for employees to type in their ID
		textWindow = new JTextField();
		textWindow.setHorizontalAlignment(JTextField.CENTER);
		textWindow.setBackground(Color.PINK);
		textWindow.setMaximumSize(new Dimension(250,40));
		//allow it to be editable in order to type id 
		textWindow.setEditable(true);
		comp.add(textWindow);

		//create an enter button & add to panel
		JButton ent = new JButton("enter"); 
		ent.setSize(200,200);
		ent.setAlignmentX(Component.CENTER_ALIGNMENT);
		comp.add(ent);

		//action listener
		ent.setActionCommand("Clicked");
		ent.addActionListener(new ButtonClickedListener());

		


		
//close frames with a window listener
		iFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

		//close admin frame
		aFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

		//close employee frame
		eFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
				//PROGRAM TO SAVE
			}
		});

		//add panel to the frame
		iFrame.add(comp);
		iFrame.pack();
		//set visible in order for frame to appear 
		iFrame.setVisible(true);


		//admin frames 
		if (aFrame.setVisible(false)){
			//create panel of components
			JPanel panel = new JPanel();
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

		}

	}

// create buttonClicked event listener 
	public static class ButtonClickedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			if(command.equals("Clicked")){
				//id stores the 
				id = textWindow.getText();
				if(id.equals("000100")){	// check for special admin code
					//open new admin view frame
					aFrame.setVisible(true);
					//close initial welcome frame
					iFrame.setVisible(false);
				} else{
					//open new employee view frame
					eFrame.setVisible(true);
					//close initial welcome frame
					iFrame.setVisible(false);
				}
			}
		}
	}
}

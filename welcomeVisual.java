/* 
Julia Heintz 
date created: 9.12.2024
last edited: 9.15.2024 

visual/GUI components for the employee-payroll 
- sign in screen 
- employee view 
  - see hours worked per date in the pay period 
  - calculate hours worked in that pay period 
- admin view 
  - shut down button (x in left corner) -> are you sure? screen 
  - view employee -> edit employee 
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
	//label for text
	private static JLabel label; 

	public static void main(String args[]){

		

		//declare & set size of initial frame
		JFrame iFrame = new JFrame("Employee Manager");
		iFrame.setSize(500,300);
		//set close
		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//create parent panel
		JPanel comp = new JPanel();
		//set layout for panel (box layout for Y axis)
		comp.setPreferredSize(new Dimension(500,300));
		BoxLayout boxlayout = new BoxLayout(comp, BoxLayout.PAGE_AXIS);
		comp.setLayout(boxlayout);



	

		//initialize label
		label = new JLabel("Enter Employee ID: ");
		label.setSize(200,200);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		comp.add(label);

		
		
		//added a text window for employees to type in their ID
		JTextField textWindow = new JTextField();
		textWindow.setHorizontalAlignment(JTextField.CENTER);
		textWindow.setBackground(Color.PINK);
		textWindow.setMaximumSize(new Dimension(250,40));
		textWindow.setEditable(true);
		comp.add(textWindow);

		

	
		//add panel to the frame
		iFrame.add(comp);
		iFrame.pack();

		// set visible in order for frame to appear 
		iFrame.setVisible(true);
	}
}

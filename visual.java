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

public class EMgui{ 
	//label for text
	private static JLabel label; 

	public static void main(String args[]){
		//declare & set size of initial frame
		JFrame iFrame = new JFrame("Employee Manager");
		iFrame.setSize(600,400);
		//set close
		iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//new panel
		JPanel comp = new JPanel();
		//set layout for panel (box layout for Y axis)
		BoxLayout boxlayout = new BoxLayout(comp, BoxLayout.Y_AXIS);
		comp.setLayout(boxlayout);

		//initialize label
		label = new JLabel("Enter Employee ID: ");
		label.setSize(100,100);
		
		//added a text window for employees to type in their ID
		JTextField textWindow = new JTextField();
		textWindow.setHorizontalAlignment(JTextField.CENTER);
		textWindow.setBackground(Color.PINK);
		textWindow.setSize(100,80);
		textWindow.setEditable(true);
		

		//add label & window to the panel 
		comp.add(label);
		comp.add(textWindow);
		//add panel to the frame
		iFrame.add(comp);

		// set visible in order for frame to appear 
		iFrame.setVisible(true);
	}
}

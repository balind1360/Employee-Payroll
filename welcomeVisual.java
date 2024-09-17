/* 
Julia Heintz 
date created: 9.12.2024
last edited: 9.17.2024 


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
	

	public static void main(String args[]){

		//declare & set size of initial frame
		JFrame iFrame = new JFrame("Employee Manager");
		iFrame.setSize(500,300);

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
		
		//close frame
		iFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});

		//add panel to the frame
		iFrame.add(comp);
		iFrame.pack();
		//set visible in order for frame to appear 
		iFrame.setVisible(true);
	}

// create buttonClicked event listener 
	public static class ButtonClickedListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String command = e.getActionCommand();
			if(command.equals("Clicked")){
				//id stores the 
				id = textWindow.getText();
				System.out.println(id);
			}
		}
	}
}

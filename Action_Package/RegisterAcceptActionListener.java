package action_package;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gui_package.Screen;
import user_package.*;


public class RegisterAcceptActionListener implements ActionListener {
	// Border
	private static Border border_error = BorderFactory.createLineBorder(Color.RED);
	// Create Private Variables
	private final JTextField[] t_input_array;
	private final int size;
	private final JFrame F;
	// Input Variables
	private String[] labels = { "Name: ", "Age: ", "Weight: ", "Height: ", "Active Id: ", "Email: " };
	private String n; // name
	private int a; // age
	private int w; // weight
	private int h; // height
	private int id; // active Id
	private String email; // email
	
	// Constructor
	public RegisterAcceptActionListener(final JTextField[] t_array, JFrame F) {
		super();
		this.size = t_array.length;
		this.t_input_array = new JTextField[size];
		for (int i = 0; i < size; i++) {
			this.t_input_array[i] = t_array[i];
		}
		this.F = F;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flagError = false;
		extractData(t_input_array);
		//TO-DO change to a switch statement for a better design
		//TO-DO make some pop ups showing what exactly the error is
		if (this.a <0 || this.a > 120) {
			t_input_array[1].setBorder(border_error);
			flagError = true;
		}
		if (this.w < 0 || this.w > 300) {
			t_input_array[2].setBorder(border_error);
			flagError = true;
		} 
		if (this.h < 0 || this.h > 500) {
			t_input_array[3].setBorder(border_error);
			flagError = true;
		} 
		if (this.id < 0 || this.id> 9) {
			t_input_array[4].setBorder(border_error);
			flagError = true;
		} 
		if (!flagError) {
			registerUser(); // Create a USER
			Screen.globalDatabase.printAllDetails();
			F.dispose();
			Screen.screen_home();
		}
	}// end action performed
	
	//Register a user
	private void registerUser(){
		User newUser = new User(n, a, w, h, id, email);
		Screen.globalDatabase.addUserToDatabase(newUser);;
	}

	// Extracts the data from the JTextField
	private void extractData(JTextField[] data) {
		/*
		 * I don't know if there is a way to loop this instead of hard coding
		 * it..?
		 */
		this.n = data[0].getText();
		this.a = tryParse(data[1].getText());
		this.w = tryParse(data[2].getText());
		this.h = tryParse(data[3].getText());
		this.id = tryParse(data[4].getText());
		this.email = data[5].getText();
	}

	// Prints the data [debugging purposes]
	public void printData() {
		int i = 0;
		while (i < size) {
			System.out.println(labels[i] + ": " + t_input_array[i].getText());
			i++;
		}
	}

	//Method for checking if the parsed Int is really an Integer
	private static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * TO-DO: Create a method for checking the name for invalid chars
	 * 
	 * */
}// end class

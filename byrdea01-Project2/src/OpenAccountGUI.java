import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */
public class OpenAccountGUI extends OrganizerGUI {
	/**
	 * This class is responsible for building a window in which the user
	 * can add the necessary information to open a new account. It will then 
	 * call the generateAccountNumber and generatePinNumber methods from the
	 * Lobby to generate the account and pin for the user. It then calls 
	 * the Information window to display the user's data. This window extends 
	 * the Organizer GUI window.
	 */
	private static final long serialVersionUID = 1L;
	Lobby newAccount = new Lobby();
	Customer current;
	Account account;
	private JLabel nameLabel;						//to reference name label
	private JLabel checkingDepositLabel;			//to reference deposit label for checking
	private JLabel savingsDepositLabel;				//to reference deposit label for savings
	private JButton inputCorrect;					//to reference the input is correct button
	private JButton openAccount;					//to reference the open account button
	static JTextField nameField;					//to reference the text field to enter customer name
	static JTextField checkingField;				//to reference the text field to enter the checking deposit amount
	static JTextField savingsField;					//to reference the text field to enter the savings deposit amount
	private JPanel centerPanel;						//to reference a panel in the border layout center location
	private JPanel centerPanelButtons;				//to reference a panel to hold the buttons in the center
	private JPanel centerPanelFields;				//to reference a panel to hold the fields in the center
	
	
	
	/*
	 * Constructor
	 */
	public OpenAccountGUI() {
			final int WINDOW_WIDTH = 550;
			final int WINDOW_HEIGHT = 250;
	
	/*
	 * Set the window's size
	 */
	setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	/*
	 * Add a grid layout manager to the content pane.
	 */
	GridLayout gridLay1 = new GridLayout(3, 2, 0, 10);
	GridLayout gridLay2 = new GridLayout(1, 2, 50, 10);
	
	/*
	 * Build panel and add to the content frame.
	 */
	buildPanel();
	centerPanelFields.setLayout(gridLay1);
	centerPanelButtons.setLayout(gridLay2);
	add(centerPanel, BorderLayout.CENTER);
	
	/*
	 * Display the window
	 */

	setVisible(true);
	
}
	private void buildPanel() {
		/*
		 * Create the message labels for the user to correctly enter their data.
		 */
		nameLabel = new JLabel("Enter your name here -------------->");
		checkingDepositLabel = new JLabel("Enter deposit to checking -------->");
		savingsDepositLabel = new JLabel("Enter deposit to savings ---------->");
		
		/*
		 * Create text fields for the user data entry
		 */
		nameField = new JTextField(20);
		nameField.setEditable(true);
		checkingField = new JTextField(20);
		checkingField.setEditable(true);
		savingsField = new JTextField(20);
		savingsField.setEditable(true);
		
		/*
		 * Create buttons for user to verify data and open account
		 */
		inputCorrect = new JButton("All input correct");
		inputCorrect.addActionListener(new InputButtonListener());
		openAccount = new JButton("Open my account");
		openAccount.setEnabled(false);
		openAccount.addActionListener(new OpenButtonListener());
		
		new JPanel();
		centerPanel = new JPanel();
		centerPanelButtons = new JPanel();
		centerPanelFields = new JPanel();
		
		/*
		 * Add components to JPanels
		 */
		centerPanelFields.add(nameLabel);
		centerPanelFields.add(nameField);
		centerPanelFields.add(checkingDepositLabel);
		centerPanelFields.add(checkingField);
		centerPanelFields.add(savingsDepositLabel);
		centerPanelFields.add(savingsField);
		centerPanelButtons.add(inputCorrect);
		centerPanelButtons.add(openAccount);
		centerPanel.add(centerPanelFields);
		centerPanel.add(centerPanelButtons);
		
		
	}
	
	private class InputButtonListener implements ActionListener {
		/*
		 * Button listener that activates the Open Account button when
		 *  the "All Input Correct" button is pressed.
		 */
		public void actionPerformed (ActionEvent e) {
			e.getActionCommand();
			openAccount.setEnabled(true);
		}
	}
	
	private class OpenButtonListener implements ActionListener {
		/*
		 * Actionlistener that opens the new account for the user. It
		 * determines the client's first and last name and calls the information
		 * window to display the name and other information.
		 */
		private String name;

		public void actionPerformed(ActionEvent e) {
			name = nameField.getText();
			StringTokenizer strTok = new StringTokenizer(name);
			Lobby.firstName = strTok.nextToken();
			Lobby.lastName = strTok.nextToken();
			
			Information infoScreen = new Information();
			
			setVisible(false);
		}
		
		}
	}


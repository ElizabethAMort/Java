import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */
public class Information extends JFrame{
	/**
	 * This class contains the information used to build a GUI window for the
	 * user to read the information they entered when they opened a new account.
	 * It displays their name, pin number, account number, checking balance, and 
	 * savings balance. A button at the bottom of the window allows the program 
	 * to return to the lobby. The user is thanked for their business and the next
	 * customer is then taken. 
	 */
	
	Lobby bankLobby = new Lobby();
	private static final long serialVersionUID = 1L;
	private JLabel newMessage; 							//to reference a welcome message
	private JLabel accountName;							//to reference an account name label
	private JLabel accountNumber;						//to reference an account number label
	private JLabel pinCode;								//to reference a pin number label
	private JLabel checkingBalance;						//to reference the checking balance label
	private JLabel savingsBalance;						//to reference the savings balance label
	private JLabel returnLabel;							//to reference the return to lobby label
	static JTextField nameField;						//to reference the name text field
	protected static JTextField numberField;			//to reference the account number text field
	protected static JTextField pinField;				//to reference the pin code text field
	private JTextField checkingField;					//to reference the checking balance text field
	private JTextField savingsField;					//to reference the savings balance text field
	private JButton returnButton;						//to reference the return to lobby button
	private JPanel northPanel;							//to reference the north panel in the borderlayout
	private JPanel centerPanel;							//to reference the center panel in the borderlayout
	private JPanel southPanel;							//to reference the south panel in the borderlayout
	private JPanel eastPanel;							//to reference the east panel in the borderlayout
	private JPanel westPanel;							//to reference the west panel in the borderlayout
	private JLabel eastMessage;							//empty message label on the east panel
	private JLabel westMessage;							//empty message label on the west panel
	protected Customer current;							//variable to reference the current customer
	public Customer[] bankAccount;						//variable to reference the customer's bank account array
	public static int checkingAmount;					//variable to reference the customer's checking amount
	public static int savingsAmount;					//variable to reference the customer's savings amount
	DecimalFormat df = new DecimalFormat("#,###,###.00");	//decimal formatter for the monetary values
	
	
	
	
	
	public Information() {
		final int WINDOW_WIDTH = 500;					//constant that determines the width of the window
		final int WINDOW_HEIGHT = 250;					//constant that determines the height of the window
		
		/*
		 * Set the title of the window
		 */
		setTitle("Welcome to the Programmer's Bank!");
		
		/*
		 * Set the window's size
		 */
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		/*
		 * Determine what happens when the close button is pressed
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Add a grid layout manager for the center panel
		 */
		GridLayout gridLay1 = new GridLayout(5, 2, 0, 10);
		GridLayout gridLay2 = new GridLayout(1, 2, 0, 10);
		
		/*
		 * Build the panel and add to the content pane
		 */
		buildPanel();
		add(northPanel, BorderLayout.NORTH);
		centerPanel.setLayout(gridLay1);
		add(centerPanel, BorderLayout.CENTER);
		southPanel.setLayout(gridLay2);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
		add(westPanel, BorderLayout.WEST);
		
		
		/*
		 * Set window to visible
		 */
		setVisible(true);
		
	}

	private void buildPanel() {
		/*
		 * Create message label
		 */
		newMessage = new JLabel("We are happy to serve you!");
		eastMessage = new JLabel("               ");
		westMessage = new JLabel("               ");
		
		/*
		 * Create the labels for the text fields
		 */
		accountName = new JLabel("New account holder ------->");
		accountNumber = new JLabel("Your account number ------->");
		pinCode = new JLabel("Your PIN code -------->");
		checkingBalance = new JLabel("Checking Account Balance ------->");
		savingsBalance = new JLabel("Savings Account Balance ------->");
		returnLabel = new JLabel("Click and return to the Lobby ------->");
		
		/*
		 * Create the text fields for the data display
		 */
		nameField = new JTextField(10);
		String fullName = Lobby.firstName.concat(" " + Lobby.lastName);
		nameField.setText(fullName);
		String acctNum = Integer.toString(Lobby.generateAccountNumber());
		numberField = new JTextField(10);
		numberField.setText(acctNum);
		Lobby.accountNumber = Integer.parseInt(acctNum);
		pinField = new JTextField(10);
		String pinNum = Integer.toString(Lobby.generatePin());
		pinField.setText(pinNum);
		Lobby.pinCode = Integer.parseInt(pinNum);
		checkingField = new JTextField(10);
		String checkingAmount = OpenAccountGUI.checkingField.getText();
		checkingField.setText(checkingAmount);
		Lobby.checkingAmount = Double.parseDouble(checkingAmount);
		savingsField = new JTextField(10);
		String savingsAmount = OpenAccountGUI.savingsField.getText();
		savingsField.setText(savingsAmount);
		Lobby.savingsAmount = Double.parseDouble(savingsAmount);
		
		try {
			Lobby.createCustomerFile();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * Create the button to return to the lobby
		 */
		returnButton = new JButton("RETURN");
		returnButton.addActionListener(new ButtonListener());

		/*
		 * Create panels for components to reside on
		 */
		northPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel = new JPanel();
		eastPanel = new JPanel();
		westPanel = new JPanel();
		
		/*
		 * Add components to their panels
		 */
		northPanel.add(newMessage);
		centerPanel.add(accountName);
		centerPanel.add(nameField);
		centerPanel.add(accountNumber);
		centerPanel.add(numberField);
		centerPanel.add(pinCode);
		centerPanel.add(pinField);
		centerPanel.add(checkingBalance);
		centerPanel.add(checkingField);
		centerPanel.add(savingsBalance);
		centerPanel.add(savingsField);
		southPanel.add(returnLabel);
		southPanel.add(returnButton);
		eastPanel.add(eastMessage);
		westPanel.add(westMessage);
	}
	/*
	 * Button listener for the "Return" button. This button closes the 
	 * current window and calls the next customer. It also sets the new checking and
	 * savings amounts for the customer and updates the daily total amount.
	 */
	public class ButtonListener implements ActionListener {
		
		public void actionPerformed (ActionEvent e) {
			Customer.bankAccount[0] = Account.getCheckingAmount();
			Customer.bankAccount[1] = Account.getSavingsAmount();
			Customer.bankAccount[0] = Lobby.checkingAmount;
			Customer.bankAccount[1] = Lobby.savingsAmount;
			try {
				Lobby.createCustomerFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
			Lobby.dailyTotalNew = Lobby.dailyTotalNew + Double.parseDouble(OpenAccountGUI.checkingField.getText()) + 
				Double.parseDouble(OpenAccountGUI.savingsField.getText());
			Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
			JOptionPane.showMessageDialog(null, "Thank you for your business!");
			Lobby.nextCustomer();
			
		}
		public void setCustomer(Customer current) {
			current.getClass();
		}
	}
	public static JTextField getNumberField() {
		return numberField;
	}

	public static void setNumberField(JTextField numberField) {
		Information.numberField = numberField;
	}

	public static JTextField getPinField() {
		
		return pinField;
	}

	public void setPinField(JTextField pinField) {
		Information.pinField = pinField;
	}
}

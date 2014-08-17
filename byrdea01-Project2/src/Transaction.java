import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */
public class Transaction extends OrganizerGUI {
	/**
	 * This class is responsible for everything regarding a transaction the user may want to 
	 * perform. This included depositing money, withdrawing money, and transferring money
	 * from one account to another. 
	 */
	private static final long serialVersionUID = 1L;
		Lobby newAccount = new Lobby();
		private JLabel messageLabel; 					//to reference instructional message to the user
		private JLabel nameLabel;						//to reference name label
		private JLabel pinLabel;						//to reference pin code label
		private JLabel clickReady;						//to reference a label for the click when ready button
		private JLabel transAmount;						//to reference a label for the amount of transaction field
		private JButton readyButton;					//to reference the ready button
		static JTextField nameField;					//to reference the text field to enter customer name
		static JPasswordField pinField;					//to reference the text field to enter the customer pin
		static JTextField transactionField;				//to reference the text field to enter the transaction amount
		private JButton amountCorrect;					//to reference the amount correct button
		private JLabel transactionLabel;				//to reference the label for the transaction type and subaccount
		private JRadioButton depositButton;				//to reference the radio button for the deposit transaction
		private JRadioButton withdrawButton;			//to reference the radio button for the withdraw transaction
		private JRadioButton transferButton;			//to reference the radio button for the transfer transaction
		private JRadioButton checkingButton;			//to reference the radio button for the checking subaccount
		private JRadioButton savingsButton;				//to reference the radio button for the savings subaccount
		private ButtonGroup transactionGroup;			//to reference the button group for the transaction type
		private ButtonGroup accountGroup;				//to reference the button group for the account type		
		private JPanel northPanel;						//to reference a panel in the border layout north location
		private JPanel centerPanel;						//to reference a panel in the border layout center location
		private JPanel centerPanelTop; 					//to reference a panel to hold the uppermost grid in the center
		private JPanel centerPanelMiddle;				//to reference a panel to hold the middle grid in the center
		private JPanel centerPanelMidButton;			//to reference a panel to hold the button in the grid in the center
		private JPanel centerPanelBottom;				//to reference a panel to hold the bottom grid in the center
		private JPanel messagePanel;					//to reference a panel to hold the instruction message labels
		private JPanel transactionPanel;				//to reference a panel to hold the transaction type buttons
		private JPanel accountPanel;					//to reference a panel to hold the account type buttons
		private JPanel centerPanelCombined;				//to reference a panel to hold the transaction panel and the account panel
		private static JLabel toCheckingLabel;			//label to alert user where money will be transferred
		private static JLabel toSavingsLabel;			//label to alert user where money will be transferred
		DecimalFormat df = new DecimalFormat("$#,###,###.00");	//decimal formatter for monetary amounts
		
		/*
		 * Constructor
		 */
		public Transaction() {
				final int WINDOW_WIDTH = 600;
				final int WINDOW_HEIGHT = 400;
		
		/*
		 * Set the window's size
		 */
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		/*
		 * Add a grid layout manager to the content pane.
		 */
		GridLayout gridLay1 = new GridLayout(3, 2, 5, 5);
		GridLayout gridLay2 = new GridLayout(2, 0);
		GridLayout gridLay4 = new GridLayout(1, 3, 20, 20);
		GridLayout gridLay5 = new GridLayout(3, 0);
		GridLayout gridLay6 = new GridLayout(2, 0);
		GridLayout gridLay7 = new GridLayout(4, 1, 5, 5);
		FlowLayout flowLayout = new FlowLayout();
		
		
		/*
		 * Build panel and add to the content frame.
		 */
		buildPanel();
		centerPanelTop.setLayout(gridLay1);
		centerPanelMiddle.setLayout(flowLayout);
		centerPanelMidButton.setLayout(flowLayout);
		centerPanelCombined.setLayout(gridLay2);
		transactionPanel.setLayout(gridLay5);
		accountPanel.setLayout(gridLay6);
		centerPanelBottom.setLayout(gridLay4);
		centerPanel.setLayout(gridLay7);
		add(centerPanel, BorderLayout.CENTER);

		
		
		/*
		 * Display the window
		 */
		setVisible(true);
		
	}
		private void buildPanel() {
			/*
			 * Create a message label to provide instructions to the user
			 */
			messageLabel = new JLabel("Enter information for the required process.");
			clickReady = new JLabel("Click when ready.");
			transactionLabel = new JLabel("Select the transaction type: -->");
			
			/*
			 * Create the message labels for the user to correctly enter their data.
			 */
			nameLabel = new JLabel("Enter your name here -------------->");
			pinLabel = new JLabel("Enter your pin code here -------->");
			clickReady = new JLabel("Click when ready ---------->");
			transAmount = new JLabel("Enter amount of transaction -------->");
			toCheckingLabel  = new JLabel("to Checking");
			toSavingsLabel = new JLabel("to Savings");
			
			/*
			 * Create text fields for the user data entry
			 */
			nameField = new JTextField(20);
			nameField.setEditable(true);
			pinField = new JPasswordField(20);
			pinField.setEditable(true);
			transactionField = new JTextField(20);
			transactionField.setEditable(false);
			
			/*
			 * Create radio buttons for transaction type and sub-account. Also create
			 * the radio button groups for transaction type and sub-accounts. Add action listener
			 * to determine which buttons were selected by the user.
			 */
			depositButton = new JRadioButton("Deposit");
			depositButton.setEnabled(false);
			withdrawButton = new JRadioButton("Withdraw");
			withdrawButton.setEnabled(false);
			transferButton = new JRadioButton("Transfer");
			transferButton.setEnabled(false);
			checkingButton = new JRadioButton("Checking");
			checkingButton.setEnabled(false);
			savingsButton = new JRadioButton("Savings");
			savingsButton.setEnabled(false);
			
			transactionGroup = new ButtonGroup();
			accountGroup = new ButtonGroup();
			transactionGroup.add(depositButton);
			transactionGroup.add(withdrawButton);
			transactionGroup.add(transferButton);
			accountGroup.add(checkingButton);
			accountGroup.add(savingsButton);
			
			depositButton.addActionListener(this);
			withdrawButton.addActionListener(this);
			transferButton.addActionListener(this);
			checkingButton.addActionListener(this);
			savingsButton.addActionListener(this);
			
			/*
			 * Create buttons for user to verify data and open account. 
			 */
			
			readyButton = new JButton("Ready");
			readyButton.addActionListener(new ReadyButtonListener());
			amountCorrect = new JButton("Amount correct");
			amountCorrect.addActionListener(new AmountButtonListener());
			
			
			new JPanel();
			centerPanel = new JPanel();
			northPanel = new JPanel();
			centerPanelTop = new JPanel();
			centerPanelMiddle = new JPanel();
			centerPanelMidButton = new JPanel();
			transactionPanel = new JPanel();
			accountPanel = new JPanel();
			centerPanelBottom = new JPanel();
			messagePanel = new JPanel();
			centerPanelCombined = new JPanel();
			transactionPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
			accountPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
			centerPanelTop.setBorder(BorderFactory.createLineBorder(Color.black, 3));
			centerPanelMiddle.setBorder(BorderFactory.createLineBorder(Color.magenta, 2));
			
			/*
			 * Add components to JPanels
			 */
			messagePanel.add(messageLabel);
			northPanel.add(messagePanel);
			centerPanelTop.add(nameLabel);
			centerPanelTop.add(nameField);
			centerPanelTop.add(pinLabel);
			centerPanelTop.add(pinField);
			centerPanelTop.add(clickReady);
			centerPanelTop.add(readyButton);
			centerPanelMiddle.add(transAmount);
			centerPanelMiddle.add(transactionField);
			centerPanelMidButton.add(amountCorrect);
			centerPanelCombined.add(centerPanelMiddle);
			centerPanelCombined.add(centerPanelMidButton);
			transactionPanel.add(depositButton);
			transactionPanel.add(transferButton);
			transactionPanel.add(withdrawButton);
			accountPanel.add(checkingButton);
			accountPanel.add(toSavingsLabel);
			accountPanel.add(savingsButton);
			accountPanel.add(toCheckingLabel);
			centerPanelBottom.add(transactionLabel);
			centerPanelBottom.add(transactionPanel);
			centerPanelBottom.add(accountPanel);
			centerPanel.add(centerPanelTop);
			centerPanel.add(centerPanelMiddle);
			centerPanel.add(centerPanelMidButton);
			centerPanel.add(centerPanelBottom);
			
			
			
		}
		/*
		 * Ready button allows the program to determine if the entered
		 * data can be found in the arrayList. If it can, the transaction
		 * can proceed. If not, data should be entered again.
		 */
		private class ReadyButtonListener implements ActionListener {
			
			public void actionPerformed (ActionEvent e) {
				e.getActionCommand();
				
				try {
					Lobby.readCustomerFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Lobby.identifier();
		}
		}
			
		private class AmountButtonListener implements ActionListener {
				/*
				 * Actionlistener that determines when the "Amount correct" button
				 * has been pressed. It then activates the radiobuttons for 
				 * selecting the transaction type and the account type.
				 */
			public void actionPerformed(ActionEvent e) {
				e.getActionCommand();
				Lobby.transactionAmount = Double.parseDouble(Transaction.transactionField.getText());
				depositButton.setEnabled(true);
				withdrawButton.setEnabled(true);
				transferButton.setEnabled(true);
				checkingButton.setEnabled(true);
				savingsButton.setEnabled(true);
			}
		}
		
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Determine which radio button was pushed and perform
				 * the action associated with that button. Also update the
				 * daily totals and the users account balance(s). Also displays information
				 * about the transaction and then calls the next customer.
				 */
				if (e.getSource() == checkingButton) {
					if (depositButton.isSelected()) {
						Lobby.checkingAmount = Lobby.checkingAmount + Lobby.transactionAmount;
						Customer.bankAccount[0] = Account.setCheckingAmount(Lobby.checkingAmount);
						Lobby.checkingAmount = Customer.bankAccount[0];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You deposited " + df.format(Lobby.transactionAmount) +
								" into checking. " + "\n" + "Your current balance is " + df.format(Lobby.checkingAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
					}
					if (withdrawButton.isSelected()) {
						Lobby.checkingAmount = Lobby.checkingAmount - Lobby.transactionAmount;
						Customer.bankAccount[0] = Account.setCheckingAmount(Lobby.checkingAmount);
						Lobby.checkingAmount = Customer.bankAccount[0];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You withdrew " + df.format(Lobby.transactionAmount) +
								" from checking. " + "\n" + "Your current balance is " + df.format(Lobby.checkingAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
					}
					if (transferButton.isSelected()) {    //transferred from checking to savings
						Lobby.checkingAmount = Lobby.checkingAmount - Lobby.transactionAmount;
						Lobby.savingsAmount = Lobby.savingsAmount + Lobby.transactionAmount;
						Customer.bankAccount[0] = Account.setCheckingAmount(Lobby.checkingAmount);
						Customer.bankAccount[1] = Account.setSavingsAmount(Lobby.savingsAmount);
						Lobby.checkingAmount = Customer.bankAccount[0];
						Lobby.savingsAmount = Customer.bankAccount[1];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You transferred " + df.format(Lobby.transactionAmount) +
								" from checking. " + "\n" + "Your current balance is: " + "\n" + "Checking: " + 
								df.format(Lobby.checkingAmount) + "\n" + "Savings: " + df.format(Lobby.savingsAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
			}
			}
				if (e.getSource() == savingsButton) {
					if (depositButton.isSelected()) {
						Lobby.savingsAmount = Lobby.savingsAmount + Lobby.transactionAmount;
						Customer.bankAccount[1] = Account.setSavingsAmount(Lobby.savingsAmount);
						Lobby.savingsAmount = Customer.bankAccount[1];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You deposited " + df.format(Lobby.transactionAmount) +
								" into savings. " + "\n" + "Your current balance is " + df.format(Lobby.savingsAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
					}
					if (withdrawButton.isSelected()) {
						Lobby.savingsAmount = Lobby.savingsAmount - Lobby.transactionAmount;
						Customer.bankAccount[1] = Account.setSavingsAmount(Lobby.savingsAmount);
						Lobby.savingsAmount = Customer.bankAccount[1];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You withdrew " + df.format(Lobby.transactionAmount) +
								" from savings. " + "\n" + "Your current balance is " + df.format(Lobby.savingsAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
					}
					if (transferButton.isSelected()) {    //transferred from savings to checking
						Lobby.checkingAmount = Lobby.checkingAmount + Lobby.transactionAmount;
						Lobby.savingsAmount = Lobby.savingsAmount - Lobby.transactionAmount;
						Customer.bankAccount[0] = Account.setCheckingAmount(Lobby.checkingAmount);
						Customer.bankAccount[1] = Account.setSavingsAmount(Lobby.savingsAmount);
						Lobby.checkingAmount = Customer.bankAccount[0];
						Lobby.savingsAmount = Customer.bankAccount[1];
						try {
							Lobby.createCustomerFile();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setVisible(false);
						Customer.toString(Lobby.customerList, Lobby.firstName, Lobby.lastName, Lobby.accountNumber, 
								Lobby.pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
						JOptionPane.showMessageDialog(null, "You transferred " + df.format(Lobby.transactionAmount) +
								" from savings. " + "\n" + "Your current balance is: " + "\n" + "Checking: " + 
								df.format(Lobby.checkingAmount) + "\n" + "Savings: " + df.format(Lobby.savingsAmount));
						Lobby.dailyTotalTransactions = Lobby.dailyTotalTransactions + Double.parseDouble(Transaction.transactionField.getText());
						Lobby.addToDailyTotal(Lobby.transactionAmount, Lobby.newAccountAmount);
						Lobby.nextCustomer();
				}
				
					
						
			
					}
				}
				
				
}


	
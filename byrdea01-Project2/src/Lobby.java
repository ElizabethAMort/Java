import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */


/**
 * Class Lobby drives the rest of the classes. It contains the main method and most of the methods that work on 
 * the clients' data. You may notice that no .txt files are associated with this package. The files will be created 
 * as clients are added to the 'database'. Those files will then be able to be accessed as long as the pin number
 * is recorded and entered correctly. Users are able to open new accounts and then do transactions on those accounts such
 * as make deposits, withdrawals, or transfers. I opted not to use the "close account" option. I also chose not to
 * do the money market option. As users make new accounts or complete transactions, the total for all transactions is
 * tracked and will be displayed when the Lobby is closed. This program can only handle transactions up to 
 * $1,000,000.
 */
public class Lobby {
	
	/*
	 * Create variables and initialize them as necessary.
	 */
	protected static String firstName;
	protected static String lastName;
	protected static int accountNumber;
	protected static int pinCode;
	private static Frame frame;
	protected static ArrayList<Customer> customerList = new ArrayList<Customer>();
	protected static Information infoScreen;
	protected static double dailyTotal = 0;
	protected static double checkingAmount;
	protected static double savingsAmount;
	protected static double checkingBalance;
	protected static double savingsBalance;
	protected static double transactionAmount = 0;
	protected static double newAccountAmount = 0;
	protected static Transaction newTransaction;
	protected static double dailyTotalTransactions = 0;
	protected static double dailyTotalNew = 0;
	static DecimalFormat df = new DecimalFormat("$#,###,###.00");
	
	public static void main(String[] args) throws IOException   {
		
		
		/*
		 * Create message box that opens the lobby and then calls the next customer method.
		 */
		final JDialog dialog = new JDialog(frame,
                "Welcome to Programmer's Bank",
                true);
		
		int n = JOptionPane.showConfirmDialog(
                frame, "Ready to open the Lobby? " + "\n" +	" Click 'No' to terminate the program.", 
                 "Welcome to Programmer's Bank", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
        if (n == JOptionPane.YES_OPTION) {
		nextCustomer();
		}
        if (n == JOptionPane.NO_OPTION) {
        	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        } 
        
	}
		/*
		 * This method will create a customer file when a new account is opened. 
		 * It will also overwrite checking and savings amounts when a transaction 
		 * has occurred. 
		 */
	public static void createCustomerFile() throws FileNotFoundException {
		String clientFile = Lobby.firstName.concat(" " + Lobby.lastName);
		PrintWriter outputFile = new PrintWriter(clientFile);
		outputFile.print(firstName); 				
		outputFile.print(" " + lastName);
		outputFile.print(" " + Lobby.accountNumber);
		outputFile.print(" " + Lobby.pinCode);
		outputFile.print(" " + Lobby.checkingAmount);
		outputFile.print(" " + Lobby.savingsAmount);
		outputFile.close();	 	
	}
	/*
	 * This method reads a customers file and then adds that data into an
	 * arraylist. It also displays the customer data on the console using 
	 * the toString method.
	 */
	public static void readCustomerFile() throws IOException {
		
		String textFile;
		textFile = Transaction.nameField.getText();
		Scanner inFile = new Scanner(new File(textFile));	
		firstName = inFile.next();
		lastName = inFile.next();
		accountNumber = inFile.nextInt();
		pinCode = inFile.nextInt();
		checkingAmount = inFile.nextDouble();
		savingsAmount = inFile.nextDouble();
		
		customerList.add(new Customer(firstName, lastName, accountNumber,
				pinCode, Lobby.checkingAmount, Lobby.savingsAmount));

		Customer.toString(customerList, firstName, lastName, accountNumber, pinCode, Lobby.checkingAmount, Lobby.savingsAmount);
		inFile.close();
		}
	
		/*
		 * The nextCustomer method asks if the next customer is to be accepted. It opens 
		 * the OptionsGUI window if yes is selected or calls the closeLobby method if 
		 * no is selected.
		 */
		public static void nextCustomer() {
			final JDialog dialog = new JDialog(frame,
	                "Welcome to Programmer's Bank",
	                true);
			
			int n = JOptionPane.showConfirmDialog(
	                frame, "Ready for the next customer? " + "\n" +	" Click 'No' to terminate the program.", 
	                 "Welcome to Programmer's Bank", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
	        if (n == JOptionPane.YES_OPTION) {
	        	OptionsGUI options = new OptionsGUI();
	        }
			if (n == JOptionPane.NO_OPTION) {
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				closeTheLobby();
			}
			
		}
		/*
		 * The openAccount method instantiates a new OpenAccountGUI window
		 * which allows the user to add data necessary for opening a new
		 * account.
		 */
		public static void openAccount() throws IOException {
			OpenAccountGUI newAccount = new OpenAccountGUI();
			
		}
		/*
		 * The addToDailyTotal method adds the totals for transactions and new accounts
		 * to determine how much money the bank handled during the day.
		 */
		public static void addToDailyTotal(double transactionAmount, double newAccountAmount) {
			dailyTotal = dailyTotalTransactions + dailyTotalNew;	
		}
		/*
		 * I chose not to do this option. Instead, if the user selects "Close Account", they will get
		 * a message saying that no accounts may be closed at this time.
		 */
		public static void closeAccount() {
			JOptionPane.showMessageDialog(null, "I'm sorry. We are unable to close any account at this time.");
			nextCustomer();
			
			/**
			 * I was going to attempt to do the close account method as well, but couldn't get it to work. 
			 * This is what I had put together so far.
			 */
//			String input = JOptionPane.showInputDialog("Enter your name as it appears on your account: ");
//			 String fileName = input;
//
//			 try {
//			      // Construct a File object for the file to be deleted.
//			      File target = new File(fileName);
//
//			      if (!target.exists()) {
//			    	  JOptionPane.showMessageDialog(null, "File " + fileName + " not found.");
//			    	  Lobby.nextCustomer();
//			        return;
//			      }
//			 
//			      // Attempt to delete client's file.
//			      if (target.delete()) {
//			        System.err.println("** Deleted " + fileName + " **");
//			     }
//			      else {
//			        System.err.println("Failed to delete " + fileName);
//			      }
//			 }
//			     catch (SecurityException e) {
//			      System.err.println("Unable to delete " + fileName + "("
//			          + e.getMessage() + ")");   
//			     }	 
		}
		
		/*
		 * This method tests the pin entered by the user to determine if it
		 * matches the pin generated for the user when their account was opened.
		 * If it matches, the transaction may continue. If not, they may try again.
		 */
		public static void identifier() {
			char[] pin = Transaction.pinField.getPassword();
			String pTest = new String(pin);
			int pinTest = Integer.parseInt(pTest);
			int i = 0;
			
			if (pinTest != Lobby.pinCode) {	
				
				while (i < 2) {
				JOptionPane.showMessageDialog(null, "Data not found. Please try again.");
				Transaction.nameField.setText(null);
				Transaction.pinField.setText(null);
				identifier();
				i++;
			}
				JOptionPane.showMessageDialog(null, "Data not found. Please contact customer service.");
				Lobby.nextCustomer();
			}
			else {
				Transaction.transactionField.setEditable(true);	
			}
			
		}	
		/*
		 * This method opens the Transaction window which allows the user to enter
		 * their name and pin number to be tested by the identifier method. If it passes
		 * the identifier method, their transaction may continue.
		 */
		public static void accessAccount() {
			Transaction newTransaction = new Transaction();
				
		}
		/*
		 * This method closes the lobby for the day. It displays a message saying that
		 * the lobby is now closes and then it will display the daily total for the day.
		 */
		public static void closeTheLobby() {
			JOptionPane.showMessageDialog(null, "The lobby is now closed.");
			JOptionPane.showMessageDialog(null, "Daily total is: " + df.format(dailyTotal) + ".");
		}
		/*
		 * This method generates an account number for a new client.
		 */
		public static int generateAccountNumber() {
			int accountNumber;
			Random generator = new Random();  
			generator.setSeed(System.currentTimeMillis());  
			   
			accountNumber = generator.nextInt(999999999) + 100000000;  
			if (accountNumber < 100000000 || accountNumber > 999999999) {  
			accountNumber = generator.nextInt(999999999) + 100000000;  
			while (accountNumber > 100000000 || accountNumber < 999999999) { 
				accountNumber = generator.nextInt(999999999) + 100000000;  
			}  
			} 
			return accountNumber;  
			}  
		/*
		 * This method generates a pin number for a new client.
		 */
		public static int generatePin() {
			    int pinCode;
				Random generator = new Random();  
				generator.setSeed(System.currentTimeMillis());  
				   
				pinCode = generator.nextInt(9999) + 1000;  
				if (pinCode < 10000 || pinCode > 999) {  
				pinCode = generator.nextInt(9999) + 1000;  
				while (pinCode > 10000 || pinCode < 999) { 
					pinCode = generator.nextInt(9999) + 1000;  
				}  
				} 
				return pinCode;  
				} 
		}
			


import java.text.DecimalFormat;
import java.util.ArrayList;

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */

public class Customer {
	/**
	 * This class contains the variables and the getters and setters necessary for a new customer.
	 * This includes the customer's first and last name, their account number, their pin number, and also 
	 * their checking and savings balance. It also includes the toString method necessary to display
	 * this data on the console.
	 */
	protected OpenAccountGUI newAccount;
	protected static String firstName;
	protected static String lastName;
	static int accountNumber;
	static int pinCode;
	protected static double checkingBalance;
	protected static double savingsBalance;
	static DecimalFormat df = new DecimalFormat("#,###,###.00");
	protected static double[] bankAccount = new double[2];
	
	public Customer(String firstName, String lastName, int accountNumber, int pinCode, 
			double checkingAmount, double savingsAmount) {
		
	}
	
	public static String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		Lobby.firstName = firstName;
	}
	public static String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		Lobby.lastName = lastName;
	}

	public static int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		Customer.accountNumber = accountNumber;
	}
	public static int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		Customer.pinCode = pinCode;
	}
	public static void toString(ArrayList<Customer> customerList, String firstName, String lastName, int accountNumber, 
			int pinCode, double checkingAmount, double savingsAmount) {
		System.out.println("Client Name: " + firstName + " " + lastName + " Account Number: " + accountNumber + " PIN number: " 
				+ pinCode + " Checking Balance: " + df.format(Lobby.checkingAmount) + " Savings Balance: " +
				df.format(Lobby.savingsAmount));  
	}
	public void setInformation(Information infoScreen) {
		infoScreen.getClass();
	}

	}

/*
 * Elizabeth Mort
 * CS 16100 Summer 2011
 * Project 2
 */

public class Account {
	/*
	 * This class contains the variables and the getters and setters necessary
	 * to record the amount in a given client's accounts. 
	 */
	
	static double checkingAmount;
	static double savingsAmount;
	
	public Account(double checkingAmount, double savingsAmount) {
		
	}

	public static double getCheckingAmount() {
		return checkingAmount;
	}

	public static double setCheckingAmount(double checkingAmount) {
		Account.checkingAmount = Lobby.checkingAmount;
		return Lobby.checkingAmount;
	}

	public static double getSavingsAmount() {
		return savingsAmount;
	}

	public static double setSavingsAmount(double savingsAmount) {
		 Account.savingsAmount = Lobby.savingsAmount;
		 return Lobby.savingsAmount;
	}


	
	
}

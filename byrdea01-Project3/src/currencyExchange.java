import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;


public class currencyExchange {
	//assign constants
	static final double COMMISSION_RATE = 0.012;
	static Scanner kb = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#,##0.00");
	static final char SENTINEL = 'x';
	
	/*
	 * Elizabeth Mort
	 * CS 160 Spring 2011
	 * Project 3: Currency Exchange
	 * 
	 * Description of project: This project will read input from the user (a foreign currency type and amount), 
	 * use various methods to examine the input, choose the correct formula based on a buy or sell transaction 
	 * (user determined)and then will output the information to a file. The buy/sell rates are determined by 
	 * data contained in a file which will be read and assigned by the main method. This class also contains
	 * methods for verifying that proper input was entered, determining which transaction should be performed 
	 * (buy or sell), performing the buy or sell currency calculation, and displaying output data (number of US 
	 * dollars the transaction will cost) to the user.
	 */
	
	/*
	 * The following method sends data to the output file.
	 */
	public static void logTransaction(PrintWriter outputFile, char currency, double amount, char transactionType, 
			double dollars) throws IOException {
		
		outputFile.println(currency + " " + (int)amount + " " + transactionType + " " + dollars);
		
	}
	/*
	 * The following method determines whether a buy or a sell equation is appropriate to use. It
	 * then returns the dollarAmount.
	 */
	public static double doTransaction (char currency, double amount, char transactionType, 
			double buyRateC, double buyRateP, double buyRateE, double sellRateC, double sellRateP, 
			double sellRateE) throws IOException{
		double dollarAmount = 0;
	switch (transactionType) {
	case 'b':
		dollarAmount = buyCurrency(currency, amount, buyRateC, buyRateP, buyRateE);
		break;
	case 's': 
		dollarAmount = sellCurrency(currency, amount, sellRateC, sellRateP, sellRateE);
		break;
		}
	return dollarAmount;
}
	/*
	 * The following method determines the validity of the user-entered currency type.
	 * It will either invoke doTransaction method or prompt the user to enter a proper
	 * currency type
	 */
	public static boolean isValidCurrency(char currency){
		boolean isValid = true;
		switch (currency) {
		case 'C':
			isValid = true;
			break;
		case 'c':
			isValid = true;
			break;
		case 'P': 
			isValid = true;
			break;
		case 'p':
			isValid = true;
			break;
		case 'E':
			isValid = true;
			break;
		case 'e':
			isValid = true;
			break;
			default:
				isValid = false;
		}	
		return isValid; 
	}
	
	/*
	 *The following method displays the total buy and total sell information. 
	 */
	
	public static void displaySummary(int nBuy, double totalBuyAmount, int nSell, double totalSellAmount) {
				
		System.out.println("\n" + "Number of buy transactions: " + nBuy);
		System.out.println("Total amount of buy transactions: " + "$" + df.format(totalBuyAmount));
		System.out.println("Number of sell transactions: " + nSell);
		System.out.println("Total amount of sell transactions: " + "$" + df.format(totalSellAmount));
		
	}
		
	/*
	 * The following method evaluates and returns the value for
	 * a user who is buying currency with US Dollars.
	 */
	public static double buyCurrency(char currency, double amount, double buyRateC, 
			double buyRateP, double buyRateE) {
		double buyRate = 0;

		switch (currency) {
		case 'C':
			buyRate = buyRateC;
			break;
		case 'c':
			buyRate = buyRateC;
			break;
		case 'P':
			buyRate = buyRateP;
			break;
		case 'p':
			buyRate = buyRateP;
			break;
		case 'E':
			buyRate = buyRateE;
			break;
		case 'e':
			buyRate = buyRateE;
			break;
		}
	return (1 + COMMISSION_RATE) * amount * buyRate;		
	}
	
	/*
	 * The following method evaluates and returns the value for 
	 * a user who is selling currency to obtain US dollars.
	 */
	public static double sellCurrency(char currency, double amount, double sellRateC,
			double sellRateP, double sellRateE){
		double sellRate = 0;

		switch (currency) {
		case 'C':
			sellRate = sellRateC;
			break;
		case 'c':
			sellRate = sellRateC;
			break;
		case 'P':
			sellRate = sellRateP;
			break;
		case 'p':
			sellRate = sellRateP;
			break;
		case 'E':
			sellRate = sellRateE;
			break;
		case 'e':
			sellRate = sellRateE;
			break;
		}
	return (1 - COMMISSION_RATE) * amount * sellRate;
		}
	
	
	/*
	 * The main method contains variables for buy and sell rates, the command
	 * to read the file containing the rates, and the user prompts to obtain
	 * the currency and the amount.
	 */
	public static void main(String[] args) throws IOException {
		
		double buyRateC;			    //buy rate for Canadian Dollar
		double sellRateC;			    //sell rate for Canadian Dollar
		double buyRateP;			    //buy rate for Mexican Peso
		double sellRateP;		    	//sell rate for Mexican Peso
		double buyRateE;			    //buy rate for European Euro
		double sellRateE;		    	//sell rate for European Euro
		char currency = 0;				//currency input from the user
		char transactionType = 0;   	//transaction type input from the user
		double amount = 0; 		    	//amount of money to be exchanged
		int nBuy = 0;					//number of buy transactions
		int nSell = 0;					//number of sell transactions
		double totalBuyAmount = 0;		//totals for buy transactions
		double totalSellAmount = 0;		//totals for sell transactions
		String currencyName = ""; 	    //conversion of currency char to a string for currency name
		double dollarAmount = 0; 	    //dollar amount of exchange
		
		
		//Reading the file for the exchange rates
		File inFile = new File("rates.txt");
		Scanner rates = new Scanner(inFile);
		
		//Writing to the output file
		PrintWriter outputFile = new PrintWriter("TransactionLog.txt");
		
		//Assigning values to the buy and sell rate variables
		buyRateC = rates.nextDouble();
		sellRateC = rates.nextDouble();
		buyRateP = rates.nextDouble(); 
		sellRateP = rates.nextDouble(); 
		buyRateE = rates.nextDouble(); 
		sellRateE = rates.nextDouble();
		
		//Requesting information from the user regarding currency type and amount
		System.out.print("Enter currency and amount(enter C for Canadian Dollar, P for Mexican Peso," +
						" or E for European Euro): "); 
			currency = kb.next().charAt(0);
			amount = kb.nextDouble();
		while (currency != SENTINEL) {
		if (currency == 'C' || currency == 'c'){
			currencyName = "Canadian Dollars"; }
		if (currency == 'P' || currency == 'p'){
				currencyName = "Mexican Pesos"; }
		if (currency == 'E' || currency == 'e') {
				currencyName = "European Euros"; }
		
		/*
		 * The following method tests for valid currency input, extracts currency amount from user input,
		 * requests transaction type from user, updates the buy/sell accumulator, and invokes
		 * the doTransaction method which will result in the equation for buy or sell being completed.
		 */
		
		isValidCurrency(currency); 
		//Display a correction if incorrect input is entered for currency type
		if (isValidCurrency(currency) == false) {
			System.out.println("You must enter one of 'C', 'P' or 'E' (upper- or lower-case)" +
			"for the currency."); }
		if (isValidCurrency(currency) == true) {	
			System.out.print("Buy or sell? ");
			transactionType = kb.next().charAt(0);
			if (transactionType != 'b' && transactionType != 's') {
				System.out.println("You must enter one of 'b' or 's' for the transaction type"); }
			if (transactionType == 'b'){
				nBuy++;}
			if (transactionType == 's'){
				nSell++; }
			dollarAmount = doTransaction(currency, amount, transactionType, buyRateC, buyRateP, buyRateE,
					sellRateC, sellRateP, sellRateE);
		
			//update the totalers
		if (transactionType == 'b') {
				totalBuyAmount = totalBuyAmount + dollarAmount; }
		else if (transactionType == 's') {
				totalSellAmount = totalSellAmount + dollarAmount; }
				
		}
			//Display a summary of the transaction
		if (transactionType == 'b' || transactionType == 's') {
			System.out.println((int)amount + " " + currencyName + " will cost you " + "$" + 
					df.format(dollarAmount));
			logTransaction(outputFile, currency, amount, transactionType, dollarAmount); }
			System.out.print("Enter next currency and amount(x to exit): ");
			currency = kb.next().charAt(0);
			if (currency != SENTINEL) {
			amount = kb.nextDouble();}
		
			
		}
		    //close output file and cause the summary to be displayed. 
			outputFile.close();
			if (currency == SENTINEL){
			displaySummary(nBuy, totalBuyAmount, nSell, totalSellAmount); }
	
	
	
}
	}

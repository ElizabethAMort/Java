import java.util.Scanner;

public class CalculatorApp {

	public static void main(String[] args) {
		int numberOne = 0;
		int numberTwo = 0;
		char operator = 0;
		
		Scanner kb = new Scanner(System.in);
		Addition add = new Addition();
		Subtraction sub = new Subtraction();
		Multiplication mult = new Multiplication();
		Division div = new Division();
		
		System.out.println("Please enter an integer: ");
		numberOne = kb.nextInt();
		
		System.out.println("Please enter another integer: ");
		numberTwo = kb.nextInt();
		
		System.out.println("Please enter an operator (+, -, *, /)");
		operator = kb.next().charAt(0);
		
		kb.close();
		
		switch(operator){
			case '+':
				add.addition(numberOne, numberTwo);
				break;
			case '-':
				sub.subtraction(numberOne, numberTwo);
				break;
			case '*':
				mult.multiplication(numberOne, numberTwo);
				break;
			case '/':
				div.division(numberOne, numberTwo);
				break;
			default:
				System.out.println("Please enter a valid operator.");
		}
		
	}

}

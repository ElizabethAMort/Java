import java.util.Scanner;
//Projects for Java mastery completed 7/16/14 by Elizabeth Mort

//Fibonacci Sequence to the Nth number, number provided by user

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println("Please enter a number: ");
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		int i = 0;
		
		while(i < n){
		System.out.print(fibonacci(i) + ", ");
		i++;
		}
		
		kb.close();
	}
		
	
	public static int fibonacci(int n)  {
	
		    if(n < 2){
		        return n;
		    }else{	
		      return fibonacci(n - 1) + fibonacci(n - 2);
		    }

	}

}
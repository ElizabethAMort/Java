import java.util.Scanner;
//Project for Java Mastery. Completed 7/15/14 by EM

public class PItoTheNth {

	public static void main(String[] args) {
		double pi = Math.PI;
		int format;
		System.out.println("Enter a number 1-9");
		Scanner kb = new Scanner(System.in);
		format = kb.nextInt();
		
		switch(format){
		case 1: 
			System.out.format("Pi to the " + format + "st digit is " + "%.1f", pi);
			break;
		
		case 2: 
		System.out.format("Pi to the " + format + "nd digit is " + "%.2f", pi);
		break;
	
case 3: 
	System.out.format("Pi to the " + format + "rd digit is " + "%.3f", pi);
	break;
case 4: 
	System.out.format("Pi to the " + format + "th digit is " + "%.4f", pi);
	break;
case 5: 
	System.out.format("Pi to the " + format + "th digit is " + "%.5f", pi);
break;
case 6: 
	System.out.format("Pi to the " + format + "th digit is " + "%.6f", pi);
break;
case 7: 
	System.out.format("Pi to the " + format + "th digit is " + "%.7f", pi);
break;
case 8: 
	System.out.format("Pi to the " + format + "th digit is " + "%.8f", pi);
break;
case 9: 
	System.out.format("Pi to the " + format + "th digit is " + "%.9f", pi);
break;
default:
	System.out.println("Pi is " + pi + " according to Java.");
		}	
		kb.close();
	}

}

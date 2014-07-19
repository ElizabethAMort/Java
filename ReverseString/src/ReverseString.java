import java.util.Scanner;

//Project for Java Mastery completed 7/19/14 by EM
public class ReverseString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter a word: ");
		
		Scanner kb = new Scanner(System.in);
		
		String forward = kb.next();
		
		for(int i = forward.length()-1; i >= 0; i--){
			System.out.print(forward.charAt(i)); 
		}
		kb.close();
	}

}

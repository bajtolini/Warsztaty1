package guessNumber2;

import java.util.Scanner;

public class Main {

	final static int LOW = 0;
	final static int BULLSEYE = 1;
	final static int HIGH = 2;
	final static int UNANSWERED = 3;
		
	public static void main(String[] args) {
		reverseGuessGame();
	}
	
	static int answear(Scanner scan) {
		while (true) {
			System.out.print("Odpowiedź : ");
			String str = scan.nextLine();
			str.toLowerCase();
		
			if (str.equals("za mało")) {
				return LOW;
			} else if (str.equals("za dużo")) {
				return HIGH;
			} else if (str.equals("zgadłeś")) {
				return BULLSEYE;
			} else {
				System.out.println("Zła odpowiedź!");
			}
		}
	}
	
	static void reverseGuessGame() {
		int min = 0;
		int max = 1000;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Pomyśl liczbę od " + min + " do " 
				+ max + ", a ja ją zgadnę w max. 10 próbach");
		
		int response = UNANSWERED;
		int guess;
		while (response != BULLSEYE) {
			guess = Math.round((float) (max - min) / 2) + min;
			System.out.println("Zgaduję: " + guess);
			
			response = answear(scan);
			if (response == HIGH) {
				max = guess;
			} else if (response == LOW) {
				min = guess;
			} else if (response != BULLSEYE) {
				System.out.println("nie oszukuj!");
			}
		}
		
		System.out.println("Wygrałem!");
		scan.close();		
	}

}

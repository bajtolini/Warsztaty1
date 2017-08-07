package guessNumber;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		numberGame();
	}
	
	static String compare(int cpNumber, int plNumber) {
		if (plNumber == cpNumber) {
			return "Zgadłeś!";
		} else if (plNumber > cpNumber) {
			return "Za dużo!";			
		} else {
			return "Za mało!";
		}
	}
	
	static void numberGame() {
		Random rand = new Random();
		int cpNumber = rand.nextInt(100) + 1;
		int plNumber = 0;
		Scanner scan = new Scanner(System.in);
		
		String str = "Zgadnij liczbę: ";
		while (cpNumber != plNumber) {
			System.out.print(str);
			if (scan.hasNextInt()) {
				plNumber = scan.nextInt();
				System.out.println(compare(cpNumber, plNumber));
			} else {
				System.out.println("To nie jest liczba");
				scan.next();
			}
		}
		
		scan.close();
	}

}

package lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	final static int MINRANGE = 1;
	final static int MAXRANGE = 49;

	public static void main(String[] args) {
		lotto();
	}
	
	static boolean checkNumber(int number, Integer[] arr) {
		if ((number <= MAXRANGE) && (number >= MINRANGE)) {
			for (int element : arr) {
				if ((number == element)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	static void result(Integer[] plNumbers, Integer[] cpNumbers) {
		int counter = 0;
		for (int cpNumber : cpNumbers) {
			for (int plNumber : plNumbers) {
				if (cpNumber == plNumber) {
					counter++;
				}
			}
		}
		
		System.out.println("Twoje liczby : " + Arrays.toString(plNumbers));
		System.out.println("Wylosowane liczby : " + Arrays.toString(cpNumbers));
		if (counter > 2) {
			System.out.println("Trafiłeś " + counter + "!");
		} else {
			System.out.println("Kiepściuchno :(");			
		}
	}
	
	static void lotto() {
		Integer[] arr = new Integer[49];
		
		for (int i = 0; i< arr.length; i++) {
			arr[i] = i + 1;
		}
		Collections.shuffle(Arrays.asList(arr));
		Integer[] cpNumbers = Arrays.copyOfRange(arr, 0, 6);
		Arrays.sort(cpNumbers);
		
		Scanner scan = new Scanner(System.in);
		Integer[] plNumbers = new Integer[6];
		Arrays.fill(plNumbers, 0);
		int number = 1;
		
		for (int i = 0; i < plNumbers.length;) {
			System.out.println("Podaj liczbę nr." + (i+1));
			if (scan.hasNextInt()) {
				number = scan.nextInt();
				if (checkNumber(number, plNumbers)) {
					plNumbers[i] = number;
					i++;
				} else {
					System.out.println("Podaj nową liczbę z zakresu "
							+ MINRANGE + "-"
							+ MAXRANGE);
				}
			} else {
				System.out.println("Podaj liczbę z zakresu "
						+ MINRANGE + "-"
						+ MAXRANGE);
				scan.next();
			}
		}
		Arrays.sort(plNumbers);
		
		result(plNumbers, cpNumbers);
		
		scan.close();		
	}

}

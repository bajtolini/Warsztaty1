package cube;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Podaj kod rzutu kostką : ");
		System.out.println(diceRoll(scan.nextLine()));
		
		scan.close();
	}
	
	static int diceRoll(String roll) {
		int[] diceTypes = new int[]{3, 4, 6, 8, 10, 12, 20, 100};
		roll = roll.toUpperCase();
		
		String firstSection = roll.substring(0, roll.indexOf("D"));
		roll = roll.substring(firstSection.length() + 1);
		int howManyRolls = (firstSection.length() == 0) ? 
				1 : Integer.parseInt(firstSection);
		
		String thirdSection = null;
		int diceType = 0;
		int modifier = 0;
		if ((roll.contains("+")) || (roll.contains("-"))) {
			int modifierIndex = 0;
			modifierIndex = (roll.indexOf("-") != -1) ? 
					roll.indexOf("-") : roll.indexOf("+");
					
			thirdSection = roll.substring(modifierIndex);
			modifier = Integer.parseInt(thirdSection);
			
			roll = roll.substring(0, roll.length() - thirdSection.length());
			diceType = Integer.parseInt(roll);
		} else {
			diceType = Integer.parseInt(roll);
		}
		
		for (int i = 0; i < diceTypes.length; i++) {
			if (diceType == diceTypes[i]) {
				Random rand = new Random();
				int result = modifier;

				for (int j = 0; j < howManyRolls; j++) {
					result += rand.nextInt(diceType) + 1;
				}
				
				return result; 
			}
		}
		return 0;
	}

}

import java.util.ArrayList;
import java.util.Scanner;

public class testCoinSorter {
	public static void main(String[] args) {
		CoinSorter coinSorter = new CoinSorter();
		Scanner sc = new Scanner (System.in);
		
		displayMenu();
		int option = sc.nextInt();
		while(option != 6) {
			runOption(option, coinSorter);
			displayMenu();
			option = sc.nextInt();
		}
		System.out.println("You've decided to quit the program. Bye bye!");
	}
	
	// Used to display the main menu
	private static void displayMenu() {
		System.out.println("\n***Coin Sorter - Main Menu***");
		System.out.println("1 - Coin calculator");
    	System.out.println("2 - Multiple coin calculator");
    	System.out.println("3 - Print coin list");
    	System.out.println("4 - Set details");
    	System.out.println("5 - Display program configurations");
    	System.out.println("6 - Quit the program");
	}
	
	// Used to choose an option from the main menu based on user's selection
	static void runOption(int option, CoinSorter coinSorter) {
		switch(option) {
			case 1:
				System.out.println("You've chosen the coin calculator");
				runCoinCalculator(coinSorter);
				break;
	    		case 2:
	    			System.out.println("You've chosen the multiple coin calculator");
	    			runMultipleCoinCalculator(coinSorter);
	    			break;
	    		case 3:
	    			System.out.println("You've chosen to print the coin list");
	    			System.out.println(coinSorter.printCoinList());
	    			break;
	    		case 4:
	    			System.out.println("You've chosen to set the details of the coin sorter");
	    			setCoinCalculatorDetails(coinSorter);
	    			break;
	    		case 5:
	    			System.out.println("You've chosen to display program configurations");
				System.out.println(coinSorter.displayProgramConfigs());
	    			break;
	    		default: return;
		}
	}

	// Used to set coin calculator details and display the sub menu
	private static void setCoinCalculatorDetails(CoinSorter coinSorter) {
		Scanner sc = new Scanner (System.in);
		displaySubMenu();
		
		int option = sc.nextInt();
		while(option != 4) {
			runOptionForSubMenu(option, coinSorter);
			displaySubMenu();
			option = sc.nextInt();
		}
	}

	// Used to choose an option from the sub menu based on user's selection
	private static void runOptionForSubMenu(int option, CoinSorter coinSorter) {
		Scanner sc = new Scanner (System.in);
		switch(option) {
			case 1:
				System.out.println("You've chosen to set the currency. Please enter the currency");
				coinSorter.setCurrency(sc.nextLine());
				break;
			case 2:
				System.out.println("You've chosen to set the minimum coin input value");
				coinSorter.setMinCoinIn(sc.nextInt());
				break;
			case 3:
				System.out.println("You've chosen to set the maximum coin input value");
				coinSorter.setMaxCoinIn(sc.nextInt());
				break;
			default: return;
		}
	}

	// Used to run the multiple coin calculator and validate user's inputs
	private static void runMultipleCoinCalculator(CoinSorter coinSorter) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How much would you like to exchange? in pennies e.g. £2 is 200p");
		int exchangeValue = sc.nextInt();
		boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn());
		
		if(exchangeValueValidation) {
			System.out.println("What coin would you like to exclude? The options are " + coinSorter.getCoinListString());
			int coinType = sc.nextInt();
			boolean coinValidation = validateCoin(coinType, coinSorter.getCoinList());
			if(coinValidation) {
				System.out.println(coinSorter.multiCoinCalculator(exchangeValue, coinType));
			}
		}
	}

	// Used to run the coin calculator and validate user's inputs
	private static void runCoinCalculator(CoinSorter coinSorter) {
		Scanner sc = new Scanner(System.in);
		System.out.println("How much would you like to exchange? in pennies e.g. £2 is 200p");
		int exchangeValue = sc.nextInt();
		boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn());
		
		if(exchangeValueValidation) {
			System.out.println("What coin denomination would you like it in? The options are " + coinSorter.getCoinListString());
			int coinType = sc.nextInt();
			boolean coinValidation = validateCoin(coinType, coinSorter.getCoinList());
			if(coinValidation) {
				System.out.println(coinSorter.coinCalculator(exchangeValue, coinType));
			}
		}

	}
	
	// Used to check the denomination a user enters in part of the coin list
	private static boolean validateCoin(int coinType, ArrayList<Integer> coinList) {
		if(coinList.contains(coinType)) {
			System.out.println("The coin list does contain " + coinType + "p");
			return true;
		} else {
			System.out.println("The coin list does not contain " + coinType + "p");
			return false;
		}
	}
	
	// Used to check the the exchange value id in between the boundaries of the coin values
	private static boolean validateExchangeValue(int exchangeValue, int minCoinValue, int maxCoinValue) {
		if(exchangeValue < minCoinValue) {
			System.out.println("The minimum amount you can exchange is: " + minCoinValue);
			return false;
		} else if(exchangeValue > maxCoinValue) {
			System.out.println("The maximum amount you can exchange is: " + maxCoinValue);
			return false;
		} else {
			return true;
		}
	}
	
	// Used to display the sub menu
	private static void displaySubMenu() {
		System.out.println("***Set Details Sub-Menu***");
		System.out.println("1 - Set currency");
        System.out.println("2 - Set minimum coin input value");
        System.out.println("3 - Set maximum coin input value");
        System.out.println("4 - Return to main menu");
	}
}

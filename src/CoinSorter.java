import java.util.ArrayList;
import java.util.Arrays;

public class CoinSorter {
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private ArrayList<Integer> coinList;

	public CoinSorter(String currencyIn, int minCoinEntered, int maxCoinEntered, ArrayList<Integer> coinListIn) {
		currency = currencyIn;
		minCoinIn = minCoinEntered;
		maxCoinIn = maxCoinEntered;
		coinList = coinListIn;
	}
	
	public CoinSorter() {
		ArrayList<Integer>defaultCoinList = new ArrayList<>(Arrays.asList(200, 100, 50, 20, 10));
		currency = "£";
		minCoinIn = 0;
		maxCoinIn = 10000;
		coinList = defaultCoinList;
	}

	public void setCurrency(String currencyIn) {
		currency = currencyIn;
	}
	
	public void setMinCoinIn(int minCoinEntered) {
		minCoinIn = minCoinEntered;
	}

	public void setMaxCoinIn(int maxCoinEntered) {
		maxCoinIn = maxCoinEntered;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public int getMinCoinIn() {
		return minCoinIn;
	}
	
	public int getMaxCoinIn() {
		return maxCoinIn;
	}
	
	public ArrayList<Integer> getCoinList() {
		return coinList;
	}
	
	public String printCoinList() {
		return "The current coin denominations are in circulation: " + getCoinListString();
	}
	
	public String coinCalculator(int exchangeValue, int coinType) {
		int maxNumberOfCoins = exchangeValue / coinType;
		int remainder = exchangeValue % coinType;
		return "A total of " + maxNumberOfCoins + " x "+ coinType + "p coins can " 
				+ "be exchanged, with a remainder of " + remainder + "p";
		
	}
	
	/* The multiple coin calculator works by taking an exchange value and a coin type
	 * to exclude. It iterates through the coin list and checks how many of each coin
	 * can be used to subtract from the exchange value before a remainder is left. Once
	 * this is known we subtract the value from the remainder in the last iteration and
	 * a string is added to a main string that contains the quantities of each coin in
	 * the exchange value. 
	 */
	public String multiCoinCalculator(int exchangeValue, int coinTypeToExclude) {
		int remainder = exchangeValue;
		String quantitiesOfCoins = "";
		for(int i =0;i<coinList.size();i++) {
			int coin = coinList.get(i);
			int quantity = 0;
			if(!(coin==coinTypeToExclude)) {
				quantity = remainder / coin;
				remainder -= (quantity*coin);
			}
			quantitiesOfCoins += quantity + " x " + coin + "p, ";
		}
		return "The coins exchanged are: " + quantitiesOfCoins
		+ "\nwith a remainder of " + remainder + "p";
	}
	
	public String displayProgramConfigs() {
		return "Current currency: " + getCurrency() +
				"\nMinimum input value: " + getMinCoinIn() +
				"\nMaximum input value: " + getMaxCoinIn();
	}
	
	public String getCoinListString() {
		String coinListToString = coinList.toString(); // Converting the coinList to a String but it still has the square brackets in the string
		coinListToString = coinListToString.substring(1, coinListToString.length()-1); // Removes the square from the string
		return coinListToString;
	}
}

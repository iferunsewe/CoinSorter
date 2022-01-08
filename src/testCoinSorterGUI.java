/**
 * Test class to run the CoinSorterGUI
 * @version 21/11/2020
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class testCoinSorterGUI extends Application {
	
	@Override    
	public void start(Stage stage) {
		CoinSorterGUI coinSorterGUI = new CoinSorterGUI(stage);
		coinSorterGUI.displayStage(stage);
	}
	
	public static void main(String[] args) {         
		launch(args);
	}

}

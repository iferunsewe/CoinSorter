import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CoinSorterGUI extends Application {
	
	@Override    
	public void start(Stage stage) {
		CoinSorter coinSorter = new CoinSorter();
		
		Menu coinSorterMainMenu = new Menu("Click here to choose from this menu");

	    MenuItem coinCalculatorMenuItem = new MenuItem("Coin calculator");
	    MenuItem multipleCaculatorMenuItem = new MenuItem("Multiple coin calculator");
	    MenuItem printCoinListMenuItem = new MenuItem("Print coin list");
	    Menu setDetailsMenuItem = new Menu("Set details");
	    MenuItem displayProgramConfigsMenuItem = new MenuItem("Display program configurations");
	    MenuItem quitMenuItem = new MenuItem("Quit the program");
	    
	    MenuItem setCurrencyMenuItem = new MenuItem("Set currency");
	    MenuItem setMinCoinMenuItem = new MenuItem("Set minimum coin input value");
	    MenuItem setMaxCoinMenuItem = new MenuItem("Set maximum coin input value");
	    
	    setDetailsMenuItem.getItems().addAll(
	    		setCurrencyMenuItem,
	    		setMinCoinMenuItem,
	    		setMaxCoinMenuItem
	    );
	    

	    coinSorterMainMenu.getItems().addAll(
	    		coinCalculatorMenuItem,
	    		multipleCaculatorMenuItem,
	    		printCoinListMenuItem,
	    		setDetailsMenuItem,
	    		displayProgramConfigsMenuItem,
	    		quitMenuItem
	    );
	    
	    VBox root = new VBox(25);
	 	MenuBar menuBar = new MenuBar(coinSorterMainMenu);
	 	VBox homeRoot = buildHomeVBox();
	 	root.getChildren().addAll(menuBar, homeRoot);

	 	Scene defaultScene = new Scene(root, 800, 400);
	    stage.setScene(defaultScene);
	    
	    buildEventListeners(
	    	root,
	    	coinSorter,
	    	coinCalculatorMenuItem,
	    	multipleCaculatorMenuItem,
	    	printCoinListMenuItem,
	    	displayProgramConfigsMenuItem,
	    	setCurrencyMenuItem,
	    	setMinCoinMenuItem,
	    	setMaxCoinMenuItem
	    );
	 	
	 	// create and configure a button to perform the calculations 
	    stage.setTitle("Coin Sorter");
	    stage.show();
	}
	
	private void buildEventListeners(VBox root, CoinSorter coinSorter, MenuItem coinCalculatorMenuItem,
			MenuItem multipleCaculatorMenuItem, MenuItem printCoinListMenuItem, MenuItem displayProgramConfigsMenuItem,
			MenuItem setCurrencyMenuItem, MenuItem setMinCoinMenuItem, MenuItem setMaxCoinMenuItem) {
		coinCalculatorMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildCoinCalculatorVBox(coinSorter));
		});
	    
	    multipleCaculatorMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildMultipleCalculatorVBox(coinSorter));
	    });
	    
	    printCoinListMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildCoinListVBox(coinSorter));
	    });
	    
	    displayProgramConfigsMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildDisplayConfigsVBox(coinSorter));
	    });
	    
	    setCurrencyMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildSetCurrencyVBox(coinSorter));
	    });
	    
	    setMinCoinMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildSetMinCoinVBox(coinSorter));
	    });
	    
	    setMaxCoinMenuItem.setOnAction(e -> {
	    	root.getChildren().remove(1);
	    	root.getChildren().add(buildSetMaxCoinVBox(coinSorter));
	    });
	}

	public static void main(String[] args) {         
		launch(args);
	}
	
	private VBox buildHomeVBox() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
		Text explainer = new Text("Welcome to the coin sorter. Choose from the main menu above find out what you can do with the coin sorter");
	 	explainer.setFont(Font.font("Arial"));
	 	container.getChildren().addAll(explainer);
	    return container;
	}
	
	private VBox buildCoinCalculatorVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text();
	    Text explainer = new Text("Coin calculator");
	    HBox inputComponents = buildInputComponents(coinSorter, result);
	    container.getChildren().addAll(explainer, inputComponents, result);
	    return container;
	}
	
	private VBox buildMultipleCalculatorVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text();
	    Text explainer = new Text("Multiple coin calculator");
	    HBox inputComponents = buildMultipleCoinInputComponents(coinSorter, result);
	    container.getChildren().addAll(explainer, inputComponents, result);
	    return container;
	}
	
	private VBox buildCoinListVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.printCoinList());
	    Text explainer = new Text("The coin list");
	    container.getChildren().addAll(explainer, result);
	    return container;
	}
	
	private VBox buildDisplayConfigsVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.displayProgramConfigs());
	    Text explainer = new Text("The coin sorter configuration");
	    container.getChildren().addAll(explainer, result);
	    return container;
	}

	private VBox buildSetCurrencyVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
		// create and configure Labels for the text fields  
	    Label currencyLabel = new Label("Currency");
		TextField currencyField = new TextField();         
	 	currencyField.setMaxWidth(50);
	 	Button setButton = new Button();         
 	 	setButton.setText("Set");
	 	
	 	// create and configure text fields for input   
	 	HBox inputComponents = new HBox(10);
	 	inputComponents.setAlignment(Pos.CENTER);
	 	inputComponents.getChildren().addAll(currencyLabel, currencyField, setButton);
	 	
 	 	
 	 	Text result = new Text();
	    Text explainer = new Text("Set a currency");
 	 	
 	 	setButton.setOnAction(e -> {
 	 		String currency = currencyField.getText();
 	 		coinSorter.setCurrency(currency);
 	 		result.setText(coinSorter.displayProgramConfigs());
		});
	    
	    container.getChildren().addAll(inputComponents, explainer, result);
	    return container;
	}

	private VBox buildSetMinCoinVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
		// create and configure Labels for the text fields  
	    Label minValueLabel = new Label("Minimum value");
		TextField minValueField = new TextField();         
	 	minValueField.setMaxWidth(50);
	 	Button setButton = new Button();         
 	 	setButton.setText("Set");
	 	
	 	// create and configure text fields for input   
	 	HBox inputComponents = new HBox(10);
	 	inputComponents.setAlignment(Pos.CENTER);
	 	inputComponents.getChildren().addAll(minValueLabel, minValueField, setButton);
	 	
 	 	
 	 	Text result = new Text();
	    Text explainer = new Text("Set a minimum value");
 	 	
 	 	setButton.setOnAction(e -> {
 	 		int minValue = Integer.parseInt(minValueField.getText());
 	 		if(validateMinMaxValues(minValue, coinSorter.getMaxCoinIn(), result)) {
 	 			coinSorter.setMinCoinIn(minValue);
 	 	 		result.setText(coinSorter.displayProgramConfigs());
 	 		}
		});
	 	
 	 	container.getChildren().clear();
	    container.getChildren().addAll(inputComponents, explainer, result);
	    return container;
	}
	
	private VBox buildSetMaxCoinVBox(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
		// create and configure Labels for the text fields  
	    Label maxValueLabel = new Label("Maximum value");
		TextField maxValueField = new TextField();         
	 	maxValueField.setMaxWidth(50);
	 	Button setButton = new Button();         
 	 	setButton.setText("Set");
	 	
	 	// create and configure text fields for input   
	 	HBox inputComponents = new HBox(10);
	 	inputComponents.setAlignment(Pos.CENTER);
	 	inputComponents.getChildren().addAll(maxValueLabel, maxValueField, setButton);
	 	
 	 	
 	 	Text result = new Text();
	    Text explainer = new Text("Set a maximum value");
 	 	
 	 	setButton.setOnAction(e -> {
 	 		int maxValue = Integer.parseInt(maxValueField.getText());
 	 		if(validateMinMaxValues(coinSorter.getMinCoinIn(), maxValue, result)) {
	 	 		coinSorter.setMaxCoinIn(maxValue);
	 	 		result.setText(coinSorter.displayProgramConfigs());
 	 		}
		});
	 	
	    container.getChildren().addAll(inputComponents, explainer, result);
	    return container;
	}
	
	private HBox buildInputComponents(CoinSorter coinSorter, Text result) {
	    // create and configure Labels for the text fields  
	    Label exchangeValueLabel = new Label("Exchange value");
	    exchangeValueLabel.setFont(Font.font("Arial", 20)); 
	    
	    Label coinTypeLabel = new Label("Coin type");
	    coinTypeLabel.setFont(Font.font("Arial", 20));
	    
	    // create and configure text fields for input         
	 	TextField exchangeValueField = new TextField();         
	 	exchangeValueField.setMaxWidth(50);         
	 	TextField coinTypeField = new TextField();         
	 	coinTypeField.setMaxWidth(50); 
	    
	    Button calculateButton = new Button();         
 	 	calculateButton.setText("Calculate"); 
 	 	
 	 	calculateButton.setOnAction(e -> {
 	 		int exchangeValue = Integer.parseInt(exchangeValueField.getText());
 	 		int coinType = Integer.parseInt(coinTypeField.getText());
 	 		boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn(), result);
 	 		boolean coinValidation = validateCoin(coinType, coinSorter.getCoinList(), result);
 	 		if(coinValidation && exchangeValueValidation) {
 	 			result.setText(coinSorter.coinCalculator(exchangeValue, coinType));
 	 		}
 	 		
		});
	 	
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox inputComponents = new HBox(10);
	 	inputComponents.setAlignment(Pos.CENTER);
	 	inputComponents.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeField, calculateButton);
	 	return inputComponents;
	}
	
	private HBox buildMultipleCoinInputComponents(CoinSorter coinSorter, Text result) {
		// create and configure text fields for input         
	 	TextField exchangeValueField = new TextField();         
	 	exchangeValueField.setMaxWidth(50);         
	 	TextField coinTypeField = new TextField();         
	 	coinTypeField.setMaxWidth(50); 
	    
	    // create and configure Labels for the text fields  
	    Label exchangeValueLabel = new Label("Exchange value");
	    exchangeValueLabel.setFont(Font.font("Arial", 20)); 
	    
	    Label coinTypeLabel = new Label("Coin type");
	    coinTypeLabel.setFont(Font.font("Arial", 20)); 
	    
	    Button calculateButton = new Button();         
 	 	calculateButton.setText("Calculate"); 
 	 	
 	 	calculateButton.setOnAction(e -> {
 	 			int exchangeValue = Integer.parseInt(exchangeValueField.getText());
 	 			int coinType = Integer.parseInt(coinTypeField.getText());
 	 			boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn(), result);
 	 	 		boolean coinValidation = validateCoin(coinType, coinSorter.getCoinList(), result);
 	 	 		if(coinValidation && exchangeValueValidation) {
 	 	 			result.setText(coinSorter.multiCoinCalculator(exchangeValue, coinType));
 	 	 		}
		});
	 	
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox inputComponents = new HBox(10);
	 	inputComponents.setAlignment(Pos.CENTER);
	 	inputComponents.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeField, calculateButton);
	 	return inputComponents;
	}

	// Used to check the denomination a user enters in part of the coin list
	private static boolean validateCoin(int coinType, ArrayList<Integer> coinList, Text result) {
		if(coinList.contains(coinType)) {
			return true;
		} else {
			result.setText("The coin list does not contain " + coinType + "p");
			return false;
		}
	}
	
	// Used to check the the exchange value id in between the boundaries of the coin values
	private static boolean validateExchangeValue(int exchangeValue, int minCoinValue, int maxCoinValue, Text result) {
		if(exchangeValue < minCoinValue) {
			result.setText("The minimum amount you can exchange is: " + minCoinValue);
			return false;
		} else if(exchangeValue > maxCoinValue) {
			result.setText("The maximum amount you can exchange is: " + maxCoinValue);
			return false;
		} else {
			return true;
		}
	}

	private static boolean validateMinMaxValues(int minValue, int maxValue, Text result) {
		if(minValue > maxValue) {
			result.setText("The maximum amount: " + maxValue + " must be greater than the minimum amount: " + minValue);
			return false;
		}
		return true;
	}

}

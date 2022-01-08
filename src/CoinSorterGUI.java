/**
 * A class to that uses JavaFX to display information and provide the user 
 * with some actions relating to functionality of the CoinSorter. 
 * @version 21/11/2020
 */

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CoinSorterGUI {
	private Menu coinSorterMainMenu = new Menu("Main menu. Click here to use the coin sorter.");
    private MenuItem coinCalculatorMenuItem = new MenuItem("Coin calculator");
    private MenuItem multipleCalculatorMenuItem = new MenuItem("Multiple coin calculator");
    private MenuItem printCoinListMenuItem = new MenuItem("Print coin list");
    private Menu setDetailsMenuItem = new Menu("Set details");
    private MenuItem displayProgramConfigsMenuItem = new MenuItem("Display program configurations");
    private MenuItem quitMenuItem = new MenuItem("Quit the program");
    private MenuItem setCurrencyMenuItem = new MenuItem("Set currency");
    private MenuItem setMinCoinMenuItem = new MenuItem("Set minimum coin input value");
    private MenuItem setMaxCoinMenuItem = new MenuItem("Set maximum coin input value");
    private Text explainer = new Text();
    private Label exchangeValueLabel = new Label("Exchange value");
    private TextField exchangeValueField = new TextField();  
    private Label currencyLabel = new Label("Currency");
    private TextField currencyField = new TextField();
    private Label minValueLabel = new Label("Minimum value");
	private TextField minValueField = new TextField();
	private Label maxValueLabel = new Label("Maximum value");
	private TextField maxValueField = new TextField(); 
	private Label coinTypeLabel = new Label("Coin type");
	private final ComboBox<Integer> coinTypeSelect = new ComboBox<Integer>();
	private Button setButton = new Button("Set");
	private Button calculateButton = new Button("Calculate");
	private Text result = new Text();
	private final int FIELD_WIDTH = 100;
	private final int SCENE_WIDTH = 800;
	private final int SCENE_HEIGHT = 400;
	private final int FONT_SIZE = 20;
	private VBox root = new VBox(25);
	private CoinSorter coinSorter = new CoinSorter();
	    
	public CoinSorterGUI(Stage stage) {
		formatComponents();
		addMenuItemsToSubMenu();
		addMenuItemsToMainMenu();
		setUpCoinList();
		setUpRoot();
	 	buildEventListeners(root, coinSorter);
	}
	
	// Creating the default screen which has the root VBox in it and then shows
	// the stage for the program to be displayed on the screen
	void displayStage(Stage stage) {
		Scene defaultScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
	    stage.setScene(defaultScene);
	    stage.setTitle("Coin Sorter");
	    stage.show();
	}
	
	// Setting up the base root. It is made up of the menu bar and the sub
    // component which is dynamic based what is selected in the menu bar.
	private void setUpRoot() {
		MenuBar menuBar = new MenuBar(coinSorterMainMenu);
	 	VBox homeRoot = buildHomeContainer();
	 	root.getChildren().addAll(menuBar, homeRoot);
	};

	// Setting up coin list to use in the form
	private void setUpCoinList() {
	    coinTypeSelect.getItems().addAll(coinSorter.getCoinList());
	    // Preselecting a value from the coin list select to prevent null values
	    coinTypeSelect.getSelectionModel().select(0); 
	}
	
	// Adding all the main menu options for coin sorter
	private void addMenuItemsToMainMenu() {
		
	    coinSorterMainMenu.getItems().addAll(
	    		coinCalculatorMenuItem,
	    		multipleCalculatorMenuItem,
	    		printCoinListMenuItem,
	    		setDetailsMenuItem,
	    		displayProgramConfigsMenuItem,
	    		quitMenuItem
	    );
	}
	
	// Adding all the sub menu items for the set details option
	private void addMenuItemsToSubMenu() {
	    setDetailsMenuItem.getItems().addAll(
	    		setCurrencyMenuItem,
	    		setMinCoinMenuItem,
	    		setMaxCoinMenuItem
	    );
	}
	
	// Format components
	private void formatComponents() {
		
		explainer.setFont(Font.font("Arial"));
		exchangeValueLabel.setFont(Font.font("Arial", FONT_SIZE));    
	    coinTypeLabel.setFont(Font.font("Arial", FONT_SIZE));
	    exchangeValueField.setMaxWidth(FIELD_WIDTH);
	 	currencyField.setMaxWidth(FIELD_WIDTH); 
	 	minValueField.setMaxWidth(FIELD_WIDTH);
	 	maxValueField.setMaxWidth(FIELD_WIDTH);
	}
	
	// Setting up all the event listeners for each menu bar option which should
	// change the content of the sub component in the root container
	private void buildEventListeners(VBox root, CoinSorter coinSorter) {
		coinCalculatorMenuItem.setOnAction(e -> {
			coinCalculatorMenuItemHandler(coinSorter, root);
		});
	    
	    multipleCalculatorMenuItem.setOnAction(e -> {
	    	multipleCalculatorMenuItemHandler(coinSorter, root);
	    });
	    
	    printCoinListMenuItem.setOnAction(e -> {
	    	printCoinListMenuItemHandler(coinSorter, root);
	    });
	    
	    displayProgramConfigsMenuItem.setOnAction(e -> {
	    	displayProgramConfigsMenuItemHandler(coinSorter, root);
	    });
	    
	    setCurrencyMenuItem.setOnAction(e -> {
	    	 setCurrencyMenuItemHandler(coinSorter, root);
	    });
	    
	    setMinCoinMenuItem.setOnAction(e -> {
	    	setMinCoinMenuItemHandler(coinSorter, root);
	    });
	    
	    setMaxCoinMenuItem.setOnAction(e -> {
	    	setMaxCoinMenuItemHandler(coinSorter, root);
	    });
	    
	    quitMenuItem.setOnAction(e -> {
	    	Platform.exit();
	    });
	}
	
	// Builds the container to used to take show at first when the program firsts loads
	private VBox buildHomeContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    explainer.setText(
	    		"Welcome to the coin sorter.\nChoose from the main menu above" +
	    		" find out what you can do with the coin sorter"
	    );
	 	container.getChildren().addAll(explainer);
	    return container;
	}

	// Builds the container to used to take advantage of the coin calculator
	private VBox buildCoinCalculatorContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    explainer.setText(
	    		"Coin calculator\nGive me the amount of money you have to exchange" + 
	    		" and what denomination you want the\nchange in and I will tell" +
	    		" you how many coins I can return and what the remainder will be.");
	    HBox formComponent = buildCoinCalculatorForm();
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	// Builds the form to used to take advantage of the coin calculator
	private HBox buildCoinCalculatorForm() {
		// Run the coin calculator when the calculator button is clicked
 	 	calculateButton.setOnAction(e -> {
 	 		coinCalculatorFormHandler();
		});
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox form = new HBox(10);
	 	form.setAlignment(Pos.CENTER);
	 	form.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeSelect, calculateButton);
	 	return form;
	}
	
	// Builds the container to used to take advantage of the multiple coin calculator
	private VBox buildMultipleCalculatorContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
	    explainer.setText(
	    		"Multiple coin calculator\nGive me the amount of money" + 
	    		" you have to exchange and what denomination you want to exclude in " +
	    		"the\nchange and I will tell you how many coins of each denomination " +
	    		"I can return and what the remainder will be."
	    );
	    HBox formComponent = buildMultipleCoinForm();
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	// Builds the form to used to take advantage of the multiple coin calculator
	private HBox buildMultipleCoinForm() {
		// Run the multiple coin calculator when the calculator button is clicked
 	 	calculateButton.setOnAction(e -> {
 	 		multipleCalculatorFormHandler();
		});
	 	
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox form = new HBox(10);
	 	form.setAlignment(Pos.CENTER);
	 	form.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeSelect, calculateButton);
	 	return form;
	}
	
	// Builds the container to display the coin list.
	private VBox buildCoinListContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.printCoinList());
	    explainer.setText("The coin list");
	    container.getChildren().addAll(explainer, result);
	    return container;
	}
	
	// Builds the container to display the program configurations.
	private VBox buildDisplayConfigsContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.displayProgramConfigs());
	    explainer.setText(
	    		"The coin sorter configuration\n" +
	    		"This displays how the coin sorter is currently set up."
	    );
	    container.getChildren().addAll(explainer, result);
	    return container;
	}

	// Builds the set currency container to display on the screen.
	private VBox buildSetCurrencyContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
	 	// create and configure text fields for input   
	 	HBox formComponent = new HBox(10);
	 	formComponent.setAlignment(Pos.CENTER);
	 	formComponent.getChildren().addAll(currencyLabel, currencyField, setButton);
	 	
	    explainer.setText("Set a currency");
 	 	
 	 	setButton.setOnAction(e -> {
 	 		String currency = currencyField.getText();
 	 		coinSorter.setCurrency(currency);
 	 		result.setText(coinSorter.displayProgramConfigs());
		});
	    
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}

	// Builds the set minimum coin input value container to display on the screen.
	private VBox buildSetMinCoinContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	 	
	 	// create and configure text fields for input 
	 	HBox formComponent = new HBox(10);
	 	formComponent.setAlignment(Pos.CENTER);
	 	formComponent.getChildren().addAll(minValueLabel, minValueField, setButton);
	 	
	    explainer.setText(
	    		"Set a minimum value\nWhen you enter a minimum value below, this will change the" + 
	    		"\nconfiguration of the coin sorter and a you not be able to exchange a value lower" + 
	    		"\nthan this value. You can not set below 0 and it can not be greater than the maximum" + 
	    		"\nvalue " + coinSorter.getMaxCoinIn()
	    );
 	 	
 	 	setButton.setOnAction(e -> {
 	 		int minValue = Integer.parseInt(minValueField.getText());
 	 		if(validateMinMaxValues(minValue, coinSorter.getMaxCoinIn(), result)) {
 	 			coinSorter.setMinCoinIn(minValue);
 	 	 		result.setText(coinSorter.displayProgramConfigs());
 	 		}
		});
	 	
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	// Builds the set maximum coin input value container to display on the screen.
	private VBox buildSetMaxCoinContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	 	
	 	// create and configure text fields for input   
	 	HBox formComponent = new HBox(10);
	 	formComponent.setAlignment(Pos.CENTER);
	 	formComponent.getChildren().addAll(maxValueLabel, maxValueField, setButton);
	 	
	    explainer.setText(
	    		"Set a maximum value\nWhen you enter a maximum value below, this will change the" + 
	    	    "\nconfiguration of the coin sorter and a you not be able to exchange a value higher" + 
	    	    "\nthan this value. You can not set below 0 and it can not be less than the minimum" + 
	    	    "\nvalue " + coinSorter.getMinCoinIn()
	    
	    );
 	 	
 	 	setButton.setOnAction(e -> {
 	 		int maxValue = Integer.parseInt(maxValueField.getText());
 	 		if(validateMinMaxValues(coinSorter.getMinCoinIn(), maxValue, result)) {
	 	 		coinSorter.setMaxCoinIn(maxValue);
	 	 		result.setText(coinSorter.displayProgramConfigs());
 	 		}
		});
	 	
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	// Event handler for the the coin calculator form. It performs some validations
	// and uses the CoinSorter object to calculate the result and then uses the result variable
	// to display the result of the calculation
	private void coinCalculatorFormHandler() {
		if (exchangeValueField.getText() == "") {
			result.setText("You must enter an exchange value in order to calculate the result");
			return;
		}
		int exchangeValue = Integer.parseInt(exchangeValueField.getText().trim());
		int coinType = (Integer) coinTypeSelect.getValue();
 		boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn(), result);
 		
 		// Check if coin is part of the coin list before running the coin calculator
 		if(exchangeValueValidation) {
 			result.setText(coinSorter.coinCalculator(exchangeValue, coinType));
 		}
 		
	}

	// Event handler for the the multiple coin calculator form. It performs some validations
	// and uses the CoinSorter object to calculate the result and then uses the result variable
	// to display the result of the calculation
	private void multipleCalculatorFormHandler() {
		if (exchangeValueField.getText() == "") {
			result.setText("You must enter an exchange value in order to calculate the result");
			return;
		}
		int exchangeValue = Integer.parseInt(exchangeValueField.getText().trim());
		int coinType = (Integer) coinTypeSelect.getValue();
		boolean exchangeValueValidation = validateExchangeValue(exchangeValue, coinSorter.getMinCoinIn(), coinSorter.getMaxCoinIn(), result);
 		
 		// Check if coin is part of the coin list before running the multiple coin calculator
 		if(exchangeValueValidation) {
 			result.setText(coinSorter.multiCoinCalculator(exchangeValue, coinType));
 		}
 		
	}
	
	// Handles when a user clicks the coin calculator menu item. It has to clear
	// the some texts and fields as they are shared with other screens
	private void coinCalculatorMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildCoinCalculatorContainer());
	}
	
	// Handles when a user clicks the multiple coin calculator menu item. It has to clear
	// the some texts and fields as they are shared with other screens
	private void multipleCalculatorMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildMultipleCalculatorContainer());
	}

	// Handles when a user clicks the display program configurations menu item
	private void displayProgramConfigsMenuItemHandler(CoinSorter coinSorter, VBox root) {
		root.getChildren().remove(1);
    	root.getChildren().add(buildDisplayConfigsContainer());
	}
	
	// Handles when a user clicks the print coin list menu item
	private void printCoinListMenuItemHandler(CoinSorter coinSorter, VBox root) {
		root.getChildren().remove(1);
    	root.getChildren().add(buildCoinListContainer());
	}
	
	// Handles when a user clicks the set currency menu item. It has to clear
	// the some texts and fields as they are shared with other screens
	private void setCurrencyMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetCurrencyContainer());
	}
	
	// Handles when a user clicks the set minimum coin value menu item. It has to clear
	// the some texts and fields as they are shared with other screens
	private void setMinCoinMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetMinCoinContainer());
	}
	
	// Handles when a user clicks the set maximum coin value menu item. It has to clear
	// the some texts and fields as they are shared with other screens
	private void setMaxCoinMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetMaxCoinContainer());
	}
	
	// Used to clear text and fields that used in multiple screens
	private void clearTextsAndFields() {
		result.setText("");
		exchangeValueField.setText("");
	}
	
	// Used to check the the exchange value id in between the boundaries of the coin values
	private boolean validateExchangeValue(int exchangeValue, int minCoinValue, int maxCoinValue, Text result) {
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

	// Used to validate that the minimum and and maximum values are in the right order when they are set
	private boolean validateMinMaxValues(int minValue, int maxValue, Text result) {
		if(minValue < 0 || maxValue < 0) {
			result.setText("The amount set must be greater than 0");
			return false;
		} else if(minValue > maxValue) {
			result.setText("The maximum amount: " + maxValue + " must be greater than the minimum amount: " + minValue);
			return false;
		}
		return true;
	}

}

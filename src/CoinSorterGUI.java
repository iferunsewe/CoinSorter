import javafx.application.Application;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CoinSorterGUI extends Application {
	private Menu coinSorterMainMenu = new Menu("Click here to choose from this menu");
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
	private final int FIELD_WIDTH = 50;
	private final int SCENE_WIDTH = 800;
	private final int SCENE_HEIGHT = 400;
	private final int FONT_SIZE = 20;
	
	@Override    
	public void start(Stage stage) {
		CoinSorter coinSorter = new CoinSorter();
		
		// Format components
		explainer.setFont(Font.font("Arial"));
		exchangeValueLabel.setFont(Font.font("Arial", FONT_SIZE));    
	    coinTypeLabel.setFont(Font.font("Arial", FONT_SIZE));
	    exchangeValueField.setMaxWidth(FIELD_WIDTH);
	 	currencyField.setMaxWidth(FIELD_WIDTH); 
	 	minValueField.setMaxWidth(FIELD_WIDTH);
	 	maxValueField.setMaxWidth(FIELD_WIDTH);
	 	
		
	 	// Adding all the sub menu items for the set details option
	    setDetailsMenuItem.getItems().addAll(
	    		setCurrencyMenuItem,
	    		setMinCoinMenuItem,
	    		setMaxCoinMenuItem
	    );
	    
	    // Adding all the main menu options for coin sorter
	    coinSorterMainMenu.getItems().addAll(
	    		coinCalculatorMenuItem,
	    		multipleCalculatorMenuItem,
	    		printCoinListMenuItem,
	    		setDetailsMenuItem,
	    		displayProgramConfigsMenuItem,
	    		quitMenuItem
	    );
	    
	    // Setting up coin list to use in the form
	    coinTypeSelect.getItems().addAll(coinSorter.getCoinList());
	    // Preselecting a value from the coin list select to prevent null values
	    coinTypeSelect.getSelectionModel().select(0); 
	    
	    // Setting up the base root. It is made up of the menu bar and the sub
	    // component which is dynamic based what is selected in the menu bar.
	    VBox root = new VBox(25);
	 	MenuBar menuBar = new MenuBar(coinSorterMainMenu);
	 	VBox homeRoot = buildHomeContainer();
	 	root.getChildren().addAll(menuBar, homeRoot);
	 	
	 	// Setting up all the event listeners for each menu bar option which should
	 	// change the content of the sub component in the root container
	 	buildEventListeners(root, coinSorter);

	 	// Creating the 
	 	Scene defaultScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
	    stage.setScene(defaultScene);
	    stage.setTitle("Coin Sorter");
	    stage.show();
	}

	public static void main(String[] args) { 
		launch(args);
	}
	
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
	
	private VBox buildHomeContainer() {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    explainer.setText("Welcome to the coin sorter. Choose from the main menu above find out what you can do with the coin sorter");
	 	container.getChildren().addAll(explainer);
	    return container;
	}

	private VBox buildCoinCalculatorContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    explainer.setText("Coin calculator");
	    HBox formComponent = buildCoinCalculatorForm(coinSorter);
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	private HBox buildCoinCalculatorForm(CoinSorter coinSorter) {
		// Run the coin calculator when the calculator button is clicked
 	 	calculateButton.setOnAction(e -> {
 	 		coinCalculatorFormHandler(coinSorter);
		});
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox form = new HBox(10);
	 	form.setAlignment(Pos.CENTER);
	 	form.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeSelect, calculateButton);
	 	return form;
	}
	
	private VBox buildMultipleCalculatorContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    
	    explainer.setText("Multiple coin calculator");
	    HBox formComponent = buildMultipleCoinForm(coinSorter);
	    container.getChildren().addAll(explainer, formComponent, result);
	    return container;
	}
	
	private HBox buildMultipleCoinForm(CoinSorter coinSorter) {
		// Run the multiple coin calculator when the calculator button is clicked
 	 	calculateButton.setOnAction(e -> {
 	 		multipleCalculatorFormHandler(coinSorter);
		});
	 	
	 	// create and configure an HBox for the labels and text inputs                       
	 	HBox form = new HBox(10);
	 	form.setAlignment(Pos.CENTER);
	 	form.getChildren().addAll(exchangeValueLabel, exchangeValueField, coinTypeLabel, coinTypeSelect, calculateButton);
	 	return form;
	}
	
	private VBox buildCoinListContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.printCoinList());
	    explainer.setText("The coin list");
	    container.getChildren().addAll(explainer, result);
	    return container;
	}
	
	private VBox buildDisplayConfigsContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	    Text result = new Text(coinSorter.displayProgramConfigs());
	    explainer.setText("The coin sorter configuration");
	    container.getChildren().addAll(explainer, result);
	    return container;
	}

	private VBox buildSetCurrencyContainer(CoinSorter coinSorter) {
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

	private VBox buildSetMinCoinContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	 	
	 	// create and configure text fields for input 
	 	HBox formComponent = new HBox(10);
	 	formComponent.setAlignment(Pos.CENTER);
	 	formComponent.getChildren().addAll(minValueLabel, minValueField, setButton);
	 	
	    explainer.setText("Set a minimum value");
 	 	
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
	
	private VBox buildSetMaxCoinContainer(CoinSorter coinSorter) {
		VBox container = new VBox(25);
	    container.setAlignment(Pos.CENTER);
	 	
	 	// create and configure text fields for input   
	 	HBox formComponent = new HBox(10);
	 	formComponent.setAlignment(Pos.CENTER);
	 	formComponent.getChildren().addAll(maxValueLabel, maxValueField, setButton);
	 	
	    explainer.setText("Set a maximum value");
 	 	
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
	
	private void coinCalculatorFormHandler(CoinSorter coinSorter) {
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

	private void multipleCalculatorFormHandler(CoinSorter coinSorter) {
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
	
	private void coinCalculatorMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildCoinCalculatorContainer(coinSorter));
	}
	
	private void multipleCalculatorMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildMultipleCalculatorContainer(coinSorter));
	}

	private void displayProgramConfigsMenuItemHandler(CoinSorter coinSorter, VBox root) {
		root.getChildren().remove(1);
    	root.getChildren().add(buildDisplayConfigsContainer(coinSorter));
	}
	
	private void printCoinListMenuItemHandler(CoinSorter coinSorter, VBox root) {
		root.getChildren().remove(1);
    	root.getChildren().add(buildCoinListContainer(coinSorter));
	}
	
	private void setCurrencyMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetCurrencyContainer(coinSorter));
	}
	
	private void setMinCoinMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetMinCoinContainer(coinSorter));
	}
	
	private void setMaxCoinMenuItemHandler(CoinSorter coinSorter, VBox root) {
		clearTextsAndFields();
		root.getChildren().remove(1);
    	root.getChildren().add(buildSetMaxCoinContainer(coinSorter));
	}
	
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

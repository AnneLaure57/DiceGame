package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.persist.MongoDBKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.PostGreSQLKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.XMLKit;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieOneFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieTwoFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollTwoDiceAtSameTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * The main view controller of the Dice Game.
 */
public class MainView implements Initializable {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : MainView.
	 */
	private static final Logger LOG = Logger.getLogger(MainView.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	/* ========================================= Vues ================================================== */ /*=========================================*/

	@FXML 
	private Parent root ;
	
	@FXML
	private AnchorPane formNickName;
	
	@FXML
	private AnchorPane rollForm;

	/* ========================================= Composants ================================================ */ /*=========================================*/
	
	/**
     * Pop-Up to change the player's name.
     */
    private Alert changeNickName = new Alert(Alert.AlertType.CONFIRMATION);
    
	/**
     * Pop-Up to change the player's name & new game.
     */
    private Alert changeNickNameNewGame = new Alert(Alert.AlertType.CONFIRMATION);
    
	/* ========================================= Menus & Check Menu Items */
	
	@FXML private MenuBar mainItems;
	
	// New Game
	
	@FXML private MenuItem newPseudo;
	
	@FXML private MenuItem keepPseudo;
	
	// Strategies
	
	@FXML private Menu mainStrategies;
	
	@FXML private CheckMenuItem strategyOne;
	
	@FXML private CheckMenuItem strategyTwo;
	
	@FXML private CheckMenuItem strategyThree;
	
	// Persist Kits
	
	@FXML private Menu mainPersistKits;
	
	@FXML private CheckMenuItem xmlCheckItem;
	
	@FXML private CheckMenuItem mongoCheckItem;
	
	@FXML private CheckMenuItem posgresCheckItem;
	
	// Help
		
	@FXML private Menu help;
	
	@FXML private CheckMenuItem rules;
	
	// Quit
	
	@FXML private MenuItem quit;
	
	/* ========================================= Buttons */
	
	@FXML private Button quitRules;
	
	@FXML private Button start;

	/* ========================================= Text Fileds */

	@FXML private TextField addNickName;
	
	/* ========================================= Labels */

	@FXML private Label displayNameStrategy;

	@FXML private Label errorMessage;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	/**
	 * Method initialize : to init FXML and observe observable backend components.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (location.equals(getClass().getClassLoader().getResource("view/MainView.fxml"))) {
			formNickName.setVisible(true);
			rollForm.setVisible(false);
		}
		if (location.equals(getClass().getClassLoader().getResource("view/MainBar.fxml"))) {

			strategyOne.setSelected(true);
			xmlCheckItem.setSelected(true);

			// Load rules view
			rules.setSelected(true);
			rules.setOnAction(e -> displayRules());
		}
	}

	/* ========================================= Property Change ============================================== */
	
	public void propertyChange(PropertyChangeEvent evt) {
		
	}
	
	/* ========================================= Start Game ============================================== */
	
	/**
	 * Method startGame : to start the dice game.
	 * When user click on the button 'Commencer',
	 * we check if the player's name is a valid one.
	 * Then, if it's a valid pseudo, the game start for this player :
	 * we display the play interface to play the dice game.
	 * 
	 * @param event Event click on the button 'Commencer'.
	 */
	@FXML
    private void startGame(ActionEvent event) {
		try {
			if (! validInput(addNickName.getText())) {
	    		errorMessage.setText("Veuillez saisir un pseudo !");
	    		errorMessage.setTextFill(Color.RED);
	    	} else {
	    		String nickNameFound = addNickName.getText().trim();
	    		
	    		formNickName.setVisible(false);
	    		rollForm.setVisible(true);
	  
	    		DiceGame diceGame = DiceGame.getInstance();
	    		diceGame.changePlayerName(nickNameFound);
	    	}
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Method validInput : to check if a player's name is OK :
	 *  - not null
	 *  - not blank 
	 * 
	 * @param nickName The player's name to check.
	 * 
	 * @return Return true if the player's name is OK, otherwise return false.
	 */
	public boolean validInput(String nickName){
		return ! (nickName == null || nickName.trim().isEmpty());
    }
	
	/* ========================================= Menus ============================================== */
	
	/**
	 * Method closeRollForm : Paramètres > Quitter.
	 * 
	 * @param event Event incoming : want click on X button to close the window. 
	 */
	@FXML
    private void closeRollForm(ActionEvent event) {
		try {
    		System.exit(0);
    	} catch (Exception e) {
    		LOG.severe("Erreur lorsque vous avez voulu quitter le jeu via la barre de menu : " + e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Method closeView : Click on X on the Windows Pop-Up Rules.
	 */
	@FXML
    private void closeView() {
		try {
			// Get a handle to the stage
			Stage stage = (Stage) quitRules.getScene().getWindow();
			
			// Do what you have to do
			stage.close();
		} catch (Exception e) {
			LOG.severe("Erreur lorsque vous avez voulu quitter le jeu via la barre de menu : " + e.getMessage());
    		e.printStackTrace();
		}
    }
	
	/**
	 * Method changeStrategy : to switch and select a new strategy to use to roll dice.
	 */
	@FXML 
	private void changeStrategy() {
		try {
    		// Get the Dice Game instance
    		DiceGame diceGame = DiceGame.getInstance();
    		
    		// Set the clicked strategy
			strategyOne.setOnAction(event -> { // Strategy 1
				if (strategyOne.isSelected()) {
					LOG.info("L'utilisateur a choisi la stratégie 1.");
					diceGame.changeStrategy(RollTwoDiceAtSameTime.STRATEGY_NAME);
					strategyTwo.setSelected(false);
					strategyThree.setSelected(false);
				} else {
					strategyOne.setSelected(true);
				}
			});
			
			strategyTwo.setOnAction(event -> { // Strategy 2
				if (strategyTwo.isSelected()) {
					LOG.info("L'utilisateur a choisi la stratégie 2.");
					diceGame.changeStrategy(RollDieOneFirst.STRATEGY_NAME);
					strategyOne.setSelected(false);
					strategyThree.setSelected(false);
				} else {
					strategyTwo.setSelected(true);
				}
			});
			
			strategyThree.setOnAction(event -> { // Strategy 3
				if (strategyThree.isSelected())	{
					LOG.info("L'utilisateur a choisi la stratégie 3.");
					diceGame.changeStrategy(RollDieTwoFirst.STRATEGY_NAME);
					strategyTwo.setSelected(false);
					strategyOne.setSelected(false);
				} else {
					strategyThree.setSelected(true);
				}
			});
		} catch (Exception e) {
			LOG.severe("Erreur lorsque vous avez voulu changer de stratégie : " + e.getMessage());
    		e.printStackTrace();
		}
	}
	
	/**
	 * Method changePersistKit : to switch and select a new persist kit to save high score (list of 100 first best scores).
	 */
	@FXML 
	private void changePersistKit() {
		try {
    		// Get the Dice Game instance
    		DiceGame diceGame = DiceGame.getInstance();
    		
    		// Set the clicked Persist Kit
    		xmlCheckItem.setOnAction(event -> { // Persist Kit XML
				if (xmlCheckItem.isSelected()) {
					LOG.info("L'utilisateur a choisi le persist kit : " + XMLKit.PERSIST_KIT_NAME);
					
					diceGame.changePersistKit(XMLKit.PERSIST_KIT_NAME);
					
					
					mongoCheckItem.setSelected(false);
					posgresCheckItem.setSelected(false);
				} else {
					xmlCheckItem.setSelected(true);
				}
			});
    		
    		mongoCheckItem.setOnAction(event -> { // Persist Kit MongoDB
				if (mongoCheckItem.isSelected()) {
					LOG.info("L'utilisateur a choisi le persist kit : " + MongoDBKit.PERSIST_KIT_NAME);
					
					diceGame.changePersistKit(MongoDBKit.PERSIST_KIT_NAME);
					
					
					xmlCheckItem.setSelected(false);
					posgresCheckItem.setSelected(false);
				} else {
					mongoCheckItem.setSelected(true);
				}
			});
    		
    		posgresCheckItem.setOnAction(event -> { // Persist Kit PostGreSQl
				if (posgresCheckItem.isSelected())	{
					LOG.info("L'utilisateur a choisi le persist kit : " + PostGreSQLKit.PERSIST_KIT_NAME);
					
					diceGame.changePersistKit(PostGreSQLKit.PERSIST_KIT_NAME);
					
					xmlCheckItem.setSelected(false);
					mongoCheckItem.setSelected(false);
				} else {
					posgresCheckItem.setSelected(true);
				}
			});
		} catch (Exception e) {
			LOG.severe("Erreur lorsque vous avez voulu changer de stratégie : " + e.getMessage());
    		e.printStackTrace();
		}
	} 
	
	/**
	 * Method displayRules : to display the view 'Rules' : Rules.fxml.
	 */
	@FXML 
	void displayRules() {
		try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Rules.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Règles du jeu");
            stage.getIcons().add(new Image("images/dice-game.png"));
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException ioe) {
        	LOG.severe("Impossible de charger la fenêtre 'Rules' : " + ioe.getMessage());
        	ioe.printStackTrace();
        }
	}
	
//	/*
//	 * New Game
//	 */
//	@FXML
//    private void openViewNewNickName() {
//		try {
//		  Stage stage = new Stage();
//		  root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainView.fxml"));
//		  AnchorPane content = new AnchorPane();
//		  Scene scene = new Scene(root);
//		  stage.setScene(scene);
//		  scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
//		  stage.getIcons().add(new Image("images/dice-game.png"));
//		  stage.setTitle("Ajouter un nouveau pseudo");
//		  // stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
//		  stage.show();
//    	} catch (Exception e) {
//    		LOG.severe("Erreur de saisie : "+ e.getMessage());
//    		e.printStackTrace();
//    	}
//    }
	
    /**
     * Method showChangeName : to display Pop-up to change player name.
     */
    public void showChangeName() {
    	try {			
    		// Get the Dice Game instance
    		DiceGame diceGame = DiceGame.getInstance();
    		
    		// Keep same size after first used
    		this.changeNickName.setWidth(600);
    		this.changeNickName.setHeight(430);
    		
    		// Init Pop up title and description
    		this.changeNickName.setTitle("Ajouter un nouveau pseudo");
    		this.changeNickName.setHeaderText("\n\n"
    				+ "Modifier le pseudo pour sauvregarder votre score à la fin de la partie :\n"
    				+ "\n");
    		
    		// Display player's name
    		Label label = new Label("Pseudo actuel (modifiable) :");
    		TextArea textArea = new TextArea();
    		textArea.setText(diceGame.getPlayer().getName());
    		
    		// Construct pop-up content
    		VBox dialogPaneContent = new VBox();
    		dialogPaneContent.getChildren().addAll(label, textArea);
    		
    		// Set content for Dialog Pane
    		this.changeNickName.getDialogPane().setContent(dialogPaneContent);
    		
    		
    		// Remove default ButtonTypes
    		this.changeNickName.getButtonTypes().clear();
    		
    		// Add new ButtonTypes  
    		ButtonType annuler = new ButtonType("Annuler");
    		ButtonType valider = new ButtonType("Valider changement");
    		this.changeNickName.getButtonTypes().addAll(annuler, valider);
    		
    		// option != null.
    		Optional<ButtonType> option = this.changeNickName.showAndWait();
    		
    		if (option.get() == null) {
    			LOG.info("Aucune action n'a été réalisée lors de la demande de changement de pseudo.");
    		} else if (option.get() == annuler) {
    			LOG.info("L'utilisateur a annulé lors de la demande de changement de pseudo.");
    		} else if (option.get() == valider) {
    			if (this.validInput(textArea.getText())) {
    				LOG.info("L'utilisateur modifié son pseudo.");
    				LOG.info("Acien pseudo : " + diceGame.getPlayer().getName());
    				diceGame.changePlayerName( textArea.getText().trim() );
    				LOG.info("Nouveau pseudo : " + diceGame.getPlayer().getName());
				}
    		} else {
    			LOG.info("Aucune action n'a été réalisée lors de la demande de changement de pseudo.");
    		}
		} catch (Exception e) {
			LOG.severe("Erreur lorsque l'utilisateur a voulu changer de pseudo : " + e.getMessage());
    		e.printStackTrace();
		}
    }
    
    public void newGame() {
    	DiceGame.getInstance().newGame();
    }
    
    /**
     * Method showNewGame : to display Pop-up to change player name & new hame.
     */
    public void showNewGame() {
    	try {			
    		// Get the Dice Game instance
    		DiceGame diceGame = DiceGame.getInstance();
    		
    		// Keep same size after first used
    		this.changeNickNameNewGame.setWidth(600);
    		this.changeNickNameNewGame.setHeight(430);
    		
    		// Init Pop up title and description
    		this.changeNickNameNewGame.setTitle("Recommencer une partie avec un nouveau pseudo");
    		this.changeNickNameNewGame.setHeaderText("\n\n"
    				+ "Modifier le pseudo pour sauvregarder votre score à la fin de la partie :\n"
    				+ "\n");
    		
    		// Display player's name
    		Label labelNewGame = new Label("Pseudo actuel (modifiable) :");
    		TextArea textAreaNewGame = new TextArea();
    		textAreaNewGame.setText(diceGame.getPlayer().getName());
    		
    		// Construct pop-up content
    		VBox dialogPaneContent = new VBox();
    		dialogPaneContent.getChildren().addAll(labelNewGame, textAreaNewGame);
    		
    		// Set content for Dialog Pane
    		this.changeNickNameNewGame.getDialogPane().setContent(dialogPaneContent);
    		
    		
    		// Remove default ButtonTypes
    		this.changeNickNameNewGame.getButtonTypes().clear();
    		
    		// Add new ButtonTypes  
    		ButtonType annulerNewGame = new ButtonType("Annuler");
    		ButtonType validerNewGame = new ButtonType("Valider changement");
    		this.changeNickNameNewGame.getButtonTypes().addAll(annulerNewGame, validerNewGame);
    		
    		// option != null.
    		Optional<ButtonType> option = this.changeNickNameNewGame.showAndWait();
    		
    		if (option.get() == null) {
    			LOG.info("Aucune action n'a été réalisée lors de la demande de changement de pseudo.");
    		} else if (option.get() == annulerNewGame) {
    			LOG.info("L'utilisateur a annulé lors de la demande de changement de pseudo.");
    		} else if (option.get() == validerNewGame) {
    				LOG.info("L'utilisateur modifié son pseudo.");
    				LOG.info("Acien pseudo : " + diceGame.getPlayer().getName());
    				diceGame.changePlayerName( textAreaNewGame.getText().trim() );
    				DiceGame.getInstance().newGame();
    				LOG.info("Nouveau pseudo : " + diceGame.getPlayer().getName());
    		} else {
    			LOG.info("Aucune action n'a été réalisée lors de la demande de changement de pseudo.");
    		}
		} catch (Exception e) {
			LOG.severe("Erreur lorsque l'utilisateur a voulu changer de pseudo : " + e.getMessage());
    		e.printStackTrace();
		}
    }
}

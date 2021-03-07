package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
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
    
	/* ========================================= Menus & Check Menu Items */
	
	@FXML private MenuBar mainItems;
	
	@FXML private Menu mainStrategies;
	
	@FXML private CheckMenuItem strategyOne;
	
	@FXML private CheckMenuItem strategyTwo;
	
	@FXML private CheckMenuItem strategyThree;
		
	@FXML private Menu help;
	
	@FXML private CheckMenuItem rules; // TODO check ?
	
	@FXML private MenuItem quit; // TODO Change the place
	
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

	/* ========================================= Initialize ============================================== */ /*=========================================*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (location.equals(getClass().getClassLoader().getResource("view/MainView.fxml"))) {
			formNickName.setVisible(true);
			rollForm.setVisible(false);
			//formNickName.setManaged(true);
			//formNickName.setViewOrder(0);
		}
		if (location.equals(getClass().getClassLoader().getResource("view/MainBar.fxml"))) {

			strategyOne.setSelected(true);
			
			// Load rules view
			rules.setSelected(true);
			rules.setOnAction(e -> displayRules());
		}
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
//			if (!validInput()) {
			if (! validInput(addNickName.getText())) {
	    		errorMessage.setText("Veuillez saisir un pseudo !");
	    		errorMessage.setTextFill(Color.RED);
	    	} else {
	    		String nickNameFound = addNickName.getText().trim();
	    		
	    		formNickName.setVisible(false);
	    		rollForm.setVisible(true);
	  
	    		DiceGame dicegame = DiceGame.getInstance();
	    		dicegame.changePlayerName(nickNameFound);
//	    		dicegame.getPlayer().setScore(0);
	    	}
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	public boolean validInput(String nickName){
//		if (addNickName.getText() == null || addNickName.getText().trim().isEmpty()) {
//			return false;
//		} else {
//			return true;
//		}
		return ! (nickName == null || nickName.trim().isEmpty());
    }
	
	/* ========================================= PropertyChange ============================================== */
	
	public void propertyChange(PropertyChangeEvent evt) {
		
    }
	
	/* ========================================= Main ============================================== */
	
	/**
	 * Method closeRollForm : Paramètres > Quitter.
	 * 
	 * @param event Event incoming : want click on X button to close the window. 
	 */
	@FXML
    private void closeRollForm(ActionEvent event) {
		try {
			//TODO check if a party is in progress
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
			// TODO Appeler la bonne strat dans le back
			strategyOne.setOnAction(event -> {
				if (strategyOne.isSelected()) {
					LOG.info("L'utilisateur a choisi la stratégie 1.");
					strategyTwo.setSelected(false);
					strategyThree.setSelected(false);
				} else {
					strategyOne.setSelected(true);
				}
			});
			strategyTwo.setOnAction(event -> {
				if (strategyTwo.isSelected()) {
					LOG.info("L'utilisateur a choisi la stratégie 2.");
					strategyOne.setSelected(false);
					strategyThree.setSelected(false);
				} else {
					strategyTwo.setSelected(true);
				}
			});
			strategyThree.setOnAction(event -> {
				if (strategyThree.isSelected())	{
					LOG.info("L'utilisateur a choisi la stratégie 3.");
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
     * Method showChangeEncode : to display Pop-up to change player name.
     */
    public void showChangeEncode() {
    	try {			
    		// Get the Dice Game instance
    		DiceGame dicegame = DiceGame.getInstance();
    		
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
    		textArea.setText(dicegame.getPlayer().getName());
    		
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
    				LOG.info("Acien pseudo : " + dicegame.getPlayer().getName());
    				dicegame.changePlayerName( textArea.getText().trim() );
    				LOG.info("Nouveau pseudo : " + dicegame.getPlayer().getName());
				}
    		} else {
    			LOG.info("Aucune action n'a été réalisée lors de la demande de changement de pseudo.");
    		}
		} catch (Exception e) {
			LOG.severe("Erreur lorsque l'utilisateur a voulu changer de pseudo : " + e.getMessage());
    		e.printStackTrace();
		}
    }
}

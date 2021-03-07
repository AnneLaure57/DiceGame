package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.core.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
	
	/* ========================================= Vues ================================================ */ /*=========================================*/

	@FXML 
	private Parent root ;
	
	@FXML
	private AnchorPane formNickName;
	
	@FXML
	private AnchorPane rollForm;

	/* ========================================= Composants ================================================ */ /*=========================================*/
	
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
					strategyTwo.setSelected(false);
					strategyThree.setSelected(false);	
				}
			});
			strategyTwo.setOnAction(event -> {
				if (strategyTwo.isSelected()) {
					strategyOne.setSelected(false);
					strategyThree.setSelected(false);
				}
			});
			strategyThree.setOnAction(event -> {
				if (strategyThree.isSelected())	{
					strategyTwo.setSelected(false);
					strategyOne.setSelected(false);
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
	
	/*
	 * New Game
	 */
	@FXML
    private void openViewNewNickName() {
		try {
		  Stage stage = new Stage();
		  root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainView.fxml"));
		  AnchorPane content = new AnchorPane();
		  Scene scene = new Scene(root);
		  stage.setScene(scene);
		  scene.getStylesheets().add(getClass().getResource("/app.css").toExternalForm());
		  stage.getIcons().add(new Image("images/dice-game.png"));
		  stage.setTitle("Ajouter un nouveau pseudo");
		  //stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
		  stage.show();
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/* ========================================= Start Game ============================================== */
	
	@FXML
    private void startGame(ActionEvent event) {
		try {
			if (!validInput()) {
	    		errorMessage.setText("Veuillez saisir un pseudo !");
	    		errorMessage.setTextFill(Color.RED);
	    	} else {
	    		String nickNameFound = addNickName.getText();
	    		
	    		formNickName.setVisible(false);
	    		rollForm.setVisible(true);
	  
	    		DiceGame dicegame = DiceGame.getInstance();
	    		dicegame.changePlayerName(nickNameFound);
	    		dicegame.getPlayer().setScore(0);
	    	}
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	public boolean validInput(){
		if (addNickName.getText() == null || addNickName.getText().trim().isEmpty()) {
			return false;
		}
        return true;
    }

}

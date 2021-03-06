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
	
	private static final Logger LOG = Logger.getLogger(MainView.class.getName());
	
	@FXML private Parent root ;
	
	@FXML
	private AnchorPane formNickName;
	
	@FXML
	private AnchorPane rollForm;
	
	@FXML private MenuBar mainItems;
	
	@FXML private Menu mainStrategies;
	
	@FXML private CheckMenuItem strategyOne;
	
	@FXML private CheckMenuItem strategyTwo;
	
	@FXML private CheckMenuItem strategyThree;
	
	@FXML private Label displayNameStrategy;
	
	@FXML private Menu help;
	
	@FXML private CheckMenuItem rules;
	
	@FXML private MenuItem quit;
	
	@FXML private Button quitRules;
	
	@FXML private TextField addNickName;
	
	@FXML private Label errorMessage;
	
	@FXML private Button start;

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
			
			//load rules view
			rules.setSelected(true);
			rules.setOnAction(e -> displayRules());
		}
	}
	
	/* ========================================= PropertyChange ============================================== */
	
	public void propertyChange(PropertyChangeEvent evt) {
		
    }
	
	/* ========================================= Main ============================================== */
	
	/*
	 * Click on X on the Windows
	 */
	
	@FXML
    private void closeRollForm(ActionEvent event) {
		try {
			//TODO check if a party is in progress
    		System.exit(0);
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    } 
	
	/*
	 * Paramètres > Quitter
	 */
	
	@FXML
    private void closeView() {
        // get a handle to the stage
        Stage stage = (Stage) quitRules.getScene().getWindow();
        // do what you have to do
        stage.close();
    } 
	
	/*
	 * Change Strategy
	 */
	
	@FXML 
	void changeStrategy() {
		strategyOne.setOnAction(e -> 
    	{
	    	if (strategyOne.isSelected())
	    	{
	    		strategyTwo.setSelected(false);
	    		strategyThree.setSelected(false);
	    		
	    	}
    	});
		strategyTwo.setOnAction(e -> 
    	{
	    	if (strategyTwo.isSelected())
	    	{
	    		strategyOne.setSelected(false);
	    		strategyThree.setSelected(false);
	    	}
    	});
		strategyThree.setOnAction(e -> 
    	{
	    	if (strategyThree.isSelected())
	    	{
	    		strategyTwo.setSelected(false);
	    		strategyOne.setSelected(false);
	    	}
    	});
	} 
	
	
	/*
	 * View Rules
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
        catch (IOException o) {
        	Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Impossible de charger la fenêtre.", o);
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

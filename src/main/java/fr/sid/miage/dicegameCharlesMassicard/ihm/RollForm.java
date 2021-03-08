package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class RollForm implements PropertyChangeListener, Initializable {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : RollForm.
	 */
	private static final Logger LOG = Logger.getLogger(RollForm.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	/**
	 * The player's name. 
	 */
	public String nickNameFound;

	/* ========================================= Vues ================================================== */ /*=========================================*/

	@FXML
	private Parent root ;
	
	@FXML private AnchorPane content;

	@FXML private SplitPane boardDiceGameAnchor;

	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= Labels */
	
	@FXML private Label turnNumber;
	
	@FXML private Label scoreDie1;
	
	@FXML private Label scoreDie2;
	
	@FXML private Label scoreTurn;
	
	@FXML private Label scorePreviousTurn;
	
	@FXML private Label scorePlayer;
	
	/* ========================================= Buttons */
	
	@FXML private Button throwButton;
	
	@FXML private Button undoButton;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */

	/**
	 * Method initialize : to init FXML and observe observable backend components.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.info("RollForm Initialization.");
		
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		diceGame.addPropertyChangeListener(this);
		diceGame.getPlayer().addPropertyChangeListener(this);
		diceGame.getDie1().addPropertyChangeListener(this);
		diceGame.getDie2().addPropertyChangeListener(this);

		actualiseInformationsGame(0);
		actualiseScore(0);
	}
	
	/* ========================================= Property Change ============================================== */
	
	/**
	 *
	 */
	public void propertyChange(PropertyChangeEvent evt) {

		switch(evt.getPropertyName()) {
		case "Tour partie":
			int throwNumber = (int) evt.getNewValue();
			this.turnNumber.setText(String.valueOf(throwNumber));
			break;
			
		case "Valeur dé 1":
			int die1Value = (int) evt.getNewValue();
			this.scoreDie1.setText(String.valueOf(die1Value));
			LOG.info("New face value for Die 1 : " + die1Value);
			break;
			
		case "Valeur dé 2":
			int die2Value = (int) evt.getNewValue();
			this.scoreDie2.setText(String.valueOf(die2Value));
			LOG.info("New face value for Die 2 : " + die2Value);
			break;
			
		case "Score Joueur":
			int scorePlayer = (int) evt.getNewValue();
			this.scorePlayer.setText(String.valueOf(scorePlayer));
			LOG.info("New player's score : " + scorePlayer);
			break;
			
		default :
			break;
		}
    }
	
	/* ========================================= Init FXML components ============================================== */
	
	/**
	 * Method actualiseInformationsGame : to init all labels associated to this view.
	 * 
	 * @param score The game score to display in all labels associated to this view.
	 */
	private void actualiseInformationsGame(int score) {
		turnNumber.setText(Integer.toString(score));
		scoreDie1.setText(Integer.toString(score));
		scoreDie2.setText(Integer.toString(score));
		undoButton.setDisable(true);
	}
	
	/**
	 * Method actualiseScore : to init all labels associated to this view.
	 * 
	 * @param score The game score to display in all labels associated to this view.
	 */
	private void actualiseScore(int score) {
		scoreTurn.setText(Integer.toString(score));
		scorePreviousTurn.setText(Integer.toString(score));
		scorePlayer.setText(Integer.toString(score));
	}
	
	/* ========================================= Display Best Score ================================ */
	
	//TODO
	
	/* ========================================= Roll ============================================== */
	
	/**
	 * Method roll : the method that is call when user push the button 'Lancer'.
	 */
	@FXML
    private void roll() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
			  LOG.info("Throw dice. Throw number : " + diceGame.getThrowNumber());
			  diceGame.throwDice();
			  undoButton.setDisable(false);
			  if (diceGame.getThrowNumber() == DiceGame.POINTS_TO_ADD_WHEN_WIN) {
				throwButton.setDisable(true);
			}
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Lancer' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Method cancel : the method that is call when user push the button 'Annuler'.
	 */
	@FXML
    private void cancel() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
			   LOG.info("Cancel lest dice throw.");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Annuler' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/* ========================================= Cancel Roll ============================================== */

}

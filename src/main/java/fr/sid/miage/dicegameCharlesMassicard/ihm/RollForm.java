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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("---------------");
		System.out.println("change detected");
		System.out.println(evt);
		
		switch(evt.getPropertyName()) {
		case "Tour partie":
			int throwNumber = (int) evt.getNewValue();
			this.turnNumber.setText(String.valueOf(throwNumber));
			break;
			
		case "Valeur dé 1":
			int die1Value = (int) evt.getNewValue();
			System.out.println("COUCOU Dé 1 : " + die1Value);

			System.out.println("COUCOU Dé 1 : " + this.scoreDie1.getText());
			
			this.scoreDie1.setText(String.valueOf(die1Value));
			
			System.out.println("COUCOU Dé 1 : " + this.scoreDie1.getText());
			break;
			
		case "Valeur dé 2":
			int die2Value = (int) evt.getNewValue();
			System.out.println("COUCOU Dé 2 : " + die2Value);
			this.scoreDie2.setText(String.valueOf(die2Value));
			break;
			
		case "Score Joueur":
			int scorePlayer = (int) evt.getNewValue();
			this.scorePlayer.setText(String.valueOf(scorePlayer));
			break;
			
		default :
			break;
		}
    }
	
	/* ========================================= Init FXML components ============================================== */
	
	private void actualiseInformationsGame(int score) {
		turnNumber.setText(Integer.toString(score));
		scoreDie1.setText(Integer.toString(score));
		scoreDie2.setText(Integer.toString(score));
		undoButton.setDisable(true);
	}
	
	private void actualiseScore(int score) {
		scoreTurn.setText(Integer.toString(score));
		scorePreviousTurn.setText(Integer.toString(score));
		scorePlayer.setText(Integer.toString(score));
	}
	
	/* ========================================= Display Best Score ================================ */
	
	//TODO
	
	/* ========================================= Roll ============================================== */
	
	@FXML
    private void roll() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
//			  System.out.println("C'est OK \n Throw number : " + diceGame.getThrowNumber());
			  diceGame.throwDice();
			  undoButton.setDisable(false);
			  if (diceGame.getThrowNumber() == DiceGame.POINTS_TO_ADD_WHEN_WIN) {
				throwButton.setDisable(true);
			}
		  } else {
//			  System.out.println("C'est pas OK");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Lancer' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	@FXML
    private void cancel() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
//			  System.out.println("C'est OK");
		  } else {
//			  System.out.println("C'est pas OK");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Annuler' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/* ========================================= Cancel Roll ============================================== */

}

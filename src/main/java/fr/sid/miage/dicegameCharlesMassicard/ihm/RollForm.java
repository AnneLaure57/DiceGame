package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	
	private static final Logger LOG = Logger.getLogger(RollForm.class.getName());
	
	public String nickNameFound;

	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	@FXML
	private Parent root ;
	
	@FXML private AnchorPane content;
	
	@FXML private SplitPane boardDiceGameAnchor;
	
	@FXML private Label turnNumber;
	
	@FXML private Label scoreDie1;
	
	@FXML private Label scoreDie2;
	
	@FXML private Label scoreTurn;
	
	@FXML private Label scorePreviousTurn;
	
	@FXML private Label scorePlayer;

	private DiceGame diceGame;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.diceGame = DiceGame.getInstance();
		this.diceGame.getDie1().addPropertyChangeListener(this);
		this.diceGame.getDie2().addPropertyChangeListener(this);
		actualiseInformationsGame(0);
		actualiseScore(0);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("change detected");
    }
	
	/* ========================================= Initialisation ================================ */
	
	private void actualiseInformationsGame(int score) {
		turnNumber.setText(Integer.toString(score));
		scoreDie1.setText(Integer.toString(score));
		scoreDie2.setText(Integer.toString(score));
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
		try {
		  if (diceGame.getPlayer().getName() != null) {
			  System.out.println("C'est OK");
			  diceGame.setThrowNumber(1);
		  } else {
			  System.out.println("C'est pas OK");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	@FXML
    private void cancel() {
		try {
			DiceGame diceGame = DiceGame.getInstance();
		  if (diceGame.getPlayer().getName() != null) {
			  System.out.println("C'est OK");
		  } else {
			  System.out.println("C'est pas OK");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/* ========================================= Cancel Roll ============================================== */

}

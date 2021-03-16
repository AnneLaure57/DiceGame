package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.core.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class PlayerView implements PropertyChangeListener, Initializable {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : PlayerView.
	 */
	private static final Logger LOG = Logger.getLogger(PlayerView.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	public String nickNameFound;
	
	/* ========================================= Vues ================================================== */ /*=========================================*/
	
	@FXML private RollForm parent;
	
	@FXML private AnchorPane page;
		
	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= Labels */

	@FXML private Label nickNamePlayer;
	
	@FXML private Label scoreCurrentParty;

	/* ========================================= Initialize ============================================== */ /*=========================================*/
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	/**
	 * Method initialize : to init FXML and observe observable backend components.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.info("PlayerView Initialization.");

		// Get the Dice Game instance
		Player player = DiceGame.getInstance().getPlayer();
		
		player.addPropertyChangeListener(this);
		
		actualiseScore(0);
	}
	
	/* ========================================= Property Change ============================================== */
	
	
	/**
	 * Method propertyChange : receive Events fired when information changes
	 * Those informations are transferred by listeners.
	 * 
	 * @param evt Events fired when informations changed.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		switch(evt.getPropertyName()) {
		case "Nom joueur":
			String playerName = (String) evt.getNewValue();
			this.nickNamePlayer.setText(playerName);
			LOG.info("New player's name : " + playerName);
			break;
			
		case "Score Joueur":
			int scorePlayer = (int) evt.getNewValue();
			this.scoreCurrentParty.setText(String.valueOf(scorePlayer));
			LOG.info("New player's score : " + scorePlayer);
			break;
			
		default :
			break;
		}
    }
	
	/* ========================================= Init FXML components ============================================== */
	
	/**
	 * Method actualiseScore : to init all labels associated to this view.
	 * 
	 * @param score The game score to display in all labels associated to this view.
	 */
	private void actualiseScore(int score) {
		this.scoreCurrentParty.setText(Integer.toString(score));
    }
}

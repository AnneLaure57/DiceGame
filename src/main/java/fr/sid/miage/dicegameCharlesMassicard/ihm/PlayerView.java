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
	
	@FXML private Label bestScore;
	
	@FXML private Label worseScore;

	/* ========================================= Initialize ============================================== */ /*=========================================*/
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.info("PlayerView Initialization.");

		// Get the Dice Game instance
		Player player = DiceGame.getInstance().getPlayer();
		
		player.addPropertyChangeListener(this);
		
		actualiseScore(0);
	}
	
	/* ========================================= Property Change ============================================== */
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
//		System.out.println("event property name : " + evt.getPropertyName());
		
		switch(evt.getPropertyName()) {
		case "Nom joueur":
			String playerName = (String) evt.getNewValue();
			this.nickNamePlayer.setText(playerName);
			break;
			
		case "Score Joueur":
			int scorePlayer = (int) evt.getNewValue();
			this.scoreCurrentParty.setText(String.valueOf(scorePlayer));
			break;
			
		default :
			break;
		}
		
    }
	
	/* ========================================= Init FXML components ============================================== */
	
	private void actualiseScore(int score) {
		scoreCurrentParty.setText(Integer.toString(score));
		bestScore.setText(Integer.toString(score));
		worseScore.setText(Integer.toString(score));
    }
	
	// TODO : à quoi ça sert ?
	public void setView(RollForm rollForm) {
		parent = rollForm;
	}
	
	/*@FXML
	public void setLabelNickName(String nickname) throws IOException {
		/*FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlayerView.fxml"));
		loader.setController(this);
		page = loader.load();
		Scene scene = new Scene(page);
    	LOG.info(nickname);
    	nickNamePlayer.setText(nickname);
    	System.out.println(nickNamePlayer.getText());
    } */
	
}

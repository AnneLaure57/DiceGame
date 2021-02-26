package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
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
public class PlayerView implements PropertyChangeListener, Initializable{
	
	private static final Logger LOG = Logger.getLogger(PlayerView.class.getName());
	
	public String nickNameFound;
	
	private Player player;

	/* ========================================= Components FXML ============================================= */ /*=========================================*/
	
	@FXML private RollForm parent;
	
	@FXML private AnchorPane page;
	
	@FXML private Label labelNickName;
	
	@FXML private Label nickNamePlayer;
	
	@FXML private Label labelScoreCurrentParty;
	
	@FXML private Label scoreCurrentParty;
	
	@FXML private Label labelBestScore;
	
	@FXML private Label bestScore;
	
	@FXML private Label labelWorseScore;
	
	@FXML private Label worseScore;

	/* ========================================= Initialize ============================================== */ /*=========================================*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO find why cause exception
		//player = DiceGame.getInstance().getPlayer();
		//player.addPropertyChangeListener(this);
		//nickNamePlayer.setText(player.getName());
		actualiseScore(0);
		if (location.equals(getClass().getClassLoader().getResource("view/PlayerView.fxml"))) {
			nickNamePlayer.setText("GROSSE PUTE");
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		int score = (int) evt.getNewValue();
		actualiseScore(score);
    }
	
	private void actualiseScore(int score) {
		scoreCurrentParty.setText(Integer.toString(score));
		bestScore.setText(Integer.toString(score));
		worseScore.setText(Integer.toString(score));
    } 
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
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

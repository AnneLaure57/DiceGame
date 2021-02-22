package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class PlayerView implements Initializable{
	
	private static final Logger LOG = Logger.getLogger(PlayerView.class.getName());
	
	public String nickNameFound;

	/* ========================================= Components FXML ============================================= */ /*=========================================*/
	
	@FXML private RollForm parent;
	
	@FXML private AnchorPane page;
	
	@FXML private Label labelNickName;
	
	@FXML private Label nickNamePlayer;
	
	@FXML private Label labelScoreCurrentParty;
	
	@FXML private Label scoreCurrentParty;
	
	@FXML private Label labelBestScore;
	
	@FXML private Label bestScore;
	
	@FXML private Button roll;
	
	@FXML private Button cancel;

	/* ========================================= Initialize ============================================== */ /*=========================================*/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (location.equals(getClass().getClassLoader().getResource("view/PlayerView.fxml"))) {
			nickNamePlayer.setText("salut");
			//set Invisible Label + Buttons 
			//setInvisibleComponents();
		}
	}
	
	public void setView(RollForm rollForm) {
		parent = rollForm;
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	@FXML
	public void setLabelNickName(String nickname) throws IOException {
		/*FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/PlayerView.fxml"));
		loader.setController(this);
		page = loader.load();
		Scene scene = new Scene(page);*/
    	LOG.info(nickname);
    	nickNamePlayer.setText(nickname);
    	System.out.println(nickNamePlayer.getText());
    } 

	
	@FXML
    private void setInvisibleComponents() {
        labelNickName.setVisible(false);
        nickNamePlayer.setVisible(false);
        labelScoreCurrentParty.setVisible(false);
        scoreCurrentParty.setVisible(false);
        labelBestScore.setVisible(false);
        bestScore.setVisible(false);
        roll.setVisible(false);
        cancel.setVisible(false);
    } 


}

package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class PlayerView implements Initializable{

	/* ========================================= Global ================================================ */ /*=========================================*/

	/* ========================================= Components FXML ============================================= */ /*=========================================*/
	
	@FXML private Label labelNickName;
	
	@FXML private Label nickName;
	
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
			
			//set Invisible Label + Buttons 
			//setInvisibleComponents();
			
		}
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	@FXML
    private void setInvisibleComponents() {
        labelNickName.setVisible(false);
        nickName.setVisible(false);
        labelScoreCurrentParty.setVisible(false);
        scoreCurrentParty.setVisible(false);
        labelBestScore.setVisible(false);
        bestScore.setVisible(false);
        roll.setVisible(false);
        cancel.setVisible(false);
    } 


}

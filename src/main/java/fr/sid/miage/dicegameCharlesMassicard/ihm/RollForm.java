package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
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

public class RollForm implements Initializable {
	
	private static final Logger LOG = Logger.getLogger(RollForm.class.getName());
	
	public String nickNameFound;

	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	@FXML
	private Parent root ;
	
	@FXML
	private AnchorPane content;
	
	@FXML private SplitPane boardDiceGameAnchor;
	
	public PlayerView pl = new PlayerView();
	
	// TODO : set the player name
	private static DiceGame diceGame = null;

	@FXML private Button button_lancer;

	/* ========================================= INITIALISATIONS ! ********************************************************************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		//TODO addPropertyChangeListener();
		if (location.equals(getClass().getClassLoader().getResource("view/RollForm.fxml"))) {
			//formNickName.setVisible(true);
			//formNickName.setManaged(true);
			//formNickName.setViewOrder(0);
		}
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		//TODO event with view
    }
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	
	
	/* ========================================= New Game ============================================== */
	
	@FXML
    private void roll() {
		/*try {
		  if (!nickName.equals("Label") || nickName != null) {
			  
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur de saisie : "+ e.getMessage());
    		e.printStackTrace();
    	}*/
    }

}

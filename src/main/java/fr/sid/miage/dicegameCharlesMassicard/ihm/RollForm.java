package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class RollForm implements Initializable {

	/* ========================================= Global ================================================ */ /*=========================================*/
	
	private static final Logger LOG = Logger.getLogger(RollForm.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	@FXML
	private Parent root ;
	
	@FXML private MenuBar mainItems;
	
	@FXML private Menu help;
	
	@FXML private MenuItem rules;
	
	@FXML private Menu quit;
	
	@FXML private Button quitRules;
	
	private static DiceGame diceGame = DiceGame.getInstance();

	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= Radio Buttons */
	
//	@FXML
//	private RadioButton radio_button_stop;
	
	/* ========================================= Buttons */
	
	@FXML
	private Button button_lancer;

	/* ========================================= INITIALISATIONS ! */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (location.equals(getClass().getClassLoader().getResource("view/RollForm.fxml"))) {
			rules.setOnAction(null);
		}
		
	}
	
	@FXML
    private void closeView() {
        // get a handle to the stage
        Stage stage = (Stage) quitRules.getScene().getWindow();
        // do what you have to do
        stage.close();
    } 
	
	@FXML 
	void displayRules(ActionEvent event) {
		rules.setOnAction(e-> {
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
	            logger.log(Level.SEVERE, "Impossible de charger la fenêtre.", e);
	        }
		});
	} 
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= INITIALISATIONS ============================================== */
	
	/**
	 * Méthode permettant d'initialiser les "Radio Buttons" de l'application.
	 * Cette initialisation permet de lier les "Radio Buttons" aux valeurs de la baignoire.
	 */
	public void initRadioButtons() {
		// disableProperty : désactive le "radio_button_stop" quand la simulation est en cours
		button_lancer.disableProperty().unbind();
//		button_lancer.disableProperty().bind(diceGame.start());
	}

	/* ========================================= FONCTIONNEMENT : UTILISATION DE L'APPLI PAR L'UTILISATEUR ============================================== */

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/

}

package fr.sid.miage.dicegameCharlesMassicard.ihm;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class RollForm {

	/* ========================================= Global ================================================ */ /*=========================================*/

	/* ========================================= Attributs ============================================= */ /*=========================================*/
	/**
	 * Baignoire à gérer avec avec la classe "view" et avec laquelle interagit l'utilisateur via le GUI.
	 */
	private static DiceGame diceGame = DiceGame.getInstance();

	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= Radio Buttons */
	
//	@FXML
//	private RadioButton radio_button_stop;
	
	/* ========================================= Buttons */
	
	@FXML
	private Button button_lancer;

	/* ========================================= INITIALISATIONS ! */
	
	@FXML
    public void initialize() { 
		// Initialisation de l'application.
		initRadioButtons();
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

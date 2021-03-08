package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.core.Die;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class DieView implements Initializable, PropertyChangeListener {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : DieView.
	 */
	private static final Logger LOG = Logger.getLogger(DieView.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Vues ================================================== */ /*=========================================*/
	
	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= ImageViews */
	
	@FXML private ImageView die1Face;
	
	@FXML private ImageView die2Face;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	/**
	 * Method initialize : to init FXML and observe observable backend components.
	 */
	public void initialize(URL location, ResourceBundle resources) {
		LOG.info("DieView Initialization.");
		
		// Get the Dice Game instance
		Die die1 = DiceGame.getInstance().getDie1();
		Die die2 = DiceGame.getInstance().getDie2();
		
		die1.addPropertyChangeListener(this);
		die2.addPropertyChangeListener(this);
	}

	/* ========================================= Property Change ============================================== */
		
	/**
	 *
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		
		switch(evt.getPropertyName()) {
		case "Valeur dé 1":
			int die1Value = (int) evt.getNewValue();
			die1Face.setImage(new Image(getClass().getResource(this.checkFaceValue(die1Value)).toExternalForm()));
			LOG.info("The image for Die 1 : " + this.checkFaceValue(die1Value));
			break;
			
		case "Valeur dé 2":
			int die2Value = (int) evt.getNewValue();
			die2Face.setImage(new Image(getClass().getResource(this.checkFaceValue(die2Value)).toExternalForm()));
			LOG.info("The image for Die 2 : " + this.checkFaceValue(die2Value));
			break;
			
		default :
			break;
		}
    }
	
	/* ========================================= Change dice face value image ============================================== */
	
	/**
	 * Method : use to change dice face value image.
	 * 
	 * @param faceValue The face value corresponding to the image file path to display.
	 * 
	 * @return Return the image file path to display.
	 */
	public String checkFaceValue(int faceValue) {
		return "/images/face_" + faceValue + ".png";
	}
}

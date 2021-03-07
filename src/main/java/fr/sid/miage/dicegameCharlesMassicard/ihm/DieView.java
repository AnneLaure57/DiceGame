package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import antlr.debug.Event;
import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.core.Die;
import fr.sid.miage.dicegameCharlesMassicard.core.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

	private Die die;
	
	private DiceGame dicegame;

	/* ========================================= Vues ================================================== */ /*=========================================*/
	
	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= ImageViews */

	@FXML private ImageView dieFace;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	// TODO ???
	public void setDie(Die die) {
		this.die = die;
		this.die.addPropertyChangeListener(this);
	}

	/* ========================================= Property Change ============================================== */
		
	public void propertyChange(PropertyChangeEvent evt) {
		//TODO event with view
		int faceValue = (int) evt.getNewValue();
		
		if(faceValue != 0) {
			dieFace.setImage(new Image(getClass().getResource("/images/" + checkFaceValue(faceValue) + ".png").toExternalForm()));
		} else {
			dieFace.setImage(null);
		}
    }
	
	/* ========================================= Change dice face value image ============================================== */
	
	public String checkFaceValue(int faceValue) {
		String nameImageView = null;
		if  (faceValue == 1) {
			nameImageView.equals("face_one.png");
		} else if (faceValue == 2) {
			nameImageView.equals("face_two.png");
		} else if (faceValue == 3) {
			nameImageView.equals("face_three.png");
		} else if (faceValue == 4) {
			nameImageView.equals("face_four.png");
		} else if (faceValue == 5) {
			nameImageView.equals("face_five.png");
		} else if (faceValue == 6) {
			nameImageView.equals("face_six.png");
		}
		return nameImageView;
	}
}

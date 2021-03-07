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

//	private Die die;
//	
//	private DiceGame dicegame;

	/* ========================================= Vues ================================================== */ /*=========================================*/
	
	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= ImageViews */

//	@FXML private ImageView dieFace;
	
	@FXML private ImageView die1Face;
	
	@FXML private ImageView die2Face;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */
	
	public void initialize(URL location, ResourceBundle resources) {
//		System.out.println("DieView Initialization.");
//		System.out.println("********** \n " + location + "\n" + resources + "\n**********");
		
		// Get the Dice Game instance
		Die die1 = DiceGame.getInstance().getDie1();
		Die die2 = DiceGame.getInstance().getDie2();
		
		die1.addPropertyChangeListener(this);
		die2.addPropertyChangeListener(this);
		
//		checkFaceValue(0);
	}
	
	// TODO ???
//	public void setDie(Die die) {
//		this.die = die;
//		this.die.addPropertyChangeListener(this);
//	}

	/* ========================================= Property Change ============================================== */
		
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
		case "Valeur dé 1":
			int die1Value = (int) evt.getNewValue();
//			dieFace.setImage(new Image(getClass().getResource("/images/face_.png").toExternalForm()));
//			this.checkFaceValue(die1Value);
			die1Face.setImage(new Image(getClass().getResource(this.checkFaceValue(die1Value)).toExternalForm()));
			break;
			
		case "Valeur dé 2":
			int die2Value = (int) evt.getNewValue();
			die2Face.setImage(new Image(getClass().getResource(this.checkFaceValue(die2Value)).toExternalForm()));
			break;
			
		default :
			break;
		}
    }
	
	/* ========================================= Change dice face value image ============================================== */
	
	public String checkFaceValue(int faceValue) {
//		String nameImageView = null;
//		if  (faceValue == 1) {
//			nameImageView = ("face_one");
//		} else if (faceValue == 2) {
//			nameImageView = ("face_two");
//		} else if (faceValue == 3) {
//			nameImageView = ("face_three");
//		} else if (faceValue == 4) {
//			nameImageView = ("face_four");
//		} else if (faceValue == 5) {
//			nameImageView.equals("face_five");
//		} else if (faceValue == 6) {
//			nameImageView.equals("face_six.png");
//		}
//		System.out.println("nameImageView : " + nameImageView);
//		die1Face.setImage(new Image(getClass().getResource("/images/face_" + faceValue + ".png").toExternalForm()));
//		return nameImageView;
		return "/images/face_" + faceValue + ".png";
	}
}

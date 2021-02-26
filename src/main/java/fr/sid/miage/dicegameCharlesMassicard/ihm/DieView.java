package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import antlr.debug.Event;
import fr.sid.miage.dicegameCharlesMassicard.core.Die;
import fr.sid.miage.dicegameCharlesMassicard.core.Player;
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
public class DieView implements Initializable, PropertyChangeListener{
	
	private static final Logger LOG = Logger.getLogger(DieView.class.getName());
	
	@FXML private ImageView dOne;
	
	@FXML private ImageView dTwo;
	
	private Die die;
	
	public void initialize(URL location, ResourceBundle resources) {
		//nothing here
	}
	
	public void setDie(Die die) {
		this.die = die;
		this.die.addPropertyChangeListener(this);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		//TODO event with view
		//TODO call checkFaceValue
		int faceValue1 = (int) evt.getNewValue();
		int faceValue2 = (int) evt.getNewValue();
		
		if(faceValue1 != 0 && faceValue2 != 0) {
			dOne.setImage(new Image(getClass().getResource("/images/" + checkFaceValue(faceValue1) + ".png").toExternalForm()));
			dTwo.setImage(new Image(getClass().getResource("/images/" + checkFaceValue(faceValue2) + ".png").toExternalForm()));
		} else {
			dOne.setImage(null);
			dTwo.setImage(null);
		}
    }
	
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

package fr.sid.miage.dicegameCharlesMassicard.ihm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;
import fr.sid.miage.dicegameCharlesMassicard.core.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    }
}

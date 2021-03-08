package fr.sid.miage.dicegameCharlesMassicard.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.utils.Randomizer;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class Die {

	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : Die.
	 */
	private static final Logger LOG = Logger.getLogger(Die.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The number of the die.
	 */
	private int dieNumber;
	
	/**
	 * The face value of the die.
	 */
	private int faceValue = 1;
	
	/**
	 * Observable
	 */
	PropertyChangeSupport supportDie;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/**
	 * Constructeur with a number : ex : first die, second die...
	 * When Die is created, the face value is initialized randomly.
	 * 
	 * @param dieNumber The number of the die.
	 */
	public Die(int dieNumber) {
		LOG.info("An Die has just been created.");
		this.setDieNumber(dieNumber);
		this.supportDie = new PropertyChangeSupport(this);
		this.setFaceValue(Randomizer.getInstance().getValue(5) + 1);
	}

	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Observable
	 * 
	 * @return The new value of die face value.
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("Die " + this.getDieNumber() + " : add PropertyChangeListener : " + pcl.getClass().toString());
		supportDie.addPropertyChangeListener("Valeur dé " + this.getDieNumber(), pcl);
    }
		
	/**
	 * Method roll : to roll the dice and change the face value randomly.
	 * 
	 * @return The new value of die face value.
	 */
	public boolean roll() {
		try {
			this.setFaceValue(Randomizer.getInstance().getValue(5) + 1);
			LOG.info("The die rolls, new face value is : " + this.getFaceValue());
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'roll' from Die class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/* ========================================= Accesseurs ============================================ */ /*=========================================*/
	
	/**
	 * @return the faceValue
	 */
	public int getFaceValue() {
		return faceValue;
	}

	/**
	 * @param faceValue the faceValue to set
	 */
	public void setFaceValue(int faceValue) {
		// Do nothing if this.faceValue = faceValue before
		supportDie.firePropertyChange("Valeur dé " + this.getDieNumber(), this.faceValue, faceValue);
		// Notify change after
		this.faceValue = faceValue;
	}

	/**
	 * @return the dieNumber
	 */
	public int getDieNumber() {
		return dieNumber;
	}

	/**
	 * @param dieNumber the dieNumber to set
	 */
	public void setDieNumber(int dieNumber) {
		this.dieNumber = dieNumber;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

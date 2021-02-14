package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.Random;
import java.util.logging.Logger;

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
	 * The face value of the die.
	 */
	private int faceValue = 1;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/**
	 * No Args Constructor.
	 * When Die is created, the face value is initialized at value 1.
	 */
	public Die() {
		LOG.info("An Die has just been created.");
//		this.setFaceValue(new Random().nextInt(7));
		this.setFaceValue(1);
	}

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method roll : to roll the dice and change the face value randomly.
	 * 
	 * @return The new value of die face value.
	 */
	public boolean roll() {
		try {
			this.setFaceValue(new Random().nextInt(7));
			LOG.info("The die rolls, new face value is : " + getFaceValue());
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method roll from Die class :");
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
		this.faceValue = faceValue;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

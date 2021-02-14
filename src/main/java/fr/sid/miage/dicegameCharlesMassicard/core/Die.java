package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class Die {

	/* ========================================= Global ================================================ */ /*=========================================*/

	private static final Logger LOG = Logger.getLogger(Die.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private int faceValue = 1;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	public Die() {
		LOG.info("An Die has just been created.");
		this.setFaceValue(new Random().nextInt(7));
	}

	/* ========================================= Methodes ============================================== */ /*=========================================*/

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

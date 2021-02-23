package fr.sid.miage.dicegameCharlesMassicard.utils;

import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 * Randomizer
 */

public class checkFaceValue {
	
/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Randomizer.
	 */
	private static final Logger LOG = Logger.getLogger(Randomizer.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/**
	 * No Args Constructor.
	 * When Randomizer is created.
	 */
	private checkFaceValue() {
		LOG.info("CheckFaceValue Useful");
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method getInstance : to get the Randomizer's instance.
	 * @return 
	 * 
	 * @return The Randomizer's instance.
	 */
	public int checkFaceValue(String nameImageView) {
		int value = 0;
		if  (nameImageView.equals("face_one.png")) {
			value = 1;
		} else if (nameImageView.equals("face_two.png")) {
			value = 2;
		} else if (nameImageView.equals("face_three.png")) {
			value = 3;
		} else if (nameImageView.equals("face_four.png")) {
			value = 4;
		} else if (nameImageView.equals("face_five.png")) {
			value = 5;
		} else if (nameImageView.equals("face_six.png")) {
			value = 6;
		}
		return value;
	}
}

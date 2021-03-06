package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */

public class RollDices implements RollStrategy{
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : RollDices.
	 */
	private static final Logger LOG = Logger.getLogger(RollDices.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	@Override
	public boolean rollDices(Die die1, Die die2) {
		try {
			// Roll the dice.
			die1.roll();
			die2.roll();
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the pattern Strategy in class \"RollDices\" :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
}

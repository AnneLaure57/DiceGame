package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * The strategy to use to roll dice : Roll two dice at the same time.
 */
public class RollTwoDiceAtSameTime implements RollStrategy{
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : RollDices.
	 */
	private static final Logger LOG = Logger.getLogger(RollTwoDiceAtSameTime.class.getName());
	
	/**
	 * The strategy name to call, refer and use this strategy in a switch case.
	 * Specialy usefull for switch case because you can't use RollTwoDiceAtSameTime.class.getName().
	 */
	public static final String STRATEGY_NAME = "RollTwoDiceAtSameTime";
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method rollDices : roll dice using a specific strategy.
	 * Roll two dice at the same time.
	 * 
	 * @param die1 The first die used for the Dice Game.
	 * @param die2 The second die used for the Dice Game.
	 * 
	 * @return Return true if you succed to roll dice, otherwise return false.
	 */
	@Override
	public boolean rollDices(Die die1, Die die2) {
		try {
			// Roll two dice at the same time.
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

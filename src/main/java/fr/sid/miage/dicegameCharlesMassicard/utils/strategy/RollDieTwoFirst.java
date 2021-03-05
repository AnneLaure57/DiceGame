package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */

public class RollDieTwoFirst implements RollStrategy {

	/**
	 * Logger for this class : RollDieTwoFirst.
	 */
	private static final Logger LOG = Logger.getLogger(RollDieTwoFirst.class.getName());

	@Override
	public boolean rollDices(Die die1, Die die2) {
		try {
			//set Die Two first value
			die2.roll();
			
			//pause 
			TimeUnit.SECONDS.sleep(3);
			
			//set Die One value
			die1.roll();
			
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the pattern Strategy in class \"RollDieTwoFirst\" :");
			LOG.severe(e.toString());
			return false;
		}
	}
}

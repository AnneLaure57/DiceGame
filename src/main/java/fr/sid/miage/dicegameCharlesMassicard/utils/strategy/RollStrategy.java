package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * The strategy to use to roll dice.
 */
public interface RollStrategy {
	
	/**
	 * Method rollDices : roll dice using a specific strategy.
	 * 
	 * @param die1 The first die used for the Dice Game.
	 * @param die2 The second die used for the Dice Game.
	 * 
	 * @return Return true if you succed to roll dice, otherwise return false.
	 */
	public boolean rollDices (Die die1, Die die2);
}

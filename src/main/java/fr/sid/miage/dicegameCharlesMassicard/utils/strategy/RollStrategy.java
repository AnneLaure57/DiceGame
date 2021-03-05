package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */

public interface RollStrategy {
	
	//Name of the strategy method
	public boolean rollDices (Die die1, Die die2);

}

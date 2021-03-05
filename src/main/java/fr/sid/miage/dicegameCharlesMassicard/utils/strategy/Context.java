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

public class Context {
	
	/**
	 * Logger for this class : RollDices.
	 */
	private static final Logger LOG = Logger.getLogger(RollDices.class.getName());
	
	private RollStrategy strategy;

   public Context(RollStrategy strategy){
      this.strategy = strategy;
   }

   public boolean executeStrategy(Die die1, Die die2){
      return strategy.rollDices(die1, die2);
   }

}

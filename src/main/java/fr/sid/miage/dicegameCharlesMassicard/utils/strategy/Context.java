package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 * Context used to choose a strategy to use to roll dice.
 */
public class Context {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : RollDices.
	 */
	private static final Logger LOG = Logger.getLogger(Context.class.getName());
	
	public static final int NB_SEC_BEFORE_ANOTHER_DIE_THROW = 3;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The strategy to use to roll dice.
	 */
	private RollStrategy strategy;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * Create a context using a strategy to use to roll dice.
	 * 
	 * @param strategy The strategy to use to roll dice.
	 */
	public Context(RollStrategy strategy){
		LOG.info("Create a context with the strategy : " + strategy.getClass().getName());
		this.strategy = strategy;
	}

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method executeStrategy : roll dice using the chooed strategy.
	 * 
	 * @param die1 The first die used for the Dice Game.
	 * @param die2 The second die used for the Dice Game.
	 * 
	 * @return Return true if you succed to roll dice, otherwise return false.
	 */
	public boolean executeStrategy(Die die1, Die die2){
		return strategy.rollDices(die1, die2);
	}

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
}

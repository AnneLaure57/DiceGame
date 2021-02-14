package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 */
public class DiceGame {
	/* ========================================= Global ================================================ */ /*=========================================*/

	private static final Logger LOG = Logger.getLogger(DiceGame.class.getName());
	
	private static DiceGame INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private Die die1;
	private Die die2;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	private DiceGame() {
		LOG.info("An DiceGame has just been created with twoo dice are initialized.");
		this.setDie1(new Die());
		this.setDie2(new Die());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	public void start() {
		
	}
	
	public static synchronized DiceGame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DiceGame();
		}
		return INSTANCE;
	}

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/
	
	/**
	 * @return the die1
	 */
	public Die getDie1() {
		return die1;
	}

	/**
	 * @param die1 the die1 to set
	 */
	public void setDie1(Die die1) {
		this.die1 = die1;
	}

	/**
	 * @return the die2
	 */
	public Die getDie2() {
		return die2;
	}

	/**
	 * @param die2 the die2 to set
	 */
	public void setDie2(Die die2) {
		this.die2 = die2;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

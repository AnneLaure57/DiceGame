package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 */
public class DiceGame {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : DiceGame.
	 */
	private static final Logger LOG = Logger.getLogger(DiceGame.class.getName());
	
	/**
	 * The instance of the singleton DiceGame. 
	 */
	private static DiceGame INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * Fist die of the DiceGame.
	 */
	private Die die1;
	
	/**
	 * Second die of the DiceGame.
	 */
	private Die die2;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * When DiceGame is created, two dice are initialized to play.
	 */
	private DiceGame() {
		LOG.info("An DiceGame has just been created with twoo dice are initialized.");
		this.setDie1(new Die());
		this.setDie2(new Die());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method to start a game.
	 */
	public void start() {
		
	}
	
	/**
	 * Method getInstance : to get the DiceGame's instance.
	 * 
	 * @return The DiceGame's instance.
	 */
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

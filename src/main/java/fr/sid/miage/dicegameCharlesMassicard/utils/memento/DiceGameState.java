package fr.sid.miage.dicegameCharlesMassicard.utils.memento;

import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 * Memento class.
 */
public class DiceGameState {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : DiceGame.
	 */
	private static final Logger LOG = Logger.getLogger(DiceGameState.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * Player who plays the game DiceGame.
	 */
	private int player;
	
	/**
	 * Fist die of the DiceGame.
	 */
	private int die1;
	
	/**
	 * Second die of the DiceGame.
	 */
	private int die2;
	
	/**
	 * The throw number for the game.
	 * Begin at 0.
	 * Max value 10.
	 */
	private int throwNumber;

	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * All args constructor.
	 * Create a snapshot dice game via Memento pattern
	 * 
	 * @param player Save the player.
	 * @param die1 Save the die 1.
	 * @param die2 Save the die 2.
	 * @param throwNumber Save the throw number.
	 */
	public DiceGameState(int player, int die1, int die2, int throwNumber) {
		LOG.info("Create a snapshot of dice game via Memento pattern.");
		this.player = player;
		this.die1 = die1;
		this.die2 = die2;
		this.throwNumber = throwNumber;
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/**
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * @return the die1
	 */
	public int getDie1() {
		return die1;
	}

	/**
	 * @return the die2
	 */
	public int getDie2() {
		return die2;
	}

	/**
	 * @return the throwNumber
	 */
	public int getThrowNumber() {
		return throwNumber;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

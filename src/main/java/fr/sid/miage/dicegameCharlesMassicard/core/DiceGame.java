package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.utils.TooMuchDiceThrowException;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 */
public class DiceGame {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : DiceGame.
	 */
	private static final Logger LOG = Logger.getLogger(DiceGame.class.getName());
		
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * Fist die of the DiceGame.
	 */
	private Die die1;
	
	/**
	 * Second die of the DiceGame.
	 */
	private Die die2;
	
	/**
	 * The throw number for the game.
	 * Begin at 0.
	 * Max value 10.
	 */
	private int throwNumber;
	
	/**
	 * Points earned by the player during the game.
	 * Begin at 0.
	 * Add 10 points when the sum of dices is equal to 7.
	 */
	private int playerPoints;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * When DiceGame is created, two dice are initialized to play.
	 */
	private DiceGame() {
		LOG.info("A DiceGame has just been created with twoo dice are initialized.");
		this.setDie1(new Die());
		this.setDie2(new Die());
		this.setThrowNumber(0);
		this.setPlayerPoints(0);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method newGame : to start a new game.
	 */
	public boolean newGame() {
		try {
			this.setThrowNumber(0);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'newGame' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method to start a game.
	 */
	public boolean throwDice() {
		try {
			if (this.getThrowNumber() >= 10) {
				throw new TooMuchDiceThrowException("You exceed the maximum throw number of : ");
			}
			this.throwNumber++;
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'throwDice' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method to start a game.
	 */
	public void start() {
		
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

	/**
	 * @return the throwNumber
	 */
	public int getThrowNumber() {
		return throwNumber;
	}

	/**
	 * @param throwNumber the throwNumber to set
	 */
	public void setThrowNumber(int throwNumber) {
		this.throwNumber = throwNumber;
	}

	/**
	 * @return the playerPoints
	 */
	public int getPlayerPoints() {
		return playerPoints;
	}

	/**
	 * @param playerPoints the playerPoints to set
	 */
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

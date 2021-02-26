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
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static DiceGame INSTANCE = null;
	
	/**
	 * The maximum number of throw in a game.
	 */
	private static final int MAX_NUMBER_OF_THROWS = 10;
	
	/**
	 * The sum of the dice's face value for which the player win some points.
	 */
	private static final int DICE_SUM_TO_WIN = 7;
	
	/**
	 * Number of points to add when the sum of dice's face value allow to win.
	 */
	private static final int POINTS_TO_ADD_WHEN_WIN = 7;
		
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * Player who plays the game DiceGame.
	 */
	private Player player;
	
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
	private DiceGame(String playerName) {
		LOG.info("A DiceGame has just been created with twoo dice are initialized.");
		this.setPlayer(new Player(playerName));
		this.setDie1(new Die());
		this.setDie2(new Die());
		this.setThrowNumber(0);
		this.setPlayerPoints(0);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized DiceGame getInstance() {
		if (INSTANCE == null) {
			//INSTANCE = new DiceGame("");
			INSTANCE = null;
			LOG.info("A DiceGame's Instance is created.");
		}
		return INSTANCE;
	}
	
	/**
	 * Method newGame : to start a new game.
	 * 
	 * @return Return true if the dice's throw is a success, otherwise return false.
	 */
	public boolean newGame() {
		try {
			this.setThrowNumber(0);
			this.getPlayer().setScore(0);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'newGame' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method throwDice : to throw dice.
	 * 
	 * @return Return true if the dice's throw is a success, otherwise return false.
	 */
	public boolean throwDice() {
		try {
			// Increase the number of throws.
			if (this.getThrowNumber() >= MAX_NUMBER_OF_THROWS) {
				throw new TooMuchDiceThrowException("You exceed the maximum throw number of : ");
			}
			this.throwNumber++;
			
			// TODO : Momento & Strategie
			
			// Roll the dice.
			die1.roll();
			die2.roll();
			
			// If the player wins (The sum of the dice's face value for which the player win some points)
			// then increase the player's score.
			if (die1.getFaceValue() + die2.getFaceValue() == DICE_SUM_TO_WIN) {
				this.getPlayer().increaseScore(POINTS_TO_ADD_WHEN_WIN);
			}
			
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'throwDice' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * @return Return true if the player's score is saved, otherwise return false.
	 */
	public boolean savePlayerScore() {
		try {
			// TODO
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'save' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method endGame : to start a game.
	 * 
	 * @return Return true if the game ends correctly, otherwise return false.
	 */
	public boolean endGame() {
		this.savePlayerScore();
		this.newGame();
		try {
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'save' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
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

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

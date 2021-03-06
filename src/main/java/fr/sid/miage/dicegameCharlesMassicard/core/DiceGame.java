package fr.sid.miage.dicegameCharlesMassicard.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	private static final int POINTS_TO_ADD_WHEN_WIN = 10;
		
	/**
	 * TODO javadoc
	 */
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
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
	
	private String strategyToUseToRollDice;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * When DiceGame is created, two dice are initialized to play.
	 */
	private DiceGame() {
		LOG.info("A DiceGame has just been created with twoo dice are initialized.");
		this.setPlayer(new Player(""));
		this.setDie1(new Die());
		this.setDie2(new Die());
		this.setThrowNumber(0);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Observable
	 */
	private PropertyChangeSupport supportDiceGame;
	
	/**
	 * Observable
	 * 
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("add PropertyChangeListener : " + pcl.getClass().toString());
		supportDiceGame.addPropertyChangeListener("Tour partie", pcl);
    }
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized DiceGame getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DiceGame();
			LOG.info("A DiceGame's Instance is created.");
		}
		return INSTANCE;
	}
	
	public static void resetDiceGame() {
		INSTANCE = null;
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
	
	public boolean changePlayerName(String playerName) {
		try {
			this.getPlayer().setName(playerName);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'changePlayerName' from DiceGame class :");
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
				throw new TooMuchDiceThrowException("You exceed the maximum throw number for this game. You already throw dice " + this.throwNumber + " times.");
			}
			this.throwNumber++;
			
			// TODO : Momento
			
			//TODO discuss with Louis For Strategy
			//How call the right strat ? With number ? Boolean ? in params ?
			// From front -> RadioButtons ? MenuItem ?
			// put switch case ? conditions if/else ?
			
			//Context context = null;
			//context = new Context(new RollDices());
			//context.executeStrategy(die1, die2)
			
			//context = new Context(new rollDieOneFirst());
			//context.executeStrategy(die1, die2)
			
			//context = new Context(new rollDieTwoFirst());
			//context.executeStrategy(die1, die2)
			
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
		//Do nothing if this.throwNumber=throwNumber before
		//supportDiceGame.firePropertyChange("Tour partie", this.throwNumber, throwNumber);
		this.throwNumber = throwNumber;
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

	/**
	 * @return the strategyToUseToRollDice
	 */
	public String getStrategyToUseToRollDice() {
		return strategyToUseToRollDice;
	}

	/**
	 * @param strategyToUseToRollDice the strategyToUseToRollDice to set
	 */
	public void setStrategyToUseToRollDice(String strategyToUseToRollDice) {
		this.strategyToUseToRollDice = strategyToUseToRollDice;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

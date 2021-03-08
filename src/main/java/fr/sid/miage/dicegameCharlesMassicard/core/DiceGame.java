package fr.sid.miage.dicegameCharlesMassicard.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.persist.HighScoreXML;
import fr.sid.miage.dicegameCharlesMassicard.persist.MongoDBKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.PersistKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.PostGreSQLKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.XMLKit;
import fr.sid.miage.dicegameCharlesMassicard.utils.TooMuchDiceThrowException;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.Context;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieOneFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieTwoFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollTwoDiceAtSameTime;
import javafx.application.Platform;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * A game with two dice to throw.
 * 
 * You throw the couple of dice 10 times.
 * 
 * Each time you throw dice, 
 * if you the sum of dice's face value is equal to 7,
 * then you win 10 points.
 * 
 * If you have a good final score,
 * then you can reach the top ladder!
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
	public static final int POINTS_TO_ADD_WHEN_WIN = 10;
		
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * Allow DiceGame to be an Observable.
	 */
	private PropertyChangeSupport supportDiceGame;
	
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
	 * The stratregy to use to roll dice.
	 * Example : Die one rolls first, then Die two rolls some seconds after dice one.
	 * Other example : Two dice roll together at the same time.
	 */
	private Context strategyToUseToRollDice;
	
	/**
	 * To know if it's a scheldule strategy.
	 * If it's a scheldule strategy,
	 * then wait before calculate score and throw number.
	 */
	private boolean useScheduleStrategy;
	
	/**
	 * The list of 100 first best scores.
	 */
	private HighScore highScore;
	
	/**
	 * The way to persist the high score list of 100 first best scores.
	 */
	private PersistKit persistKit;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * When DiceGame is created, two dice are initialized to play.
	 * 
	 * Allow DiceGame to be an Observable :
	 * We add a PropertyChangeSupport to our DiceGame instance.
	 */
	private DiceGame() {
		LOG.info("A DiceGame has just been created with two dice are initialized.");
		
		// Observable
		this.supportDiceGame = new PropertyChangeSupport(this);
		
		// Init core iniformations (player, dice and throw number)
		this.setPlayer(new Player(""));
		this.setDie1(new Die(1));
		this.setDie2(new Die(2));
		this.setThrowNumber(0);
		
		// Choose the first strategy, by default
		this.setStrategyToUseToRollDice(new Context(new RollTwoDiceAtSameTime()));
		this.setUseScheduleStrategy(false);
		
		// Choose the first persist kit, by default
		this.setPersistKit(persistKit = new XMLKit());
		this.setHighScore(this.getPersistKit().makeKit());
		
		// Load previous games high score 
		this.getHighScore().load();
		
		// Display/log previous games high score to check
		HighScoreXML highScore = (HighScoreXML) this.getHighScore();
		highScore.getScores().forEach(System.out::println);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method addPropertyChangeListener : allow DiceGame to be an Observable.
	 * 
	 * @param pcl PropertyChangeListener to observe the DiceGame modifications.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("DiceGame : add PropertyChangeListener : " + pcl.getClass().toString());
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
	 * Put the throwNumber at 0.
	 * Put the player's score at 0.
	 * 
	 * @return Return true if the dice's throw is a success, otherwise return false.
	 */
	public boolean newGame() {
		try {
			this.setThrowNumber(0);
			this.getPlayer().setScore(0);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'newGame' from DiceGame class.");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method changePlayerName : to set a new player name (or int this name).
	 * 
	 * @param playerName The new player's name to set into player's informations.
	 * 
	 * @return Return true if the player's name is changed, otherwise return false.
	 */
	public boolean changePlayerName(String playerName) {
		try {
			this.getPlayer().setName(playerName);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'changePlayerName' from DiceGame class.");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method changeStrategy : to use a new strategy.
	 * This strategy is used to roll dice.
	 * Example : Die one rolls first, then Die two rolls some seconds after dice one.
	 * Other example : Two dice roll together at the same time.
	 * 
	 * @param newStrategy This strategy to use to roll dice.
	 * 
	 * @return Return true if the strategy is changed, otherwise return false.
	 */
	public boolean changeStrategy(String newStrategy) {
		try {
			LOG.info("Use the strategy number : " + newStrategy);
			
			switch (newStrategy) {
			case RollTwoDiceAtSameTime.STRATEGY_NAME:
				LOG.info("Now, you use this strategy : " + RollTwoDiceAtSameTime.class.getName());
				this.setStrategyToUseToRollDice(new Context(new RollTwoDiceAtSameTime()));
				this.setUseScheduleStrategy(false);
				break;
			
			case RollDieOneFirst.STRATEGY_NAME:
				LOG.info("Now, you use this strategy : " + RollDieOneFirst.class.getName());
				this.setStrategyToUseToRollDice(new Context(new RollDieOneFirst()));
				this.setUseScheduleStrategy(true);
				break;
			
			case RollDieTwoFirst.STRATEGY_NAME:
				LOG.info("Now, you use this strategy : " + RollDieTwoFirst.class.getName());
				this.setStrategyToUseToRollDice(new Context(new RollDieTwoFirst()));
				this.setUseScheduleStrategy(true);
				break;
			
			default:
				break;
			}
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'changeStrategy' from DiceGame class.");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method changeStrategy : to use a new Persist Kit.
	 * This Persist Kit is used to save high score (list of 100 first best scores).
	 * So persist high score via :
	 *  - XML
	 *  - PostGreSQL
	 *  - MongoDB
	 * 
	 * @param newPersistKit This Persist Kit to save high score (list of 100 first best scores).
	 * 
	 * @return Return true if the Persist Kit is changed, otherwise return false.
	 */
	public boolean changePersistKit(String newPersistKit) {
		try {
			LOG.info("Use the strategy number : " + newPersistKit);
			
			switch (newPersistKit) {
			case XMLKit.PERSIST_KIT_NAME:
				LOG.info("Now, you use this Persist Kit : " + XMLKit.class.getName());
				this.setPersistKit(persistKit = new XMLKit());
				break;
			case MongoDBKit.PERSIST_KIT_NAME:
				LOG.info("Now, you use this Persist Kit : " + MongoDBKit.class.getName());
				this.setPersistKit(persistKit = new MongoDBKit());
				break;
			case PostGreSQLKit.PERSIST_KIT_NAME:
				LOG.info("Now, you use this Persist Kit : " + PostGreSQLKit.class.getName());
				this.setPersistKit(persistKit = new PostGreSQLKit());
				break;
			default:
				break;
			}
			
			// Use the persist kit to save high scores
			this.setHighScore(this.getPersistKit().makeKit());
			
			// Load saved high score (list of 100 first best scores).
			this.getHighScore().load();
			
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'changePersistKit' from DiceGame class.");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method increaseThrowNumber : increase the throw number by 1.
	 * 
	 * @return Return true if the throw number increase is a success, otherwise return false.
	 */
	private boolean increaseThrowNumber() {
		try {
			this.setThrowNumber(this.getThrowNumber() + 1);
			if (this.getThrowNumber() == MAX_NUMBER_OF_THROWS) { // TODO
				this.savePlayerScore();
			}
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'changeStrategy' from DiceGame class.");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method checkIfPlayerWins : to check if the player wins.
	 * 
	 * @return Return true if the check is a success, otherwise return false.
	 */
	private boolean checkIfPlayerWins() {
		try {
			if (die1.getFaceValue() + die2.getFaceValue() == DICE_SUM_TO_WIN) {
				this.getPlayer().increaseScore(POINTS_TO_ADD_WHEN_WIN);
			}
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'checkIfPlayerWins' from DiceGame class.");
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
			
			// TODO : Momento
			
			// Roll all dice.
			this.getStrategyToUseToRollDice().executeStrategy(die1, die2);
			
			if (this.isUseScheduleStrategy()) {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						Platform.runLater(() -> {
							// Increase the number of throws.
							increaseThrowNumber();
							
							// If the player wins (The sum of the dice's face value for which the player win some points)
							// then increase the player's score.
							checkIfPlayerWins();
							timer.cancel();
						});
					}
				}, Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW * 1000 + 50);
			} else {
				// Increase the number of throws.
				this.increaseThrowNumber();
				
				// If the player wins (The sum of the dice's face value for which the player win some points)
				// then increase the player's score.
				this.checkIfPlayerWins();
			}
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'throwDice' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method savePlayerScore : to save the player score.
	 * The player's score is inserted in the high score list.
	 * If the player's score is one of the 100 first best scores,
	 * then the player's score is saved in the 100 first best scores (and persist in database),
	 * and the new 101 best scores is deleted.
	 * 
	 * @return Return true if the player's score is saved, otherwise return false.
	 */
	private boolean savePlayerScore() {
		try {
			this.getHighScore().add(this.getPlayer().getName(), this.getPlayer().getScore());
			this.getHighScore().save();
			
			// Load previous games high score 
			this.getHighScore().load();
			
			// Display/log previous games high score to check
			
//			HighScoreXML highScore = (HighScoreXML) this.getHighScore();
//			HighScoreMongoDB highScore = (HighScoreMongoDB) this.getHighScore();
//			HighScorePostGreSQL highScore = (HighScorePostGreSQL) this.getHighScore();
			
//			highScore.getScores().forEach(System.out::println);
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'save' from DiceGame class :");
			LOG.severe(e.toString());
			return false;
		}
	}
	
	/**
	 * Method endGame : to end a game.
	 * So saved the player score and start a new game.
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
		//Do nothing if this.throwNumber = throwNumber before
		System.out.println("set throw number : " + throwNumber);
		supportDiceGame.firePropertyChange("Tour partie", this.throwNumber, throwNumber);
		// Notify change after
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
	public Context getStrategyToUseToRollDice() {
		return strategyToUseToRollDice;
	}

	/**
	 * @param strategyToUseToRollDice the strategyToUseToRollDice to set
	 */
	public void setStrategyToUseToRollDice(Context strategyToUseToRollDice) {
		this.strategyToUseToRollDice = strategyToUseToRollDice;
	}

	/**
	 * @return the useScheduleStrategy
	 */
	public boolean isUseScheduleStrategy() {
		return useScheduleStrategy;
	}

	/**
	 * @param useScheduleStrategy the useScheduleStrategy to set
	 */
	public void setUseScheduleStrategy(boolean useScheduleStrategy) {
		this.useScheduleStrategy = useScheduleStrategy;
	}

	/**
	 * @return the highScore
	 */
	public HighScore getHighScore() {
		return highScore;
	}

	/**
	 * @param highScore the highScore to set
	 */
	public void setHighScore(HighScore highScore) {
		this.highScore = highScore;
	}

	/**
	 * @return the persistKit
	 */
	public PersistKit getPersistKit() {
		return persistKit;
	}

	/**
	 * @param persistKit the persistKit to set
	 */
	public void setPersistKit(PersistKit persistKit) {
		this.persistKit = persistKit;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

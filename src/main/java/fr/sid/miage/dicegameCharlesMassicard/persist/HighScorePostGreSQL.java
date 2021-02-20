package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 * Concrete Product
 * https://www.tutorialspoint.com/postgresql/postgresql_java.htm
 * 
 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
 * 
 * Use PostGreSQL Docker : 
 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
 *  * otherwise : docker start postgres
 *  
 * If you want to manage your PostGreSQL Docker : docker run -it --rm --link postgres:postgres postgres psql -h postgres -U postgres
 * Password : riovas
 * To list databases : \l
 * Request example : select * from utilisateur;
 * To quit : \q
 */
public class HighScorePostGreSQL implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScorePostGreSQL.class.getName());
	
	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static HighScorePostGreSQL INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The list of best scores to saved.
	 */
	private List<Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * Initiate the list of best scores. 
	 */
	private HighScorePostGreSQL() {
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized HighScorePostGreSQL getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScorePostGreSQL();
			LOG.info("A HighScorePostGreSQL's Instance is created.");
		}
		return INSTANCE;
	}
	
	/**
	 * Method add : to add a new player score.
	 * The player is identified by name.
	 */
	@Override
	public void add(String playerName, int score) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Method save : to save new best scores.
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Method load : to load previous best scores.
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	/* ========================================= PostGreSQL Utils ====================================== */ /*=========================================*/
	
	/**
	 * Method connection : to connect the Java application DiceGame to the associated PostGreSQL database.
	 * 
	 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
	 * 
	 * Use PostGreSQL Docker : 
	 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
	 *  * otherwise : docker start postgres
	 *  
	 * If you want to manage your PostGreSQL Docker : docker run -it --rm --link postgres:postgres postgres psql -h postgres -U postgres
	 * Password : riovas
	 * To list databases : \l
	 * Request example : select * from utilisateur;
	 * To quit : \q
	 */
	public void connection () {
		@SuppressWarnings("unused")
		Connection connection = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dicegame", "postgres", "riovas");
	      } catch (Exception error) {
	    	  error.printStackTrace();
	    	  LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	  System.exit(0);
	      }
	      LOG.info("PostGreSQL : Opened database successfully");
	}
		
	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/**
	 * @return the scores
	 */
	public List<Entry> getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	public void setScores(List<Entry> scores) {
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

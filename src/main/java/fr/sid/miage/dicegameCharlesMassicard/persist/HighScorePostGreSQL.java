package fr.sid.miage.dicegameCharlesMassicard.persist;

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
 */
public class HighScorePostGreSQL implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScorePostGreSQL.class.getName());
	
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

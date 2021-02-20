package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
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
public class HighScoreXML implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScoreXML.class.getName());
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static HighScoreXML INSTANCE = null;

	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	/**
	 * The path where scores are saved.
	 */
	private static final String SERIALIZED_FILE_NAME = "./highscores.xml";
	
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
	private HighScoreXML() {
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized HighScoreXML getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScoreXML();
			LOG.info("A HighScoreXML's Instance is created.");
		}
		return INSTANCE;
	}
	
	/**
	 * Method add : to add a new player score.
	 * The player is identified by name.
	 */
	@Override
	public void add(String playerName, int score) {
		this.scores.add(new Entry(playerName, score));
		// Examples : https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
		this.scores.sort(Comparator.comparing(Entry::getScore));
		if (this.scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			this.scores.remove(this.scores.size() - 1);
		}
	}

	/**
	 * Method save : to save new best scores.
	 */
	@Override
	public void save() {
		XMLEncoder encoder = null;
		try {
		encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		} catch(FileNotFoundException fileNotFound) {
			LOG.severe("ERROR: While Creating or Opening the saved highScores");
		}
		encoder.writeObject(this.scores);
		encoder.close();	
	}

	/**
	 * Method load : to load previous best scores.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			LOG.severe("ERROR: File highscores.xml not found");
		}
		this.scores = (List<Entry>) decoder.readObject();
		LOG.info("All entries that are loaded : ");
		for (Entry entry : scores) {
			LOG.info(entry.toString());
		}
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

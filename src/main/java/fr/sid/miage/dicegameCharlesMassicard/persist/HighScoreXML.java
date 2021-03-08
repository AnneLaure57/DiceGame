package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	
	/**
	 * Allow HighScoreXML to be an Observable.
	 */
	PropertyChangeSupport supportHighScore;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * Initiate the list of best scores. 
	 */
	private HighScoreXML() {
		this.setScores(new ArrayList<Entry>());
		this.supportHighScore = new PropertyChangeSupport(this);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method addPropertyChangeListener : allow HighScoreXML to be an Observable.
	 * 
	 * @param pcl PropertyChangeListener to observe HighScoreXML die modifications.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("HighScoreXML : add PropertyChangeListener : " + pcl.getClass().toString());
		supportHighScore.addPropertyChangeListener("Nouveau high score", pcl);
    }
	
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
		// Get
		ArrayList<Entry> scores = (ArrayList<Entry>) this.getScores();
		
		// Add
		scores.add(new Entry(playerName, score));
		
		// Sort
		// Examples : https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
		scores.sort(Comparator.comparing(Entry::getScore).reversed());
		
		// Keep at maximum 100 high scores
		if (scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			scores.remove(scores.size() - 1);
		}

		// Set
		this.setScores(scores);
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
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			LOG.severe("ERROR: File highscores.xml not found");
		}
		List<Entry> entries = (List<Entry>) decoder.readObject();
		this.setScores(entries);
		LOG.info("All entries are loaded.");
		
		// Uncomment if you want to log more informations:
		
//		LOG.info("Display all entries that are loaded : ");
//		for (Entry entry : scores) {
//			LOG.info(entry.toString());
//		}
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
		// Do nothing if this.faceValue = faceValue before
//		this.supportHighScore.firePropertyChange("Nouveau high score", this.scores, scores);
		// Notify change after
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

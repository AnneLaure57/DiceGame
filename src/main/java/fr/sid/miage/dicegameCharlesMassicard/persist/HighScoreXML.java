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
	 * 
	 */
	private static HighScoreXML INSTANCE = null;

	/**
	 * 
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	/**
	 * 
	 */
	private static final String SERIALIZED_FILE_NAME = "./highscores.xml";
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * 
	 */
	private List<Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * 
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
		}
		return INSTANCE;
	}
	
	/**
	 *
	 */
	@Override
	public void add(String nomJoueur, int score) {
		this.scores.add(new Entry(nomJoueur, score));
//		this.scores.sort(Comparator<? extends Entry>);
		// https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
		this.scores.sort(Comparator.comparing(Entry::getScore));
		if (this.scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			this.scores.remove(this.scores.size() - 1);
		}
	}

	/**
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(SERIALIZED_FILE_NAME)));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File highscores.xml not found");
		}
		this.scores = (List<Entry>) decoder.readObject();
		for (Entry entry : scores) {
			System.out.println("nom: " + entry.getName() + ", score: " + entry.getScore());
		}
//		for (Map.Entry<String, Integer> entry : this.scores.entrySet()) {
//		    String key = entry.getKey();
//		    Object value = entry.getValue();
//		    System.out.println("nom: " + key + ", score: " + value);
//		}
	}

	/**
	 *
	 */
	@Override
	public void save() {
		XMLEncoder encoder = null;
		try {
		encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(SERIALIZED_FILE_NAME)));
		} catch(FileNotFoundException fileNotFound) {
			System.out.println("ERROR: While Creating or Opening the saved highScores");
		}
		encoder.writeObject(this.scores);
		encoder.close();	
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

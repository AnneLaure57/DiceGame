package fr.sid.miage.dicegameCharlesMassicard.persist;

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
	private static int NUMBER_OF_SCORES_TO_SAVE = 100;
	
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
	
	@Override
	public void add(String nomJoueur, int score) {
		// TODO Auto-generated method stub
		this.scores.add(new Entry(nomJoueur, score));
//		this.scores.sort(Comparator<? extends Entry>);
		if (this.scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			this.scores.remove(this.scores.size() - 1);
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
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

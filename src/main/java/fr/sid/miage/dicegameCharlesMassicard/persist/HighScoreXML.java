package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.util.ArrayList;
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
	
	private static HighScoreXML INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private List<Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	private HighScoreXML() {
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	public static synchronized HighScoreXML getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScoreXML();
		}
		return INSTANCE;
	}
	
	@Override
	public void add(String nomJoueur, int score) {
		// TODO Auto-generated method stub

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

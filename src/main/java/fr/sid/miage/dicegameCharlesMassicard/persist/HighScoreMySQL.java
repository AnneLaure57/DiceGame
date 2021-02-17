package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.util.HashMap;
import java.util.Map;

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
public class HighScoreMySQL implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	private static HighScoreMySQL INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private Map<String, Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	private HighScoreMySQL() {
		this.scores = new HashMap<String, Entry>();
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	public static synchronized HighScoreMySQL getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScoreMySQL();
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

	/* ========================================= Main ================================================== */ /*=========================================*/
}

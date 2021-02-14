package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.util.HashMap;
import java.util.Map;

import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;
import fr.sid.miage.dicegameCharlesMassicard.core.Entry;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 */
public class HighScoreMongo implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	private static HighScoreMongo INSTANCE = null;
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

//	private Map<String, Entry> scores = new HashMap<String, Entry>();
	private Map<String, Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	private HighScoreMongo() {
		this.scores = new HashMap<String, Entry>();
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	public static synchronized HighScoreMongo getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScoreMongo();
		}
		return INSTANCE;
	}
	
	@Override
	public void add(String nomJoueur, Integer score) {
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
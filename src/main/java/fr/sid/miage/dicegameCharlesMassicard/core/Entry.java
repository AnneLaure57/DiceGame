package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class Entry {

	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(Entry.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The player's name.
	 */
	private String name;

	/**
	 * The player's score.
	 */
	private int score;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * All Args Constructor.
	 * Allow to save player's name and score.
	 * 
	 * @param name The player's name.
	 * @param score The player's score.
	 */
	public Entry(String name, int score) {
		LOG.info("An Entry has just been created with name and score : " + name + " - " + score);
		this.name = name;
		this.score = score;
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
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
	 * No Args Constructor.
	 * Need it for serialization.
	 */
	public Entry() {
		// Comment this line because HighScoreXML create an empty Entry by Entry to save.
		// LOG.info("An empty Entry has just been created.");
		this.name = "";
		this.score = 0;
	}
	
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

	@Override
	public String toString() {
		return "Entry [name=" + name + ", score=" + score + "]";
	}
	
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

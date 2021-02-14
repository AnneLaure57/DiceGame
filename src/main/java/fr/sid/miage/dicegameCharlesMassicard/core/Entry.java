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

	private static final Logger LOG = Logger.getLogger(Entry.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private String name;

	private int score;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

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

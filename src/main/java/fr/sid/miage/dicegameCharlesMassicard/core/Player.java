package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class Player {

	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : Player.
	 */
	private static final Logger LOG = Logger.getLogger(Player.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The player's name.
	 */
	private String name;

	/**
	 * The player's score.
	 * The score is initialized at 0.
	 */
	private int score;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * Player Constructor :
	 *  - The player indicates his/her name.
	 *  - When player is created, the score is initialized at value 0.
	 *  
	 * @param name The player's name.
	 */
	public Player(String name) {
		LOG.info("An Player has just been created (score at 0) with name : " + getName());
		this.setName(name);
		this.setScore(0);
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

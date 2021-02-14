package fr.sid.miage.dicegameCharlesMassicard.core;

import java.util.logging.Logger;

import fr.ul.miage.louis.chantiersimulator.Camion;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public class Player {

	/* ========================================= Global ================================================ */ /*=========================================*/

	private static final Logger LOG = Logger.getLogger(Player.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	private String name;

	private int score;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	public Player(String name) {
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

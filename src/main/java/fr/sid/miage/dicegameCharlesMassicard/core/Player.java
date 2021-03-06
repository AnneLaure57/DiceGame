package fr.sid.miage.dicegameCharlesMassicard.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
	 * So start game with 0 point.
	 * Add 10 points when the sum of dices is equal to 7.
	 */
	private int score;
	
	/**
	 * Observable
	 */
	private PropertyChangeSupport supportPlayer ;

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/**
	 * No Args Player Constructor :
	 *  - When player is created, the score is initialized at value 0 and there is no name for the player.
	 */
	public Player() {
		LOG.info("An Player has just been initialized (score at 0 and no name).");
		this.supportPlayer = new PropertyChangeSupport(this);
		this.setName("");
		this.setScore(0);
	}
	
	/**
	 * Player Constructor :
	 *  - The player indicates his/her name.
	 *  - When player is created, the score is initialized at value 0.
	 *  
	 * @param playerName 
	 */
	public Player(String playerName) {
		LOG.info("An Player has just been created (score at 0) with name : " + playerName);
		this.supportPlayer = new PropertyChangeSupport(this);
		this.setName(playerName);
		this.setScore(0);
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Observable
	 * 
	 */
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("add PropertyChangeListener : " + pcl.getClass().toString());
		supportPlayer.addPropertyChangeListener("Nom joueur", pcl);
    }
 
	/**
	 * Method increaseScore : to add a certain value to player's score.
	 * 
	 * @param valueToAdd Value to add to player's score
	 * @return Return true if the score increases, otherwise return false.
	 */
	public boolean increaseScore(int valueToAdd) {
		try {
			if (valueToAdd > 0) {
				this.score += 7;
			}
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the method 'increaseScore' from Player class :");
			LOG.severe(e.toString());
			return false;
		}
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
		//Do nothing if this.name=name before
		supportPlayer.firePropertyChange("Nom joueur", this.name, name);
		//change after
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
		//Do nothing if this.score=score before
		supportPlayer.firePropertyChange("Score Joueur", this.score, score);
		//change after
		this.score = score;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

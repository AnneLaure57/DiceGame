package fr.sid.miage.dicegameCharlesMassicard;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
import fr.sid.miage.dicegameCharlesMassicard.persist.HighScoreXML;
import fr.sid.miage.dicegameCharlesMassicard.persist.PersistKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.XMLKit;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 *
 * Temp class to test backend.
 */
public class Main {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
	public static void main(String[] args) {
		
		// Déclarations
		PersistKit persistKit;
		HighScoreXML highScore;

		// Initialisations
		persistKit = new XMLKit();
		highScore = (HighScoreXML) persistKit.makeKit();
		
		// Utilisations
		highScore.add("Louis", 100);
		highScore.add("AL", 100);
		highScore.add("Claire", 50);
		highScore.add("Adel", 0);
		
		for (Entry entry : highScore.getScores()) {
			System.out.println("nom: " + entry.getName() + ", score: " + entry.getScore());
		}
		
		highScore.save();
		
		highScore.load();
	}
}

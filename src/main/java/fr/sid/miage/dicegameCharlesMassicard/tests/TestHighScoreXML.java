package fr.sid.miage.dicegameCharlesMassicard.tests;

import fr.sid.miage.dicegameCharlesMassicard.persist.HighScoreXML;
import fr.sid.miage.dicegameCharlesMassicard.persist.PersistKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.XMLKit;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 *
 * Temp class to test backend : HighScoreXML.
 */
public class TestHighScoreXML {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
	/*public static void main(String[] args) {
		
		// Declarations
		PersistKit persistKit;
		HighScoreXML highScore;

		// Initialisations
		persistKit = new XMLKit();
		highScore = (HighScoreXML) persistKit.makeKit();
		
		// Use it : add some entries
		highScore.add("Louis", 100);
		highScore.add("AL", 100);
		highScore.add("Claire", 50);
		highScore.add("Adel", 0);
		
		// Display
		highScore.getScores().forEach(System.out::println);
		
		// Test save
		highScore.save();
		
		// Test load
		highScore.load();
	}*/
}
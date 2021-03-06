package fr.sid.miage.dicegameCharlesMassicard.tests;

import java.util.ArrayList;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
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
 * Class to test backend : HighScoreXML.
 */
public class TestHighScoreXML {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
	
	/**	
	 * Main function : run the test.
	 * 	
	 * @param args Arguments passed to application's Jar (here, not used).
	 */
	public static void main(String[] args) {
		
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
		
		// Display scores
		highScore.getScores().forEach(System.out::println);
		
		// Test save
		highScore.save();
		
		// Drop all scores before load
		highScore.setScores(new ArrayList<Entry>());
		if (highScore.getScores().size() == 0) {
			System.out.println("La liste des scores a été ré-initialisée avant de tester la méthode 'load'.");
		}
		
		// Test load
		highScore.load();
		
		// Display scores after loaded
		highScore.getScores().forEach(System.out::println);
	}
}

package fr.sid.miage.dicegameCharlesMassicard.tests;

import java.util.ArrayList;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
import fr.sid.miage.dicegameCharlesMassicard.persist.HighScorePostGreSQL;
import fr.sid.miage.dicegameCharlesMassicard.persist.PersistKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.PostGreSQLKit;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 *
 * Temp class to test backend : HighScorePostGreSQL.
 */
public class TestHighScorePostGreSQL {
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
		HighScorePostGreSQL highScore;

		// Initialisations
		persistKit = new PostGreSQLKit();
		highScore = (HighScorePostGreSQL) persistKit.makeKit();
		
		// Use it : add some entries
		highScore.add("Louis", 100);
		highScore.add("AL", 100);
		highScore.add("Claire", 50);
		highScore.add("Adel", 0);
		
		// Display
		highScore.getScores().forEach(System.out::println);
		
		// Check connection
//		highScore.checkDatabaseConnection();
		
		// Check if table exists
//		highScore.createTableIfNotExists();
		
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

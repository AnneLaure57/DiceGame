package fr.sid.miage.dicegameCharlesMassicard;

import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;
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
		HighScore highScore;

		// Initialisations
		persistKit = new XMLKit();
		highScore = persistKit.makeKit();
		
		// Utilisations
		highScore.add("Louis", 100);
		highScore.add("AL", 100);
		highScore.add("Claire", 50);
		highScore.add("Adel", 0);
		
		highScore.toString();
		
//		highScore.save();
		
//		highScore.load();
	}
}

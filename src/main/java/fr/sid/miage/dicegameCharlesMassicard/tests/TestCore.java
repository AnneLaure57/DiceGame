package fr.sid.miage.dicegameCharlesMassicard.tests;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 *
 * Class to test backend : HighScoreXML.
 */
public class TestCore {
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
		// On lance le jeu
		DiceGame theGame = DiceGame.getInstance();
		
		// On renseigne le nom du joueur
		theGame.changePlayerName("Bob");
		
		// On commance la partie
		theGame.newGame();
		
		// On regarde l'initialisation des variables liées au jeu
		System.out.println("Player : " + theGame.getPlayer().getName());
		System.out.println("Throw Number : " + theGame.getThrowNumber());
		System.out.println("Player Points : " + theGame.getPlayerPoints());
		
		for (int i = 0; i < 12; i++) {
			// On jette les dés
			theGame.throwDice();
			
			// On regarde de nouveau les mêmes variables liées au jeu
			System.out.println("------------------------------------------");
			System.out.println("Player : " + theGame.getPlayer().getName());
			System.out.println("Throw Number : " + theGame.getThrowNumber());
			System.out.println("Player Points : " + theGame.getPlayerPoints());
		}
	}
}

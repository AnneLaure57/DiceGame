package fr.sid.miage.dicegameCharlesMassicard.tests;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.Context;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDices;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieOneFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieTwoFirst;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 *
 * Class to test backend : check if all strategies (strategy to use to roll dice) are OK.
 */
public class TestsStrategyPattern {
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
		
		// Initialisation du contexte des stratégie à utiliser pour le jeu
		Context context = null;
		
		// Change the n value to choose the strategy to test :
		int n = 1;
		
		switch (n) {
		  case 1:
		    System.out.println("Je lance la strat 1 : lancer le dé 1 en premier, pause de " + Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW + " sec entre les deux lancés");
		    context = new Context(new RollDieOneFirst());
			for (int i = 0; i < 11; i++) {
				System.out.println("--------------------------------------- \n Tour numéro : " + (i+1));
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		  case 2:
			System.out.println("Je lance la strat 2 : lancer le dé 2 en premier, pause de " + Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW + " sec entre les deux lancés");
			context = new Context(new RollDieOneFirst());
			context = new Context(new RollDieTwoFirst());
			for (int i = 0; i < 11; i++) {
				System.out.println("--------------------------------------- \n Tour numéro : " + (i+1));
				System.out.println("Tour numéro : " + (i+1));
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		  case 3:
		    System.out.println("Je lance la strat 3 : lancer les dés en même temps");
			context = new Context(new RollDices());
			for (int i = 0; i < 11; i++) {
				System.out.println("--------------------------------------- \n Tour numéro : " + (i+1));
				System.out.println("Tour numéro : " + (i+1));
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		}
		
	}

}

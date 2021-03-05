package fr.sid.miage.dicegameCharlesMassicard.tests;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.Context;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDices;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieOneFirst;
import fr.sid.miage.dicegameCharlesMassicard.utils.strategy.RollDieTwoFirst;

public class TestsStrategyPattern {

	public static void main(String[] args) {
		
		// On lance le jeu
		DiceGame theGame = DiceGame.getInstance();
		
		// On renseigne le nom du joueur
		theGame.changePlayerName("Bob");
		
		// On commance la partie
		theGame.newGame();
		
		Context context = null;
		
		int n = 3;
		
		switch (n) {
		  case 1:
		    System.out.println("Je lance la strat 1 : lancer le dé 1 en premier, pause de 3 sec entre les deux lancés");
		    context = new Context(new RollDieOneFirst());
			for (int i = 0; i < 11; i++) {
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		  case 2:
			System.out.println("Je lance la strat 2 : lancer le dé 2 en premier, pause de 3 sec entre les deux lancés");
			context = new Context(new RollDieOneFirst());
			context = new Context(new RollDieTwoFirst());
			for (int i = 0; i < 11; i++) {
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		  case 3:
		    System.out.println("Je lance la strat 3 : lancer les dés en même temps");
			context = new Context(new RollDices());
			for (int i = 0; i < 11; i++) {
				context.executeStrategy(theGame.getDie1(), theGame.getDie2());
			}
		    break;
		}
		
	}

}

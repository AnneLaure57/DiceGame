package fr.sid.miage.dicegameCharlesMassicard.utils;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 * Randomizer
 */
public class Randomizer {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Random generator.
	 * Example : getRandom.nextInt(1000); // random integer between 0 and 1000 
	 */
	private final Random getRandom = new Random(Instant.now().getEpochSecond());
	
	/**
	 * Logger for this class : Randomizer.
	 */
	private static final Logger LOG = Logger.getLogger(Randomizer.class.getName());
	
	/**
	 * The instance of the singleton Randomizer. 
	 */
	private static Randomizer INSTANCE = null;

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/**
	 * No Args Constructor.
	 * When Randomizer is created.
	 */
	private Randomizer() {
		LOG.info("An Randomizer has just been created.");
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/**
	 * Method getInstance : to get the Randomizer's instance.
	 * 
	 * @return The Randomizer's instance.
	 */
	public static synchronized Randomizer getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Randomizer();
		}
		return INSTANCE;
	}
	
	/**
	 * Method getValue : to get a random integer between 0 and a max value.
	 * 
	 * @param max Maximum value that the random integer must not exceed.
	 * @return The random integer between 0 and a max value.
	 */
	public int getValue(int max) {
		return getInstance().getRandom.nextInt(max);
	}
	
	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
}

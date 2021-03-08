package fr.sid.miage.dicegameCharlesMassicard.persist;

import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Concrete factory
 */
public class PostGreSQLKit implements PersistKit {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * The persit kit name to call, refer and use this persist kit in a switch case.
	 * Specialy usefull for switch case because you can't use PostGreSQLKit.class.getName().
	 */
	public static final String PERSIST_KIT_NAME = "PostGreSQLKit";
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	@Override
	public HighScore makeKit() {
		return HighScorePostGreSQL.getInstance();
	}

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
}

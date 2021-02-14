package fr.sid.miage.dicegameCharlesMassicard.persist;

import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Abstract factory
 */
public interface PersistKit {
	public abstract HighScore makeKit();
}

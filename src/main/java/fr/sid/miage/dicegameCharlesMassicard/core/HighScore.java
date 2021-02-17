package fr.sid.miage.dicegameCharlesMassicard.core;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Abstract product.
 */
public interface HighScore {
	public abstract void add(String nomJoueur, int score);
	public abstract void load();
	public abstract void save();
}

package fr.sid.miage.dicegameCharlesMassicard.core;

/**
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 *
 */
public interface HighScore {
	public abstract void add(String nomJoueur, Integer score);
	public abstract void load();
	public abstract void save();
}

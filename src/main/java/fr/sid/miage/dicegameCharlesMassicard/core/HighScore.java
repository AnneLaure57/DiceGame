package fr.sid.miage.dicegameCharlesMassicard.core;

import java.beans.PropertyChangeListener;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Abstract product.
 */
public interface HighScore {
	public abstract void add(String playerName, int score);
	public abstract void save();
	public abstract void load();
	public abstract void addPropertyChangeListener(PropertyChangeListener pcl);
}

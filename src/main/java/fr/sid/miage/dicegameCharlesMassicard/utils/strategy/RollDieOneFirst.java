package fr.sid.miage.dicegameCharlesMassicard.utils.strategy;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Die;
import javafx.application.Platform;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * The strategy to use to roll dice : Die one rolls first, then Die two rolls some seconds after dice one.
 */
public class RollDieOneFirst implements RollStrategy {
	/* ========================================= Global ================================================ */ /*=========================================*/

	/**
	 * Logger for this class : RollDieOneFirst.
	 */
	private static final Logger LOG = Logger.getLogger(RollDieOneFirst.class.getName());

	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method rollDices : roll dice using a specific strategy.
	 * Die one rolls first, then Die two rolls some seconds after dice one.
	 * 
	 * @param die1 The first die used for the Dice Game.
	 * @param die2 The second die used for the Dice Game.
	 * 
	 * @return Return true if you succed to roll dice, otherwise return false.
	 */
	@Override
	public boolean rollDices(Die die1, Die die2) {
		try {
			// Dice one rolls : set Die One first value
			die1.roll();
			LOG.info("value of Die 1 : " + die1.getFaceValue());
			
//			long period = Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW;
//			long millisecondes = TimeUnit.SECONDS.toMillis(period);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        die2.roll();
                        LOG.info("value of Die 2 : " + die2.getFaceValue());
                        this.cancel();
                    });
                }
            }, Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW * 1000);
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
////                    boolean timerStart = true;
//                    
//                    //allow to make a pause and avoid the IllegalStateException Thread Timer-0
//                    Platform.runLater(() -> {
//                        die2.roll();
//                        LOG.info("value of Die 2 : " + die2.getFaceValue());
//                        this.cancel();
//                    });
//                }
//            }, period, millisecondes);
			
			// Pause during some seconds
//			TimeUnit.SECONDS.sleep(Context.NB_SEC_BEFORE_ANOTHER_DIE_THROW);
			
			// Dice two rolls : set Die Two value
//			die2.roll();
//			LOG.info("value of Die 2 : " + die2.getFaceValue());
			
			return true;
		} catch (Exception e) {
			LOG.severe("An error occurred during the pattern Strategy in class \"RollDieOneFirst\" :");
			LOG.severe(e.toString());
			return false;
		}
	}

	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/* ========================================= Main ================================================== */ /*=========================================*/
}

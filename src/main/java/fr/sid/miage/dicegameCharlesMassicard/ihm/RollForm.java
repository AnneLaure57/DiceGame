package fr.sid.miage.dicegameCharlesMassicard.ihm;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
import fr.sid.miage.dicegameCharlesMassicard.persist.MongoDBKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.PostGreSQLKit;
import fr.sid.miage.dicegameCharlesMassicard.persist.XMLKit;
import fr.sid.miage.dicegameCharlesMassicard.utils.memento.DiceGameState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.DiceGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Caretaker in Memento pattern
 */
public class RollForm implements PropertyChangeListener, Initializable {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : RollForm.
	 */
	private static final Logger LOG = Logger.getLogger(RollForm.class.getName());
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/
	
	/**
	 * The player's name. 
	 */
	public String nickNameFound;
	
	/**
	 * The data to display in our TableView FXML component.
	 */
	private ObservableList<Entry> data = FXCollections.observableArrayList();
	
	/**
	 * The saved dice game to implement Memento Pattern.
	 */
	private DiceGameState savedDiceGame;

	/* ========================================= Vues ================================================== */ /*=========================================*/

	@FXML
	private Parent root ;
	
	@FXML private AnchorPane content;

	@FXML private SplitPane boardDiceGameAnchor;

	/* ========================================= Composants ================================================ */ /*=========================================*/

	/* ========================================= Labels */
	
	@FXML private Label turnNumber;
	
	@FXML private Label scoreDie1;
	
	@FXML private Label scoreDie2;
	
	@FXML private Label scorePlayer;
	
	/* ========================================= Buttons */
	
	@FXML private Button throwButton;
	
	@FXML private Button undoButton;
	
	/* ========================================= TableViews & TableColumn */

	@FXML TableView<Entry> tableViewScore = new TableView<Entry>(data);;
	
	@FXML TableColumn<Entry, String> tabNamePlayer = new TableColumn<Entry, String>();
	
    @FXML TableColumn<Entry, Integer> tabScorePlayer = new TableColumn<Entry, Integer>();
    
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/

	/* ========================================= Initialize ============================================ */

	/**
	 * Method initialize : to init FXML and observe observable backend components.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LOG.info("RollForm Initialization.");
		
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		diceGame.addPropertyChangeListener(this);
		diceGame.getPlayer().addPropertyChangeListener(this);
		diceGame.getDie1().addPropertyChangeListener(this);
		diceGame.getDie2().addPropertyChangeListener(this);
		diceGame.getHighScore().addPropertyChangeListener(this);

		actualiseInformationsGame(0);
		actualiseScore(0);
		
		this.tabNamePlayer.setCellValueFactory(new PropertyValueFactory<Entry, String>("Name"));
		this.tabScorePlayer.setCellValueFactory(new PropertyValueFactory<Entry, Integer>("Score"));
		
		// Add Property Change Listenerfor each DB persist type
		diceGame.changePersistKit(MongoDBKit.PERSIST_KIT_NAME);
		diceGame.getHighScore().addPropertyChangeListener(this);
		diceGame.getHighScore().load();
		diceGame.changePersistKit(PostGreSQLKit.PERSIST_KIT_NAME);
		diceGame.getHighScore().addPropertyChangeListener(this);
		diceGame.getHighScore().load();
		diceGame.changePersistKit(XMLKit.PERSIST_KIT_NAME);
		diceGame.getHighScore().addPropertyChangeListener(this);
		diceGame.getHighScore().load();
		
		this.hitSave();
	}
	
	/* ========================================= Property Change ============================================== */
	
	/**
	 * 
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();

		switch(evt.getPropertyName()) {
		case "Tour partie":
			int throwNumber = (int) evt.getNewValue();
			this.turnNumber.setText(String.valueOf(throwNumber));
			if (throwNumber == 0) {				
				throwButton.setDisable(false);
				undoButton.setDisable(true);
			}
			this.scorePlayer.setText(String.valueOf(diceGame.getDie1().getFaceValue() + diceGame.getDie2().getFaceValue()));
			break;
			
		case "Valeur dé 1":
			int die1Value = (int) evt.getNewValue();
			this.scoreDie1.setText(String.valueOf(die1Value));
			LOG.info("New face value for Die 1 : " + die1Value);
			break;
			
		case "Valeur dé 2":
			int die2Value = (int) evt.getNewValue();
			this.scoreDie2.setText(String.valueOf(die2Value));
			LOG.info("New face value for Die 2 : " + die2Value);
			break;
			
		case "Nouveau high score":
			this.tabNamePlayer.setCellValueFactory(new PropertyValueFactory<Entry, String>("Name"));
			this.tabScorePlayer.setCellValueFactory(new PropertyValueFactory<Entry, Integer>("Score"));
			
			@SuppressWarnings("unchecked") List<Entry> entries = (List<Entry>) evt.getNewValue();
			
			// Reset
			this.data.clear();
			
			// Add to data to display
			for (Entry entry : entries) {
				this.data.add(entry);
			}
			
			// Set in FXML component & refresh view
			this.tableViewScore.setItems(data);
			this.tableViewScore.refresh();
			
			LOG.info("Nouveau high score : " + entries);
			break;
			
		case "Change Persist Kit":
			String newPersistKit = (String) evt.getNewValue().toString();
			
			LOG.info("New Persist Kit : " + newPersistKit);
			break;
			
		default :

			break;
		}
    }
	
	/* ========================================= Init FXML components ============================================== */
	
	/**
	 * Method actualiseInformationsGame : to init all labels associated to this view.
	 * 
	 * @param score The game score to display in all labels associated to this view.
	 */
	private void actualiseInformationsGame(int score) {
		this.turnNumber.setText(Integer.toString(score));
		this.scoreDie1.setText(Integer.toString(score));
		this.scoreDie2.setText(Integer.toString(score));
		
		this.undoButton.setDisable(true);
	}
	
	/**
	 * Method actualiseScore : to init all labels associated to this view.
	 * 
	 * @param score The game score to display in all labels associated to this view.
	 */
	private void actualiseScore(int score) {
		this.scorePlayer.setText(Integer.toString(score));
	}
	
	/* ========================================= Roll ============================================== */
	
	/**
	 * Method roll : the method that is call when user push the button 'Lancer'.
	 */
	@FXML
    private void roll() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
			  this.hitSave();
			  LOG.info("Throw dice. Throw number : " + diceGame.getThrowNumber());
			  diceGame.throwDice();
			  undoButton.setDisable(false);
			  if (diceGame.getThrowNumber() == DiceGame.POINTS_TO_ADD_WHEN_WIN) {
				  throwButton.setDisable(true);
				  undoButton.setDisable(true);
			}
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Lancer' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Method cancel : the method that is call when user push the button 'Annuler'.
	 */
	@FXML
    private void cancel() {
		// Get the Dice Game instance
		DiceGame diceGame = DiceGame.getInstance();
		
		try {
		  if (diceGame.getPlayer().getName() != null) {
			  this.hitUndo();
			  undoButton.setDisable(true);
			  LOG.info("Cancel lest dice throw.");
		  }
    	} catch (Exception e) {
    		LOG.severe("Erreur lorque l'utilisateur a appuyé sur le bouton 'Annuler' : "+ e.getMessage());
    		e.printStackTrace();
    	}
    }
	
	/**
	 * Method hitSave : to implement the Memento pattern.
	 */
	public void hitSave() {
	    this.savedDiceGame = DiceGame.getInstance().save();
	}

	/**
	 * Method hitSave : to implement the Memento pattern.
	 */
	public void hitUndo() {
		DiceGame.getInstance().restore(this.savedDiceGame);
	}

	
	/* ========================================= Cancel Roll ============================================== */

}

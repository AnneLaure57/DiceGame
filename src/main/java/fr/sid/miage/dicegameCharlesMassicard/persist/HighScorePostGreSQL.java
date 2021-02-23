package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import fr.sid.miage.dicegameCharlesMassicard.core.Entry;
import fr.sid.miage.dicegameCharlesMassicard.core.HighScore;

/**
 * @author Anne-Laure CHARLES
 * @author Louis MASSICARD (user name : louis)
 * @version 
 * @since %G% - %U% (%I%)
 * 
 * Singleton
 * Concrete Product
 * https://www.tutorialspoint.com/postgresql/postgresql_java.htm
 * 
 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
 * 
 * Use PostGreSQL Docker : 
 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
 *  * otherwise : docker start postgres
 *  
 * If you want to manage your PostGreSQL Docker : docker run -it --rm --link postgres:postgres postgres psql -h postgres -U postgres
 * Password : riovas
 * To list databases : \l
 * Request example : select * from utilisateur;
 * To quit : \q
 * 
 * Other examples : https://stackoverflow.com/questions/769683/postgresql-show-tables-in-postgresql
 */
public class HighScorePostGreSQL implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScorePostGreSQL.class.getName());
	
	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static HighScorePostGreSQL INSTANCE = null;
	
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static final String SERVER_URL = "jdbc:postgresql://localhost:5432/";
	private static final String DATABASE_NAME = "dicegame";
	private static final String DATABASE_URL = SERVER_URL + DATABASE_NAME;

	//  Database credentials
	private static final String DATABASE_USER = "postgres";
	private static final String DATABASE_PASS = "riovas";
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The list of best scores to saved.
	 */
	private List<Entry> scores;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * Initiate the list of best scores. 
	 */
	private HighScorePostGreSQL() {
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized HighScorePostGreSQL getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScorePostGreSQL();
			LOG.info("A HighScorePostGreSQL's Instance is created.");
		}
		return INSTANCE;
	}
	
	/**
	 * Method add : to add a new player score.
	 * The player is identified by name.
	 */
	@Override
	public void add(String playerName, int score) {
		this.scores.add(new Entry(playerName, score));
		// Examples : https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
		this.scores.sort(Comparator.comparing(Entry::getScore).reversed());
		if (this.scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			this.scores.remove(this.scores.size() - 1);
		}
	}
	
	/**
	 * Method save : to save new best scores.
	 */
	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Method load : to load previous best scores.
	 */
	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	/* ========================================= PostGreSQL Utils ====================================== */ /*=========================================*/
	
	/**
	 * Method connection : to check the connection between the Java application DiceGame to the associated PostGreSQL database.
	 * 
	 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
	 * 
	 * Use PostGreSQL Docker : 
	 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
	 *  * otherwise : docker start postgres
	 */
	public void checkDatabaseConnection () {
		// https://www.jvmhost.com/articles/create-drop-databases-dynamically-java-jsp-code/
		Connection connection = null;
		Statement statement = null;
	    ResultSet resultSet = null;
	    
	    boolean dbNeedToBeCreated = true;
	    
	    try {
	    	Class.forName(JDBC_DRIVER);
	    	connection = DriverManager.getConnection(SERVER_URL, DATABASE_USER, DATABASE_PASS);
	    	statement = connection.createStatement();
	    	
	        // https://www.postgresqltutorial.com/postgresql-show-databases/
	    	resultSet = statement.executeQuery("SELECT datname FROM pg_database;");
	    	
	    		
	    	LOG.info(DATABASE_NAME);
	    	LOG.info("List of databases accessible by user " + DATABASE_USER + ":");
	    	
	    	while (resultSet.next()) {
	        	// Uncomment to debug 
	    		// System.out.println(resultSet.getString(1));
	    		// System.out.println(resultSet.getString(1).equals(DATABASE_NAME));
	        	if (resultSet.getString(1).equals(DATABASE_NAME)) {
	        		dbNeedToBeCreated = false;
				}
	        }
	    	
	    	if (dbNeedToBeCreated) {
	    		LOG.info("Initiate and create database for this app : " + DATABASE_NAME);
	    		statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME + ";");
	    		
	    		// https://stackoverflow.com/questions/7945235/how-can-i-create-a-postgresql-database-in-java
	    		// statement.executeUpdate("DROP DATABASE IF EXISTS dicegame;");
			} else {
				LOG.info("The database already exists for this app : " + DATABASE_NAME);
			}
	    	
	    	resultSet.close();
	    	statement.close();
	    	connection.close();
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	    LOG.info("PostGreSQL : Opened database successfully");
	}


	
//	https://www.tutorialspoint.com/postgresql/postgresql_java.htm
		
	/* ========================================= Accesseurs ============================================ */ /*=========================================*/

	/**
	 * @return the scores
	 */
	public List<Entry> getScores() {
		return scores;
	}

	/**
	 * @param scores the scores to set
	 */
	public void setScores(List<Entry> scores) {
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

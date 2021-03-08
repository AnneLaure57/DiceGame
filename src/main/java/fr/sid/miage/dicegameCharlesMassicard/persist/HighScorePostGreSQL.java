package fr.sid.miage.dicegameCharlesMassicard.persist;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
 * PostGresQL Java doc : https://www.tutorialspoint.com/postgresql/postgresql_java.htm
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
	 * The unique instance of this Singleton class.
	 */
	private static HighScorePostGreSQL INSTANCE = null;
	
	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	// JDBC driver name and database URL
	private static final String JDBC_DRIVER = "org.postgresql.Driver";  
	private static final String SERVER_URL = "jdbc:postgresql://localhost:5432/";
	private static final String DATABASE_NAME = "dicegame";
	private static final String DATABASE_URL = SERVER_URL + DATABASE_NAME;

	//  Database credentials
	private static final String DATABASE_USER = "postgres";
	private static final String DATABASE_PASS = "riovas";

	// Table's informations
	private static final String TABLE_NAME = "entries";
	private static final String TABLE_FIELD_ID = "id";
	private static final String TABLE_FIELD_NAME = "name";
	private static final String TABLE_FIELD_SCORE = "score";
	
	/* ========================================= Attributs ============================================= */ /*=========================================*/

	/**
	 * The list of best scores to saved.
	 */
	private List<Entry> scores;
	
	/**
	 * Allow HighScoreXML to be an Observable.
	 */
	PropertyChangeSupport supportHighScore;
	
	/* ========================================= Constructeurs ========================================= */ /*=========================================*/

	/**
	 * No Args Constructor.
	 * Initiate the list of best scores. 
	 */
	private HighScorePostGreSQL() {
		this.supportHighScore = new PropertyChangeSupport(this);
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method addPropertyChangeListener : allow HighScorePostGreSQL to be an Observable.
	 * 
	 * @param pcl PropertyChangeListener to observe HighScorePostGreSQL die modifications.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		System.out.println("HighScorePostGreSQL : add PropertyChangeListener : " + pcl.getClass().toString());
		supportHighScore.addPropertyChangeListener("Nouveau high score", pcl);
    }
	
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
		// Get
		ArrayList<Entry> scores = (ArrayList<Entry>) this.getScores();
		
		// Add
		scores.add(new Entry(playerName, score));
		
		// Sort
		// Examples : https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
		scores.sort(Comparator.comparing(Entry::getScore).reversed());
		
		// Keep at maximum 100 high scores
		if (scores.size() > NUMBER_OF_SCORES_TO_SAVE) {
			scores.remove(scores.size() - 1);
		}

		// Set
		this.setScores(scores);
	}
	
	/**
	 * Method save : to save new best scores.
	 */
	@Override
	public void save() {
		this.checkDatabaseConnection();
		this.createTableIfNotExists();
		this.truncateTable();
		this.insertMany(getScores());
	}
	
	/**
	 * Method load : to load previous best scores.
	 */
	@Override
	public void load() {
		this.checkDatabaseConnection();
		this.createTableIfNotExists();
		this.setScores(this.getMany());
	}

	/* ========================================= PostGreSQL Utils ====================================== */ /*=========================================*/
	
	/**
	 * Method checkDatabaseConnection : to check the connection between the Java application DiceGame to the associated PostGreSQL database.
	 * 
	 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
	 * 
	 * Use PostGreSQL Docker : 
	 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
	 *  * otherwise : docker start postgres
	 */
	private void checkDatabaseConnection () {
		LOG.info("Check connection to database : " + DATABASE_URL);
		
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
	    	
	    	LOG.info("PostGreSQL : Opened database successfully");
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	}

	/**
	 * Method createTableIfNotExists : to run the command CREATE TABLE IF NOT EXISTS.
	 * 
	 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
	 * 
	 * Use PostGreSQL Docker : 
	 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
	 *  * otherwise : docker start postgres
	 */
	private void createTableIfNotExists () {
		LOG.info("Create table (if not exists), table name : " + TABLE_NAME);
		
		Connection connection = null;
		Statement statement = null;
		int commandReturn;
	    	    
	    try {
	    	Class.forName(JDBC_DRIVER);
	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
	    	statement = connection.createStatement();
	    	
	    	String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
	                " ("+ TABLE_FIELD_ID + " INT PRIMARY KEY NOT NULL," +
	                " " + TABLE_FIELD_NAME + " TEXT NOT NULL, " +
	                " " + TABLE_FIELD_SCORE + " INT NOT NULL);";
	    	commandReturn = statement.executeUpdate(sql);
	    		    	
	    	if (commandReturn == 0) {
				LOG.info("SQL statement return nothing.");
			} else {
				LOG.info("The row count for SQL Data Manipulation Language (DML) statements.");
				LOG.info("Row count : " + commandReturn);
			}
	    				    	
	    	statement.close();
	    	connection.close();
	    	
	    	LOG.info("PostGreSQL : the table " + TABLE_NAME + " is ready.");
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	}
	
	/**
	 * Method truncateTable : to run the command TRUNCATE.
	 * 
	 * If you have a running PostGreSQL server, then run this command : sudo pkill -u postgres
	 * 
	 * Use PostGreSQL Docker : 
	 *  * first use : docker run --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres
	 *  * otherwise : docker start postgres
	 */
	private void truncateTable () {
		LOG.info("Truncate table, table name : " + TABLE_NAME);
		
		Connection connection = null;
		Statement statement = null;
		int commandReturn;
	    	    
	    try {
	    	Class.forName(JDBC_DRIVER);
	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
	    	statement = connection.createStatement();
	    	
	    	String sql = "TRUNCATE " + TABLE_NAME + ";";
	    	commandReturn = statement.executeUpdate(sql);
	    		    	
	    	if (commandReturn == 0) {
				LOG.info("SQL statement return nothing.");
			} else {
				LOG.info("The row count for SQL Data Manipulation Language (DML) statements.");
				LOG.info("Row count : " + commandReturn);
			}
	    				    	
	    	statement.close();
	    	connection.close();
	    	
	    	LOG.info("PostGreSQL : the table " + TABLE_NAME + " is truncated, so now the table is empty.");
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	}
	
	/**
	 * Method insertMany : to insert a list of Entry.
	 * 
	 * @param scores The list of Entry to insert in database/table.
	 */
	private void insertMany (List<Entry> scores) {
		LOG.info("Insert Many into table : " + TABLE_NAME);
		
		Connection connection = null;
		Statement statement = null;
			    	    
	    try {
	    	Class.forName(JDBC_DRIVER);
	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
	    	connection.setAutoCommit(false);
	    	
	    	statement = connection.createStatement();
	    	
	    	int index = 0;
	    	for (Entry entry : scores) {
	    		index++;
	    		String sql = "INSERT INTO " + TABLE_NAME + " (" + TABLE_FIELD_ID + "," + TABLE_FIELD_NAME + "," + TABLE_FIELD_SCORE + ") "
	    				+ "VALUES (" + index + ", " + "'"+entry.getName()+"'" + ", " + entry.getScore() +");";
	    		statement.executeUpdate(sql);
			}
	    		    	
	    	statement.close();
	    	connection.commit();
	    	connection.close();
	    	
	    	LOG.info("PostGreSQL : the list of Entry is inserted.");
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	}
	
	/**
	 * Method getMany : to get all Entry in database.
	 * 
	 * @return The list of Entry to inserted in database/table.
	 */
	private List<Entry> getMany () {
		LOG.info("Get Many from table : " + TABLE_NAME);
		
		List<Entry> scores = new ArrayList<Entry>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
			    	    
	    try {
	    	Class.forName(JDBC_DRIVER);
	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
	    	connection.setAutoCommit(false);
	    	
	    	statement = connection.createStatement();
	    	resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
	    	
	    	while (resultSet.next()) {
	            String  name = resultSet.getString("name");
	            int score  = resultSet.getInt("score");
	            
	            scores.add(new Entry(name, score));
	            
	         // Uncomment if you want to log more informations:
//	    		int ID = resultSet.getInt("id");
	            
//	            System.out.println( TABLE_FIELD_ID + " = " + ID );
//	            System.out.println( TABLE_FIELD_NAME + " = " + name );
//	            System.out.println( TABLE_FIELD_SCORE + " = " + score );
//	            System.out.println();
	        }
	    		    	
	        resultSet.close();
	        statement.close();
	        connection.close();
	        
	        LOG.info("PostGreSQL : the list of Entry is loaded.");
	    	
	    } catch (Exception error) {
	    	error.printStackTrace();
	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
	    	System.exit(0);
	    }
	    
	    return scores;
	}
		
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
		// Do nothing if this.faceValue = faceValue before
		supportHighScore.firePropertyChange("Nouveau high score", this.scores, scores);
		// Notify change after
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

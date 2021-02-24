package fr.sid.miage.dicegameCharlesMassicard.persist;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

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
 * Mongodb Java doc : https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
 */
public class HighScoreMongoDB implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScoreMongoDB.class.getName());
	
	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static HighScoreMongoDB INSTANCE = null;
	
	// Name and database URL
	private static final String SERVER_URL = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "dicegame";
	private static final String DATABASE_URL = SERVER_URL + DATABASE_NAME;

	//  Database credentials
//	private static final String DATABASE_USER = "postgres";
//	private static final String DATABASE_PASS = "riovas";

	// Collection's informations
	private static final String COLLECTION_NAME = "ENTRIES";
	private static final String TABLE_FIELD_ID = "ID";
	private static final String TABLE_FIELD_NAME = "NAME";
	private static final String TABLE_FIELD_SCORE = "SCORE";
	
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
	private HighScoreMongoDB() {
		this.setScores(new ArrayList<Entry>());
	}
	
	/* ========================================= Methodes ============================================== */ /*=========================================*/
	
	/**
	 * Method getInstance : return the instance of the current Concrete Product or create it.
	 * 
	 * @return the current Concrete Product or create it.
	 */
	public static synchronized HighScoreMongoDB getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HighScoreMongoDB();
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
//		this.checkDatabaseConnection();
//		this.createTableIfNotExists();
//		this.truncateTable();
//		this.insertMany(getScores());
	}
	
	/**
	 * Method load : to load previous best scores.
	 */
	@Override
	public void load() {
	//		this.checkDatabaseConnection();
	//		this.createTableIfNotExists();
	//		this.getMany();
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
	public void checkDatabaseConnection () {
		LOG.info("Check connection to database : " + DATABASE_URL);
		
	    try {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI(SERVER_URL));
	    	
	    	// Deprecated
//	    	DB database = mongoClient.getDB(DATABASE_NAME);
//	    	List<String> databases = mongoClient.getDatabaseNames();
//	    	for (String string : databases) {
//				System.out.println("DB name : " + string);
//			}
//	    	DBCollection collection = database.getCollection(COLLECTION_NAME);
	    	
	    	// Non deprecated
	    	MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
	    	MongoIterable<String> collectionNames = database.listCollectionNames();
	    	MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
	    	
	    	// Uncomment if you want more details
//	    	 MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
//	    	for (String string : databaseNames) {
//	    		System.out.println("DB name : " + string);
//	    	}
	    	
	    	mongoClient.close();
	    	
	    	LOG.info("MongoDB : Opened database successfully");
	    	
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
	public void createTableIfNotExists () {
//		LOG.info("Create table (if not exists), table name : " + TABLE_NAME);
//		
//		Connection connection = null;
//		Statement statement = null;
//		int commandReturn;
//	    	    
//	    try {
//	    	Class.forName(JDBC_DRIVER);
//	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
//	    	statement = connection.createStatement();
//	    	
//	    	String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
//	                " ("+ TABLE_FIELD_ID + " INT PRIMARY KEY NOT NULL," +
//	                " " + TABLE_FIELD_NAME + " TEXT NOT NULL, " +
//	                " " + TABLE_FIELD_SCORE + " INT NOT NULL);";
//	    	commandReturn = statement.executeUpdate(sql);
//	    		    	
//	    	if (commandReturn == 0) {
//				LOG.info("SQL statement return nothing.");
//			} else {
//				LOG.info("The row count for SQL Data Manipulation Language (DML) statements.");
//				LOG.info("Row count : " + commandReturn);
//			}
//	    				    	
//	    	statement.close();
//	    	connection.close();
//	    	
//	    	LOG.info("PostGreSQL : the table " + TABLE_NAME + " is ready.");
//	    	
//	    } catch (Exception error) {
//	    	error.printStackTrace();
//	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
//	    	System.exit(0);
//	    }
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
	public void truncateTable () {
//		LOG.info("Truncate table, table name : " + TABLE_NAME);
//		
//		Connection connection = null;
//		Statement statement = null;
//		int commandReturn;
//	    	    
//	    try {
//	    	Class.forName(JDBC_DRIVER);
//	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
//	    	statement = connection.createStatement();
//	    	
//	    	String sql = "TRUNCATE " + TABLE_NAME + ";";
//	    	commandReturn = statement.executeUpdate(sql);
//	    		    	
//	    	if (commandReturn == 0) {
//				LOG.info("SQL statement return nothing.");
//			} else {
//				LOG.info("The row count for SQL Data Manipulation Language (DML) statements.");
//				LOG.info("Row count : " + commandReturn);
//			}
//	    				    	
//	    	statement.close();
//	    	connection.close();
//	    	
//	    	LOG.info("PostGreSQL : the table " + TABLE_NAME + " is truncated, so now the table is empty.");
//	    	
//	    } catch (Exception error) {
//	    	error.printStackTrace();
//	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
//	    	System.exit(0);
//	    }
	}
	
	/**
	 * Method insertMany : to insert a list of Entry.
	 * 
	 * @param scores The list of Entry to insert in database/table.
	 */
	public void insertMany (List<Entry> scores) {
		LOG.info("Insert Many into collection : " + COLLECTION_NAME);
		
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
	public List<Entry> getMany () {
		LOG.info("Get Many from table : " + COLLECTION_NAME);
		
		List<Entry> scores = new ArrayList<Entry>();
//		
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet resultSet = null;
//			    	    
//	    try {
//	    	Class.forName(JDBC_DRIVER);
//	    	connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
//	    	connection.setAutoCommit(false);
//	    	
//	    	statement = connection.createStatement();
//	    	resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
//	    	
//	    	while (resultSet.next()) {
//	    		int ID = resultSet.getInt("id");
//	            String  name = resultSet.getString("name");
//	            int score  = resultSet.getInt("score");
//	            System.out.println( "ID = " + ID );
//	            System.out.println( "NAME = " + name );
//	            System.out.println( "SCORE = " + score );
//	            System.out.println();
//	        }
//	    		    	
//	        resultSet.close();
//	        statement.close();
//	        connection.close();
//	    	
//	    } catch (Exception error) {
//	    	error.printStackTrace();
//	    	LOG.severe(error.getClass().getName() + ": " + error.getMessage());
//	    	System.exit(0);
//	    }
//	    
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
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

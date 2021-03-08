package fr.sid.miage.dicegameCharlesMassicard.persist;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
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
 * Mongodb Java doc : 
 *   * https://www.mongodb.com/blog/post/getting-started-with-mongodb-and-java-part-i
 *   * https://mongodb.github.io/mongo-java-driver/3.4/driver/getting-started/quick-start/
 */
public class HighScoreMongoDB implements HighScore {
	/* ========================================= Global ================================================ */ /*=========================================*/
	
	/**
	 * Logger for this class : Entry.
	 */
	private static final Logger LOG = Logger.getLogger(HighScoreMongoDB.class.getName());
	
	/**
	 * The unique instance of this Singleton class.
	 */
	private static HighScoreMongoDB INSTANCE = null;
	
	/**
	 * Maximum number of scores to save.
	 * If there is too much to score, then smaller scores aren't saved.
	 */
	private static final int NUMBER_OF_SCORES_TO_SAVE = 100;
		
	// Name and database URL
	private static final String SERVER_URL = "mongodb://localhost:27017";
	private static final String DATABASE_NAME = "dicegame";
	private static final String DATABASE_URL = SERVER_URL + DATABASE_NAME;

	//  Database credentials
//	private static final String DATABASE_USER = "postgres";
//	private static final String DATABASE_PASS = "riovas";

	// Collection's informations
	private static final String COLLECTION_NAME = "entries";
	private static final String COLLECTION_FIELD_ID = "_id";
	private static final String COLLECTION_FIELD_NAME = "name";
	private static final String COLLECTION_FIELD_SCORE = "score";
	
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
			LOG.info("A HighScoreMongoDB's Instance is created.");
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
		this.checkDatabaseConnection();
		this.removeAllDocuments();
		this.insertMany(getScores());
	}
	
	/**
	 * Method load : to load previous best scores.
	 */
	@Override
	public void load() {
		this.checkDatabaseConnection();
		this.scores = this.getMany();
	}

	/* ========================================= PostGreSQL Utils ====================================== */ /*=========================================*/
	
	/**
	 * Method checkDatabaseConnection : to check the connection between the Java application DiceGame to the associated MongoDB database.
	 * 
	 */
	private void checkDatabaseConnection () {
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
	    	@SuppressWarnings("unused")
			MongoIterable<String> collectionNames = database.listCollectionNames();
	    	@SuppressWarnings("unused")
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
	 * Method removeAllDocuments : remove all documents in MongoDB.
	 */
	private void removeAllDocuments () {
		LOG.info("Remove all documents in collection : " + COLLECTION_NAME);
		
	    try {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI(SERVER_URL));
	    	MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
	    	MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
	    	
//	    	collection.deleteMany(null);
	    	collection.drop();
	    	
	    	mongoClient.close();
	    	LOG.info("MongoDB : all documents are deleted in collection : " + COLLECTION_NAME);
	    	
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
		LOG.info("Insert Many into collection : " + COLLECTION_NAME);
		  
	    try {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI(SERVER_URL));
	    	MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
	    	MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
	    	
	    	List<Document> documents = new ArrayList<Document>();
	    	int index = 0;
	    	for (Entry entry : scores) {
	    		index++;
	    		documents.add(new Document(COLLECTION_FIELD_ID, index).append(COLLECTION_FIELD_NAME, entry.getName()).append(COLLECTION_FIELD_SCORE, entry.getScore()));
			}
	    	collection.insertMany(documents);
	    	
   	    	mongoClient.close();
	    	LOG.info("MongoDB : the list of Entry is inserted.");
	    	
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
		LOG.info("Get Many from table : " + COLLECTION_NAME);
		
		List<Entry> scores = new ArrayList<Entry>();
			    	    
	    try {
	    	MongoClient mongoClient = new MongoClient(new MongoClientURI(SERVER_URL));
	    	MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
	    	MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
	    	
	    	MongoCursor<Document> cursor = collection.find().iterator();
	    	try {
	    	    while (cursor.hasNext()) {
//	    	        System.out.println(cursor.next().toJson());
	    	    	Document entry = cursor.next();
	    	        scores.add(new Entry(entry.getString("name"), entry.getInteger("score")));
	    	    }
	    	} finally {
	    	    cursor.close();
	    	}
	    		    	
	    	mongoClient.close();
	    	LOG.info("MongoDB : succed to get the list of Entry in database.");
	    	
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
		this.scores = scores;
	}
	
	/* ========================================= Main ================================================== */ /*=========================================*/
}

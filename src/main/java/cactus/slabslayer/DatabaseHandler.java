package cactus.slabslayer;


import static com.mongodb.client.model.Filters.eq;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Connects to the MongoDB database.
 *
 * @author Cyrus
 * @author Trevor
 * @version 2023
 */
public class DatabaseHandler {
  /** The singleton instance of the database handler. */
  private static DatabaseHandler instance;

  /** The MongoDB client. */
  private MongoClient mongoClient;

  /** The database. */
  private MongoDatabase database;

  /**
   * Constructs a new database handler.
   *
   * @throws InterruptedException if the thread is interrupted
   */
  private DatabaseHandler() throws InterruptedException {
    ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://cactus:cactus123@cactus.lxqiguy.mongodb.net/"
                    + "?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("test");
  }

  /**
   * Gets the singleton instance of the database handler.
   *
   * @return the singleton instance of the database handler
   * @throws InterruptedException if the thread is interrupted
   */
  public static DatabaseHandler getInstance() throws InterruptedException {
    if (instance == null) {
      synchronized (DatabaseHandler.class) {
        if (instance == null) {
          instance = new DatabaseHandler();
        }
      }
    }
    return instance;
  }

  public MongoDatabase getDatabase() {
    return database;
  }

  public void close() {
    mongoClient.close();
  }

  /**
   * Saves the game to the database.
   *
   * @param db the database
   * @param filePath the file path
   */
  public static void save(MongoDatabase db, String filePath) {
    Document document = new Document();
    document.append("filePath", filePath);
    db.getCollection("saves").insertOne(document);
  }

  public static void read(MongoDatabase db, String filePath) {
    db.getCollection("saves").find(eq("filePath", filePath)).forEach(System.out::println);
  }

  /**
   * Drives the program.
   *
   * @param args unused
   * @throws InterruptedException if the thread is interrupted
   */
  public static void main(String[] args) throws InterruptedException {
    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    MongoDatabase database = databaseHandler.getDatabase();
    save(database, "C:\\Users\\Trevor\\Desktop\\game-save.json");
  }
}

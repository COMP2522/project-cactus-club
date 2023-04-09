package cactus.slabslayer;

import static processing.core.PApplet.loadJSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import org.bson.Document;
import processing.data.JSONArray;

/**
 * Connects to the MongoDB database and handles all database operations.
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

  /**
   * Gets the database.
   *
   * @return the database
   */
  public MongoDatabase getDatabase() {
    return database;
  }

  /**
   * Closes the database connection.
   */
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
    Thread thread = new Thread(() -> {
      Document document = new Document();
      JSONArray json = new JSONArray();
      String filename = "game-save.json";

      json = loadJSONArray(new File(filename));

      String jsonString = json.toString();

      document.append("saveData", jsonString);
      db.getCollection("saves").insertOne(document);
      System.out.println("Uploaded save data to database!");
    });
    thread.start();
  }

//  public static CompletableFuture<Void> save(MongoDatabase db, String filePath) {
//    return CompletableFuture.runAsync(() -> {
//      Document document = new Document();
//      JSONArray json = new JSONArray();
//      String filename = "game-save.json";
//
//      json = loadJSONArray(new File(filename));
//
//      String jsonString = json.toString();
//
//      document.append("saveData", jsonString);
//      db.getCollection("saves").insertOne(document);
//    });
//  }

  //  /**
  //   * Saves the game to the database.
  //   *
  //   * @param db the database
  //   * @param filePath the file path
  //   */
  //  public static void save(MongoDatabase db, String filePath) {
  //    Document document = new Document();
  //    JSONArray json = new JSONArray();
  //    String filename = "game-save.json";
  //
  //    json = loadJSONArray(new File(filename));
  //
  //    String jsonString = json.toString();
  //
  //    document.append("saveData", jsonString);
  //    db.getCollection("saves").insertOne(document);
  //  }


  /**
   * Reads the most recent game save from the database.
   *
   * @param db the database
   */
  public static void read(MongoDatabase db) {
    Thread thread = new Thread(() -> {
      MongoCollection<Document> collection = db.getCollection("saves");
      Document mostRecentDoc = collection.find().sort(
          new BasicDBObject("$natural", -1)).limit(1).first();
      String mostRecentDocString = mostRecentDoc.toJson();
      mostRecentDocString = mostRecentDocString.substring(59,
          mostRecentDocString.length() - 2).replaceAll("", "");
      System.out.println(mostRecentDocString);
    });
    thread.start();
  }

//  public static CompletableFuture<String> read(MongoDatabase db) {
//    return CompletableFuture.supplyAsync(() -> {
//      MongoCollection<Document> collection = db.getCollection("saves");
//      Document mostRecentDoc = collection.find().sort(new BasicDBObject("$natural", -1)).limit(1).first();
//      String mostRecentDocString = mostRecentDoc.toJson();
//      mostRecentDocString = mostRecentDocString.substring(59, mostRecentDocString.length() - 2).replaceAll("", "");
//      return mostRecentDocString;
//    });
//  }

  //  /**
  //   * Reads the most recent game save from the database.
  //   *
  //   * @param db the database
  //   * @return the most recent game save
  //   */
  //  public static void read(MongoDatabase db) {
  //    MongoCollection<Document> collection = db.getCollection("saves");
  //    Document mostRecentDoc = collection.find().sort(new BasicDBObject("$natural", -1)).limit(1).first();
  //    String mostRecentDocString = mostRecentDoc.toJson();
  //    mostRecentDocString = mostRecentDocString.substring(59, mostRecentDocString.length() - 2).replaceAll("", "");
  //    System.out.println(mostRecentDocString);
  //  }

  /**
   * Drives the program.
   *
   * @param args unused
   * @throws InterruptedException if the thread is interrupted
   */
  public static void main(String[] args) throws InterruptedException {
    //    DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
    //    MongoDatabase database = databaseHandler.getDatabase();
    //    String filePath = "game-save.json";
    //
    ////    save(database, filePath);
    ////    read(database);
    //    databaseHandler.close();
    save(getInstance().getDatabase(), "game-save.json");
    read(getInstance().getDatabase());
  }
}

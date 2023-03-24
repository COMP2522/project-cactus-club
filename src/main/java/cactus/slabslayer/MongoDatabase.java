package cactus.slabslayer;


import static com.mongodb.client.model.Filters.eq;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * Connects to the MongoDB database.
 *
 * @author Cyrus
 * @author Trevor
 * @version 2023
 */
public class MongoDatabase {

  private static void initializeDatabase() {
    ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://cactus:cactus123@cactus.lxqiguy.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    com.mongodb.client.MongoDatabase database = mongoClient.getDatabase("test");
  }

  public static void main(String[] args) {
    initializeDatabase();
  }
}

package cactus.slabslayer;

import static processing.core.PApplet.loadJSONArray;
import static processing.core.PApplet.loadJSONObject;

import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import processing.core.PVector;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
 * Represents the game process that handles saving and loading
 * the game.
 */
public class GameSaveHandler extends GameProcess {
  /**
   * The interval between autosaves in milliseconds.
   */
  private static final int AUTOSAVE_INTERVAL_MS = 5000;
  /**
   * The game to save.
   */
  private Game game;
  /**
   * The directory to save the game to.
   */
  private String saveDir;
  /**
   * The time of the last autosave.
   */
  private long lastAutosaveTime;

  /**
   * Constructs a new GameSaveHandler.
   *
   * @param game    the game to save
   * @param saveDir the directory to save the game to
   */
  public GameSaveHandler(Game game, String saveDir) {
    lastAutosaveTime = System.currentTimeMillis();
    this.game = game;
    this.saveDir = saveDir;
  }

  /**
   * Constructs a new GameSaveHandler.
   *
   * @param game             the game to save
   * @param saveDir          the directory to save the game to
   * @param lastAutosaveTime the time of the last autosave
   */
  public GameSaveHandler(Game game, String saveDir, long lastAutosaveTime) {
    this.lastAutosaveTime = lastAutosaveTime;
    this.game = game;
    this.saveDir = saveDir;
  }

  /**
   * Updates the save file.
   */
  @Override
  public void update() throws InterruptedException {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastAutosaveTime >= AUTOSAVE_INTERVAL_MS) {
      // Save the game elements periodically
      saveGame(game.getJsonables(), saveDir);
      System.out.println("Autosave completed.");
      lastAutosaveTime = currentTime;

      JSONArray jsonArray = loadJSONArray(new File(saveDir)); // create the JSON object to save
      MongoDatabase db = DatabaseHandler.getInstance().getDatabase();

      Thread saveThread = new Thread(() -> {
        DatabaseHandler.save(db, jsonArray);
      });
      saveThread.start();
    }
  }

  /**
   * Saves the game to a file.
   *
   * @param elements the elements to save
   * @param dir      the directory to save the game to
   */
  public void saveGame(ArrayList<Jsonable> elements, String dir) {
    JSONArray jsonElements = new JSONArray();
    for (Jsonable element : elements) {
      jsonElements.append(JSONObject.parse(element.toJson()));
    }
    saveJsonArray(jsonElements, dir);
  }

  /**
   * saves the JSONArray to a file.
   *
   * @param jsonElements the JSONArray to save
   * @param dir          the directory to save the game to
   */
  private void saveJsonArray(JSONArray jsonElements, String dir) {
    File file = new File(dir);
    try {
      FileWriter writer = new FileWriter(file);
      writer.write(jsonElements.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads the game from a file.
   *
   * @param dir    the directory to load the game from
   * @param window the game window
   * @param in     the game's input handler
   * @param game   the game to load
   */
  public void loadGame(String dir, Window window, InputHandler in, Game game) {
    JSONArray jsonElements = loadJSONArray(new File(dir));
    for (int i = 0; i < jsonElements.size(); i++) {
      JSONObject jsonElement = jsonElements.getJSONObject(i);
      String type = jsonElement.getString("type");

      // Use a switch statement to create the appropriate game element object
      switch (type) {
        case "Paddle":
          Paddle pad = new Paddle(window);
          game.spawnPaddle(pad.fromJson(jsonElement.toString()));
          break;
        case "PowerUp":
          PowerUp powerUp = new PowerUp();
          game.spawnPowerUp(powerUp.fromJson(jsonElement.toString()));
          break;
        case "Ball":
          Ball ball = new Ball(window);
          game.spawnBall(ball.fromJson(jsonElement.toString()));
          break;
        case "Slab":
          Slab slab = new Slab(1, 1, 1, 1, 1, 1, 1, 1, window);
          game.spawnSlab(slab.fromJson(jsonElement.toString()));
          break;
        case "Wall":
          Wall wall = new Wall(1f, 1f, 1f, 1f, 1f, 1f, window);
          game.spawnWall(wall.fromJson(jsonElement.toString()));
          break;
        case "Layout":
          Layout layout = new Layout(window);
          game.spawnLayout(layout.fromJson(jsonElement.toString()));
          break;
        case "Button":
          Button button = new Button(new Window());
          game.spawnButton(button.fromJson(jsonElement.toString()));
          break;
        case "TextBox":
          TextBox textbox = new TextBox(window);
          game.spawnTextBox(textbox.fromJson(jsonElement.toString()));
          break;
        case "ScoreBox":
          ScoreBox scorebox = new ScoreBox(window);
          game.spawnScoreBox(scorebox.fromJson(jsonElement.toString()));
          break;
        default:
          throw new IllegalArgumentException("Unknown game element type: " + type);
      }
    }
  }
}

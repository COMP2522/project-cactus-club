package cactus.slabslayer;

import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static processing.core.PApplet.loadJSONArray;

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
  public void update() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastAutosaveTime >= AUTOSAVE_INTERVAL_MS) {
      // Save the game elements periodically
      saveGame(game.getJsonables(), saveDir);
      System.out.println("Autosave completed.");
      lastAutosaveTime = currentTime;
    }
  }

  /**
   * Saves the game to a file.
   *
   * @param elements the elements to save
   * @param dir      the directory to save the game to
   */
  public void saveGame(ArrayList<JSONable> elements, String dir) {
    JSONArray jsonElements = new JSONArray();
    for (JSONable element : elements) {
      jsonElements.append(JSONObject.parse(element.toJSON()));
    }
    saveJSONArray(jsonElements, dir);
  }

  /**
   * saves the JSONArray to a file.
   *
   * @param jsonElements the JSONArray to save
   * @param dir          the directory to save the game to
   */
  private void saveJSONArray(JSONArray jsonElements, String dir) {
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
    // TODO Remove print statements once we are done testing

    JSONArray jsonElements = loadJSONArray(new File(dir));
    for (int i = 0; i < jsonElements.size(); i++) {
      JSONObject jsonElement = jsonElements.getJSONObject(i);
      String type = jsonElement.getString("type");

      // Use a switch statement to create the appropriate game element object
      switch (type) {
        case "Paddle":
          Paddle pad = new Paddle(window);
          System.out.println(jsonElement.toString());
          Paddle padOutput = (Paddle) pad.fromJSON(jsonElement.toString());
          System.out.println(padOutput.toJSON());
          game.spawnPaddle(padOutput);
          break;
        case "PowerUp":
          PowerUp powerUp = new PowerUp();
          System.out.println(jsonElement.toString());
          PowerUp powOutput = (PowerUp) powerUp.fromJSON(jsonElement.toString());
          System.out.println(powOutput.toJSON());
          game.spawnPowerUp(powOutput);
          break;
        case "Ball":
          Ball ball = new Ball(window);
          System.out.println(jsonElement.toString());
          Ball ballOutput = (Ball) ball.fromJSON(jsonElement.toString());
          System.out.println(ballOutput.toJSON());
          game.spawnBall(ballOutput);
          break;
        case "Slab":
          Slab slab = new Slab(1, 1, 1, 1, 1, 1, 1, 1, window);
          System.out.println(jsonElement.toString());
          Slab slabOutput = (Slab) slab.fromJSON(jsonElement.toString());
          System.out.println(slabOutput.toJSON());
          game.spawnSlab(slabOutput);
          break;
        case "Wall":
          Wall wall = new Wall(1f, 1f, 1f, 1f, 1f, 1f, window);
          System.out.println(jsonElement.toString());
          Wall wallOutput = (Wall) wall.fromJSON(jsonElement.toString());
          System.out.println(wallOutput.toJSON());
          game.spawnWall(wallOutput);
          break;
        case "Layout":
          Layout layout = new Layout(window);
          System.out.println(jsonElement.toString());
          Layout layoutOutput = (Layout) layout.fromJSON(jsonElement.toString());
          System.out.println(layoutOutput.toJSON());
          game.spawnLayout(layoutOutput);
          break;
        case "Button":
          Button button = new Button(new Window());
          System.out.println(jsonElement.toString());
          Button buttonOutput = (Button) button.fromJSON(jsonElement.toString());
          System.out.println(buttonOutput.toJSON());
          game.spawnButton(buttonOutput);
          break;
        case "TextBox":
          TextBox textbox = new TextBox(window);
          System.out.println(jsonElement.toString());
          TextBox textboxOutput = (TextBox) textbox.fromJSON(jsonElement.toString());
          System.out.println(textboxOutput.toJSON());
          game.spawnTextBox(textboxOutput);
          break;
        default:
          throw new IllegalArgumentException("Unknown game element type: " + type);
      }
    }
  }

  /**
   * tests the save and load functions.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // Create a window and input handler
    Window window = new Window();
    InputHandler in = new InputHandler(window);

    // Create some game elements to save
    Ball ball = new Ball(new Window());
    Paddle paddle = new Paddle(new Window());
    Slab slab = new Slab(200f, 200f, 1, 100f, 100f, 1.0f, 1.0f, 1.0f, new Window());
    Slab slab2 = new Slab(1, 1, 1, 1, 1, 1, 1, 1, new Window());
    Wall wall = new Wall(1, 1, 1, 1, 1, 1, new Window());
    Layout layout = new Layout(new Window());
    layout.addLayoutElement(new Button(new Window()));
    layout.addLayoutElement(new TextBox(new Window()));

    // Create an ArrayList to store the game elements
    ArrayList<JSONable> gameElements = new ArrayList<>();
    gameElements.add(ball);
    gameElements.add(paddle);
    gameElements.add(slab);
    gameElements.add(slab2);
    gameElements.add(wall);
    gameElements.add(layout);

    // Save the game elements to a file
    GameSaveHandler saveHandler = new GameSaveHandler(Game.getGameInstance(window, in), "game-save.json");
    saveHandler.saveGame(gameElements, "game-save.json");

    // Print a success message
    System.out.println("Game elements saved successfully.");
    saveHandler.loadGame("game-save.json", window, in, Game.getGameInstance(window, in));
  }
}






package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameSaveHandlerTest {
  private GameSaveHandler saveHandler;
  private Ball ball;
  private Paddle paddle;
  private Slab slab;
  private TextBox textBox;
  private ScoreBox scoreBox;
  private PowerUp powerUp;
  private Layout layout;
  private Button button;
  private Wall wall;
  private ArrayList<JSONable> gameElements;
  private Game game;
  private Window window;

  @BeforeEach
  public void setUp() {
    game = Game.getGameInstance();
    window = new Window();
    saveHandler = new GameSaveHandler(game, "test-save.json");
    ball = new Ball(window);
    paddle = new Paddle(window);
    slab = new Slab(1.0f, 1.0f, 1, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, new Window());
    textBox = new TextBox(window);
    scoreBox = new ScoreBox(window);
    powerUp = new PowerUp();
    button = new Button(window);
    layout = new Layout(window);
    layout.addLayoutElement(textBox);
    layout.addLayoutElement(scoreBox);
    layout.addLayoutElement(button);
    wall = new Wall();
    gameElements = new ArrayList<>();
    gameElements.add(ball);
    gameElements.add(wall);
    gameElements.add(paddle);
    gameElements.add(slab);
    gameElements.add(textBox);
    gameElements.add(scoreBox);
    gameElements.add(powerUp);
    gameElements.add(button);
    gameElements.add(layout);
  }

  @Test
  public void testSaveGame() {
    String dir = "test-save.json";
    saveHandler.saveGame(gameElements, dir);
    JSONArray loadedJsonElements = PApplet.loadJSONArray(new File(dir));
    assertEquals(gameElements.size(), loadedJsonElements.size());
    for (int i = 0; i < gameElements.size(); i++) {
      JSONObject expected = JSONObject.parse(gameElements.get(i).toJSON());
      JSONObject actual = loadedJsonElements.getJSONObject(i);

      if (expected.getString("type").equals("Ball")) {
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vx"), actual.getJSONObject("constructorVars").getFloat("vx"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vy"), actual.getJSONObject("constructorVars").getFloat("vy"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("xPos"), actual.getJSONObject("constructorVars").getFloat("xPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("yPos"), actual.getJSONObject("constructorVars").getFloat("yPos"), 0.01f);
      } else if (expected.getString("type").equals("Paddle")) {
        assertEquals(expected.getJSONObject("constructorVars").getFloat("xpos"), actual.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("width"), actual.getJSONObject("constructorVars").getFloat("width"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("height"), actual.getJSONObject("constructorVars").getFloat("height"), 0.01f);
      } else if (expected.getString("type").equals("Slab")) {
        assertEquals(expected.getJSONObject("constructorVars").getFloat("xpos"), actual.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("ypos"), actual.getJSONObject("constructorVars").getFloat("ypos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("width"), actual.getJSONObject("constructorVars").getFloat("width"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("height"), actual.getJSONObject("constructorVars").getFloat("height"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vx"), actual.getJSONObject("constructorVars").getFloat("vx"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vy"), actual.getJSONObject("constructorVars").getFloat("vy"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("health"), actual.getJSONObject("constructorVars").getInt("health"));
        assertEquals(expected.getJSONObject("constructorVars").getFloat("pdropChance"), actual.getJSONObject("constructorVars").getFloat("pdropChance"), 0.01f);
      } else if (expected.getString("type").equals("TextBox")) {
        assertEquals(expected.getJSONObject("constructorVars").getString("text"), actual.getJSONObject("constructorVars").getString("text"));
        assertEquals(expected.getJSONObject("constructorVars").getInt("xPos"), actual.getJSONObject("constructorVars").getInt("xPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("yPos"), actual.getJSONObject("constructorVars").getInt("yPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("size"), actual.getJSONObject("constructorVars").getInt("size"), 0.01f);
      } else if (expected.getString("type").equals("ScoreBox")) {
        assertEquals(expected.getJSONObject("constructorVars").getInt("size"), actual.getJSONObject("constructorVars").getInt("size"));
        assertEquals(expected.getJSONObject("constructorVars").getInt("xPos"), actual.getJSONObject("constructorVars").getInt("xPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("yPos"), actual.getJSONObject("constructorVars").getInt("yPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getString("text"), actual.getJSONObject("constructorVars").getString("text"));
      } else if (expected.getString("type").equals("PowerUp")) {
        assertEquals(expected.getJSONObject("constructorVars").getInt("type"), actual.getJSONObject("constructorVars").getInt("type"));
        assertEquals(expected.getJSONObject("constructorVars").getFloat("xpos"), actual.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("ypos"), actual.getJSONObject("constructorVars").getFloat("ypos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("yvel"), actual.getJSONObject("constructorVars").getFloat("yvel"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("diameter"), actual.getJSONObject("constructorVars").getFloat("diameter"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("health"), actual.getJSONObject("constructorVars").getInt("health"), 0.01f);
      } else if (expected.getString("type").equals("Button")) {
        assertEquals(expected.getJSONObject("constructorVars").getString("text"), actual.getJSONObject("constructorVars").getString("text"));
        assertEquals(expected.getJSONObject("constructorVars").getInt("localXPos"), actual.getJSONObject("constructorVars").getInt("localXPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("localYPos"), actual.getJSONObject("constructorVars").getInt("localYPos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("width"), actual.getJSONObject("constructorVars").getInt("width"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getInt("height"), actual.getJSONObject("constructorVars").getInt("height"), 0.01f);
      } else if (expected.getString("type").equals("Wall")) {
        assertEquals(expected.getJSONObject("constructorVars").getFloat("xpos"), actual.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("ypos"), actual.getJSONObject("constructorVars").getFloat("ypos"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("width"), actual.getJSONObject("constructorVars").getFloat("width"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("height"), actual.getJSONObject("constructorVars").getFloat("height"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vx"), actual.getJSONObject("constructorVars").getFloat("vx"), 0.01f);
        assertEquals(expected.getJSONObject("constructorVars").getFloat("vy"), actual.getJSONObject("constructorVars").getFloat("vy"), 0.01f);
      } else if (expected.getString("type").equals("Layout")) { // Add check for Layout object
        JSONArray expectedElements = expected.getJSONObject("constructorVars").getJSONArray("layoutElements");
        JSONArray actualElements = actual.getJSONObject("constructorVars").getJSONArray("layoutElements");
        assertEquals(expectedElements.size(), actualElements.size());
        for (int j = 0; j < expectedElements.size(); j++) {
          JSONObject expectedElem = expectedElements.getJSONObject(j);
          JSONObject actualElem = actualElements.getJSONObject(j);
          assertEquals(expectedElem.getString("type"), actualElem.getString("type"));
        }
      }
    }
    File file = new File(dir);
    file.delete();
  }
}
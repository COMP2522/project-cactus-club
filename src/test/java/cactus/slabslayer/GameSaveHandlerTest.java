package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameSaveHandlerTest {
  private GameSaveHandler saveHandler;
  private Ball ball;
  private Paddle paddle;
  private Slab slab;
  private ArrayList<JSONable> gameElements;
  private Game game;
  private String saveDir;
  private InputHandler in;

  @BeforeEach
  public void setUp() {
    in = new InputHandler(new Window());
    Game game = new Game(new Window(), in);
    saveHandler = new GameSaveHandler(game, "test-save.json");
    ball = new Ball(new Window());
    paddle = new Paddle(new Window());
    slab = new Slab(1.0f, 1.0f, 1, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, new Window());
    gameElements = new ArrayList<>();
    gameElements.add(ball);
    gameElements.add(paddle);
    gameElements.add(slab);
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
      }
    }
    File file = new File(dir);
    file.delete();
  }
}
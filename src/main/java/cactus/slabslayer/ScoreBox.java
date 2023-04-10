package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

/**
 * Represents a text box containing the player's score.
 */
public class ScoreBox extends TextBox {

  /**
   * Constructs a new score box with a default message and size.
   * Sets its location to the middle of the layout.
   *
   * @param window as a Window
   */
  public ScoreBox(Window window) {
    super(window);
  }

  /**
   * Constructs a score text box with
   * a custom message, position, and size.
   *
   * @param text as a String
   * @param xpos as an int
   * @param ypos as an int
   * @param size as an int
   */
  public ScoreBox(String text, int xpos, int ypos, int size, int rgb, Window window) {
    super(text, new PVector(xpos, ypos), size, rgb, window);
  }

  /**
   * Constructs a new score box with
   * a custom message, position, and size.
   *
   * @param text     as a String
   * @param localPos as a PVector
   * @param size     as an int
   */
  public ScoreBox(String text, PVector localPos, int size, int rgb, Window window) {
    super(text, localPos, size, rgb, window);
  }

  /**
   * Converts a JSON string to a text box object.
   *
   * @param json JSON string
   * @return text box
   */
  @Override
  public ScoreBox fromJson(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type  = jsonObject.getString("type");

    if ("ScoreBox".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      String text = constructorVars.getString("text");
      int xpos = constructorVars.getInt("xPos");
      int ypos = constructorVars.getInt("yPos");
      int size = constructorVars.getInt("size");
      return new ScoreBox(text, new PVector(xpos, ypos), size, rgb, window);
    }
    // handle other types here
    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

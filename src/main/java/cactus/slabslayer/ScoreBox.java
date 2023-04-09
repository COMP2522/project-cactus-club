package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

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
   * @param xPos as an int
   * @param yPos as an int
   * @param size as an int
   */
  public ScoreBox(String text, int xPos, int yPos, int size, int rgb, Window window) {
    super(text, new PVector(xPos, yPos), size, rgb, window);
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
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type  = jsonObject.getString("type");

    if ("ScoreBox".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      String text = constructorVars.getString("text");
      int xPos = constructorVars.getInt("xPos");
      int yPos = constructorVars.getInt("yPos");
      int size = constructorVars.getInt("size");
      return new ScoreBox(text, new PVector(xPos, yPos), size, rgb, window);
    }
    // handle other types here
    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

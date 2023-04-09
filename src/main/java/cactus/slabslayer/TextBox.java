package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

/**
 * Represents a text box.
 */
public class TextBox extends GameElement {

  /**
   * Text in the text box.
   */
  protected String text;

  /**
   * The font size for the text.
   */
  protected int size;

  /**
   * The RGB values of the text's color.
   */
  protected int rgb;

  /**
   * The x and y coordinates of this text box in relation
   * to the layout it's nested in.
   */
  protected PVector localPos;

  /**
   * The game window.
   */
  protected Window window;

  /**
   * Constructs a new text box with a default message and size.
   * Sets its location to the middle of the layout.
   *
   * @param window as a Window
   */
  public TextBox(Window window) {
    text = "Text goes here!";
    localPos = new PVector(0, 0);
    size = 50;
    this.window = window;
    this.renderPriority = 5;
  }

  /**
   * Constructs a new text box with
   * a custom message, position, and size.
   *
   * @param text as a String
   * @param xpos as an int
   * @param ypos as an int
   * @param size as an int
   */
  public TextBox(String text, int xpos, int ypos, int size, int rgb, Window window) {
    this.text = text;
    this.localPos = new PVector(xpos, ypos);
    this.size = size;
    this.rgb = rgb;
    this.window = window;
    this.renderPriority = 5;
  }

  /**
   * Constructs a new text box with
   * a custom message, position, and size.
   *
   * @param text     as a String
   * @param localPos as a PVector
   * @param size     as an int
   */
  public TextBox(String text, PVector localPos, int size, int rgb, Window window) {
    this.text = text;
    this.localPos = localPos;
    this.size = size;
    this.rgb = rgb;
    this.window = window;
    this.renderPriority = 5;
  }

  /**
   * Returns the text in this text box.
   *
   * @return text as a String
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the text to a new String.
   *
   * @param text the text to set, as a String
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets the current font size of the text.
   *
   * @return size as an int
   */
  public int getSize() {
    return size;
  }

  /**
   * Sets the size of the font.
   *
   * @param size as an int
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Get the RGB values of the text.
   *
   * @return rgb as an int
   */
  public int getRgb() {
    return rgb;
  }

  /**
   * Set the RGB values of the text.
   *
   * @param rgb as an int
   */
  public void setRgb(int rgb) {
    this.rgb = rgb;
  }

  /**
   * Gets the local position in relation to its layout.
   *
   * @return localPos as a PVector
   */
  public PVector getLocalPos() {
    return localPos;
  }

  /**
   * Gets the local x pos.
   *
   * @return local x pos as an int
   */
  public int getLocalXPos() {
    return (int) localPos.x;
  }

  /**
   * Gets the local y pos.
   *
   * @return local y pos as an int
   */
  public int getLocalYPos() {
    return (int) localPos.y;
  }

  /**
   * Sets the local x pos.
   *
   * @param posX as an int
   */
  public void setLocalXpos(int posX) {
    this.localPos.x = posX;
  }

  /**
   * Sets the local y pos.
   *
   * @param posY as an int
   */
  public void setLocalYpos(int posY) {
    this.localPos.y = posY;
  }

  /**
   * Renders the layout in the window.
   */
  @Override
  public void render() {
    window.fill(rgb);
    window.textSize(size);
    window.text(text, localPos.x, localPos.y);
  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJson() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setString("text", text);
    constructorVars.setInt("xPos", getLocalXPos());
    constructorVars.setInt("yPos", getLocalYPos());
    constructorVars.setInt("size", size);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }


  /**
   * Converts a JSON string to a text box object.
   *
   * @param json JSON string
   * @return text box
   */
  @Override
  public TextBox fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type  = jsonObject.getString("type");

    if ("TextBox".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      String text = constructorVars.getString("text");
      int xpos = constructorVars.getInt("xPos");
      int ypos = constructorVars.getInt("yPos");
      int size = constructorVars.getInt("size");
      return new TextBox(text, new PVector(xpos, ypos), size, rgb, window);
    }
    // handle other types here
    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

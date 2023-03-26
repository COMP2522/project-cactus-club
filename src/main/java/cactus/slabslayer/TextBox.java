package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

public class TextBox extends GameElement {

  /**
   * Text in the text box.
   */
  private String text;

  /**
   * The font size for the text.
   */
  private int size;

  /**
   * The x and y coordinates of this text box in relation
   * to the layout it's nested in.
   */
  private PVector localPos;

  /**
   * The game window.
   */
  private Window window;

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
  }

  /**
   * Constructs a new text box with
   * a custom message, position, and size.
   *
   * @param text as a String
   * @param xPos as an int
   * @param yPos as an int
   * @param size as an int
   */
  public TextBox(String text, int xPos, int yPos, int size, Window window) {
    this.text = text;
    this.localPos = new PVector(xPos, yPos);
    this.size = size;
    this.window = window;
  }

  /**
   * Constructs a new text box with
   * a custom message, position, and size.
   *
   * @param text     as a String
   * @param localPos as a PVector
   * @param size     as an int
   */
  public TextBox(String text, PVector localPos, int size) {
    this.text = text;
    this.localPos = localPos;
    this.size = size;
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
   * Gets the local position in relation to its layout.
   *
   * @return localPos as a PVector
   */
  public PVector getLocalPos() {
    return localPos;
  }

  /**
   * Sets the local x pos.
   *
   * @param posX as an int
   */
  public void setLocalXPos(int posX) {
    this.localPos.x = posX;
  }

  /**
   * Sets the local y pos.
   *
   * @param posY as an int
   */
  public void setLocalYPos(int posY) {
    this.localPos.y = posY;
  }

  /**
   * Renders the layout in the window.
   */
  @Override
  public void render() {
    window.textSize(size);
    window.text(text, localPos.x, localPos.y);
  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   * text = "Text goes here!";
   * localPos = new PVector(0, 0);
   * size = 50;
   */
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject contructorVars = new JSONObject();
    contructorVars.setString("text", text);
    contructorVars.setInt("xPos", (int) localPos.x);
    contructorVars.setInt("yPos", (int) localPos.y);
    contructorVars.setInt("size", size);
    json.setJSONObject("constructorVars", contructorVars);
    return json.toString();
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

    if ("TextBox".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      String text = constructorVars.getString("text");
      int xPos = constructorVars.getInt("xPos");
      int yPos = constructorVars.getInt("yPos");
      int size = constructorVars.getInt("size");
      return new TextBox(text, xPos, yPos, size, window);
    }
    // handle other types here
    throw new IllegalArgumentException("Unknown type: " + type);
  }

  public static void main(String[] args) {
    Window window = new Window();
    TextBox textBox = new TextBox("here", 100, 100, 50, window);
    System.out.println(textBox.toJSON());
    TextBox textBox1 = (TextBox) textBox.fromJSON(textBox.toJSON());
    System.out.println(textBox1.toJSON());
  }

}

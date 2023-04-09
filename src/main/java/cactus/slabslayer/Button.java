package cactus.slabslayer;

import processing.core.PConstants;
import processing.core.PVector;
import processing.data.JSONObject;

public class Button extends GameElement {

  /**
   * Text in the button.
   */
  protected String text;

  /**
   * The width of the button.
   */
  protected int width;

  /**
   * The height of the button.
   */
  protected int height;

  /**
   * The x and y coordinates of this button in relation
   * to the layout it's nested in.
   */
  protected PVector localPos;

  /**
   * The game window.
   */
  protected Window window;

  /**
   * Constructs a new button.
   */
  public Button(Window window) {
    text = "My Button";
    width = 300;
    height = 150;
    this.window = window;
    localPos = new PVector(50, 50);
  }

  /**
   * Constructs a new button.
   */
  public Button(String text, int width, int height, PVector localPos, Window window) {
    this.text = text;
    this.width = width;
    this.height = height;
    this.window = window;
    this.localPos = localPos;
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
   * @param text as a String
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Gets the width of the button.
   *
   * @return width as an int
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width of the button.
   *
   * @param width as an int
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the height of the button.
   *
   * @return height as an int
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of the button.
   *
   * @param height as an int
   */
  public void setHeight(int height) {
    this.height = height;
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
   * Sets the local pos.
   *
   * @param localPos as a PVector
   */
  public void setLocalPos(PVector localPos) {
    this.localPos = localPos;
  }

  /**
   * Checks if the current mouse position is in the
   * button's box.
   *
   * @return true/false
   */
  public boolean mouseInBounds() {

    return window.mouseX >= localPos.x && window.mouseX <= localPos.x + width &&
            window.mouseY >= localPos.y && window.mouseY <= localPos.y + height;

  }

  /**
   * Executes the button's given function if clicked.
   *
   * Default button has no assigned function to execute.
   */
  public void execute() {
    System.out.println("No function assigned to this button.");
  }

  /**
   * Renders the button in the window.
   */
  @Override
  public void render() {

    window.fill(255);
    window.rect(localPos.x, localPos.y, width, height);

    window.fill(0);
    window.textSize(width/5);
    window.textAlign(PConstants.CENTER, PConstants.CENTER);
    window.text(text, localPos.x + (width/2), localPos.y + (height/2.5f));
    window.textAlign(PConstants.LEFT, PConstants.BOTTOM);

  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   * public Button(Window window) {
   * buttons.add(this);
   * width = 300;
   * height = 150;
   * this.window = window;
   * localPos = new PVector(50, 50);
   * }
   */
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setString("text", text);
    constructorVars.setInt("width", width);
    constructorVars.setInt("height", height);
    constructorVars.setFloat("localXPos", localPos.x);
    constructorVars.setFloat("localYPos", localPos.y);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a button object.
   *
   * @param json JSON string
   * @return button
   */
  @Override
  public Button fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Button".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      String text = constructorVars.getString("text");
      int width = constructorVars.getInt("width");
      int height = constructorVars.getInt("height");
      int localXPos = constructorVars.getInt("localXPos");
      int localYPos = constructorVars.getInt("localYPos");
      Button button = new Button(window);
      button.setText(text);
      button.setWidth(width);
      button.setHeight(height);
      button.setLocalXPos(localXPos);
      button.setLocalYPos(localYPos);
      return button;
    }

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  public static void main(String[] args) {
    Button button = new Button(new Window());
    System.out.println(button.toJSON());
    Button output = (Button) button.fromJSON(button.toJSON());
    System.out.println(output.toJSON());
  }
}

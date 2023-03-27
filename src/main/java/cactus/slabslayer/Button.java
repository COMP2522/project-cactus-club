package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

import java.util.ArrayList;

public class Button extends GameElement {

  /**
   * The width of the button.
   */
  private int width;

  /**
   * The height of the button.
   */
  private int height;

  /**
   * The x and y coordinates of this button in relation
   * to the layout it's nested in.
   */
  private PVector localPos;

  /**
   * The game window.
   */
  private Window window;

  /**
   * A list of all buttons that currently exist.
   * Used for input handler to check if mouse is clicked
   * in each button's bounds.
   */
  public static ArrayList<Button> buttons = new ArrayList<>();

  /**
   * Constructs a new button.
   */
  public Button(Window window) {
    buttons.add(this);
    width = 300;
    height = 150;
    this.window = window;
    localPos = new PVector(50, 50);
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
   */
  public void execute() {
    System.out.println("Executing button's function");
    // to do
  }

  /**
   * Renders the button in the window.
   */
  @Override
  public void render() {
    window.rect(localPos.x, localPos.y, width, height);
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
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Button".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      int width = constructorVars.getInt("width");
      int height = constructorVars.getInt("height");
      int localXPos = constructorVars.getInt("localXPos");
      int localYPos = constructorVars.getInt("localYPos");
      Button button = new Button(window);
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

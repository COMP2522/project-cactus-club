package cactus.slabslayer;

import processing.data.JSONObject;

import java.awt.*;

/**
 * Represents a paddle object.
 */
public class Paddle extends GameElement implements Moveable, Collidable {
  /**
   * Width of the paddle.
   */
  float width;

  /**
   * Height of the paddle.
   */
  float height;

  /**
   * x-coord of the paddle.
   */
  float xpos;

  /**
   * Window to render to.
   */
  Window window;

  /**
   * Constructo for paddle object.
   *
   * @param window Window to render to
   */
  public Paddle(Window window) {
    this.width = 100;
    this.height = 20;
    this.xpos = 250 - (width / 2);
    this.window = window;
  }

    /**
     * Constructor for paddle object.
     *
     * @param width Width of the paddle
     * @param height Height of the paddle
     * @param xpos x-coord of the paddle
     * @param window Window to render to
     */
    public Paddle(float width, float height, float xpos, Window window) {
      this.width = width;
      this.height = height;
      this.xpos = xpos;
      this.window = window;

    }
  /**
   * Controls paddle movement.
   */
  @Override
  public void move() {
    // to do
  }

  /**
   * Renders the paddle in the window.
   */
  public void render() {
    window.stroke(0);
    window.strokeWeight(4);
    window.fill(200, 255, 200);
    window.rect(xpos, window.height/100 * 60, width, height);
  }

  /**
   * Checks if colliding with another object.
   * @param toCheck the Object to check
   * @return true/false if colliding
   */
  @Override
  public boolean isCollidingWith(Object toCheck) {
    // to do
    return false;
  }

  /**
   * Executes collision with another object.
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {
    // to do
  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setFloat("width", width);
    constructorVars.setFloat("height", height);
    constructorVars.setFloat("xpos", xpos);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a paddle object.
   *
   * @param json JSON string
   * @return paddle
   */
  @Override
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Paddle".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      float width = constructorVars.getFloat("width");
      float height = constructorVars.getFloat("height");
      float xpos = constructorVars.getFloat("xpos");
      Paddle paddle = new Paddle(window);
      paddle.width = width;
      paddle.height = height;
      paddle.xpos = xpos;
      return paddle;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }
  public static void main(String[] args) {
    // Create a new Paddle instance
    Paddle paddle = new Paddle(new Window());

    // Serialize Paddle instance to JSON
    String json = paddle.toJSON();
    System.out.println(json);

    // Deserialize JSON to Paddle instance
    Paddle newPaddle = (Paddle) paddle.fromJSON(json);
    System.out.println(newPaddle.toJSON());
  }
}

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
   * x-coord velocity of the paddle.
   */
  float xvel;

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
    this.xvel = 10;
    this.window = window;
  }

  /**
   * Getter for width.
   *
   * @return width
   */
  public float getWidth() {
    return width;
  }

  /**
   * Setter for width.
   *
   * @param width new width
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * Getter for height.
   *
   * @return height
   */
  public float getHeight() {
    return height;
  }

  /**
   * Setter for height.
   *
   * @param height new height
   */
  public void setHeight(float height) {
    this.height = height;
  }

  /**
   * Getter for x-coord.
   *
   * @return x-coord
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Setter for x-coord.
   *
   * @param xpos new x-coord
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Getter for x-coord velocity.
   *
   * @return x-coord velocity
   */
  public float getXvel() {
    return xvel;
  }

  /**
   * Setter for x-coord velocity.
   *
   * @param xvel new x-coord velocity
   */
  public void setXvel(float xvel) {
    this.xvel = xvel;
  }

  /**
   * Controls paddle movement.
   */
  @Override
  public void move(InputHandler in) {
    if (in.isLeft()) {
      xpos -= xvel;
    }
    if (in.isRight()) {
      xpos += xvel;
    }
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

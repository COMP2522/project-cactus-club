package cactus.slabslayer;

import processing.core.PVector;
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
   * y-coord of the paddle.
   */
  float ypos;

  /**
   * x-coord velocity of the paddle.
   */
  float xvel;

  /**
   * The resolution at which to check collisions.
   */
  int checkResolution = 20;

  /**
   * How funneled the refletion angle is between ball and paddle.
   *
   * Bigger is wider max angle.
   */
  int funnelFactor = 10;

  /**
   * Activates and deactivates bebug logs.
   */
  boolean isDebugging = false;

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
    this.width = 150;
    this.height = 20;
    this.xpos = 250 - (width / 2);
    this.ypos = window.height/100*90;
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
   * Getter for y-coord.
   *
   * @return ypos
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Constructor for paddle object.
   *
   * @param width  Width of the paddle
   * @param height Height of the paddle
   * @param xpos   x-coord of the paddle
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
    window.rect(xpos, ypos, width, height);

    // log: debug: renders possible reflection angles of a poddle and a ball
    if (isDebugging) {
      for (int i = 0; i <= width; i += width/checkResolution) {
        PVector angle = new PVector(-1, 0);
        angle.setMag(100);
        float theta = window.map(i, 0, width, window.PI/funnelFactor, window.PI - window.PI/funnelFactor);
        angle.rotate(theta);
        window.line(xpos + i, ypos, xpos + i + angle.x, ypos);
      }
    }
  }

  /**
   * Checks if colliding with another object.
   *
   * @param toCheck the Object to check
   * @return true/false if colliding
   */
  @Override
  public boolean isCollidingWith(Object toCheck) {
    if (toCheck.getClass() == Ball.class) {
      Ball b = (Ball) toCheck;

      // splits the paddle into checkResolution number of points
      // and checks if the ball is contacting any of those points
      for (int i = 0; i <= this.width; i += Math.max(this.width/checkResolution, 1)) {
        PVector segPos = new PVector(xpos + i, window.height/100*90);
        if (!(PVector.dist(segPos, new PVector(b.getXpos(), b.getYpos())) < b.getDiameter()/2)) {
          continue;
        }
        return true;
      }
    }
    if (toCheck.getClass() == PowerUp.class) {
      PowerUp pu = (PowerUp) toCheck;
      if (pu.xpos + pu.getDiameter()/2 > this.xpos &&
              pu.xpos + pu.getDiameter()/2 < this.xpos + width &&
              pu.ypos + pu.getDiameter()/2 > this.ypos &&
              pu.ypos + pu.getDiameter()/2 < this.ypos + height) {
        return true;
      }
    }
    return false;
  }

  /**
   * Executes collision with another object.
   *
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {
    if (collidedWith.getClass() == Ball.class) {
      // paddle does nothing when colliding with ball
    }
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

    String jsonPaddle = "{\n" +
            "  \"type\": \"Paddle\",\n" +
            "  \"constructorVars\": {\n" +
            "    \"xpos\": 3,\n" +
            "    \"width\": 1,\n" +
            "    \"height\": 2\n" +
            "  }\n" +
            "}";

    // Serialize Paddle instance to JSON
    String json = paddle.toJSON();
    System.out.println(json);

    System.out.println(jsonPaddle);

    // Deserialize JSON to Paddle instance
    Paddle newPaddle2 = (Paddle) paddle.fromJSON(jsonPaddle);
//    Paddle newPaddle = (Paddle) paddle.fromJSON(json);

    System.out.println(newPaddle2.toJSON());
  }
}

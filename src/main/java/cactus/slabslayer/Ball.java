package cactus.slabslayer;

import processing.data.JSONObject;

/**
 * Represents a ball object.
 */
public class Ball extends GameElement implements Moveable, Collidable {
  float xpos;
  float ypos;


  float vy;
  float vx;

  Window window;

  /**
   * Constructs a new Ball object with initial position and velocity.
   *
   * @param scene the Window object in which the ball will be rendered and moved
   */
  public Ball(Window scene) {
    xpos = 100;
    ypos = 100;

    vy = 5;
    vx = 5;

    window = scene;
  }

  /**
   * Getter for x-coordinate of ball.
   *
   * @return x-coordinate
   */
  public float getXpos() {
    return xpos;

  }

  /**
   * Getter for y-coordinate of ball.
   *
   * @return y-coordinate
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Getter for x-velocity of ball.
   *
   * @return x-velocity
   */
  public float getVx() {
    return vx;
  }

  /**
   * Getter for y-velocity of ball.
   *
   * @return y-velocity
   */
  public float getVy() {
    return vy;
  }
  
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  public void setVx(float vx) {
    this.vx = vx;
  }

  public void setVy(float vy) {
    this.vy = vy;
  }

  /**
   * Renders the ball in the window.
   */
  public void render() {
    window.ellipse(xpos, ypos, 30, 30);
  }

  /**
   * Moves the ball, reverses velocity if boundary is hit.
   */
  public void move(InputHandler in) {
    xpos += vx;
    ypos += vy;

    if (xpos < 0 || xpos > window.width) {
      vx *= -1;
    }

    if (ypos < 0 || ypos > window.height) {
      vy *= -1;
    }
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

  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setFloat("vx", vx);
    constructorVars.setFloat("xPos", xpos);
    constructorVars.setFloat("vy", vy);
    constructorVars.setFloat("yPos", ypos);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Creates a new Ball object from a JSON string.
   *
   * @param json JSON string
   * @return new Ball object
   */
  @Override
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Ball".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      float xPos = constructorVars.getFloat("xPos");
      float yPos = constructorVars.getFloat("yPos");
      float vy = constructorVars.getFloat("vy");
      float vx = constructorVars.getFloat("vx");
      Ball ball = new Ball(window);
      ball.setXpos(xPos);
      ball.setYpos(yPos);
      ball.setVy(vy);
      ball.setVx(vx);
      return ball;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }


  public static void main(String[] args) {
    Ball ball = new Ball(new Window());
    System.out.println(ball.toJSON());
    Ball newBall = (Ball) ball.fromJSON(ball.toJSON());
    System.out.println(newBall.toJSON());
  }
}

package cactus.slabslayer;

/**
 * Represents a ball object.
 */
public class Ball implements JSONable {
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
  
  public void setXPos(float xPos) {
    this.xPos = xPos;
  }

  public void setYPos(float yPos) {
    this.yPos = yPos;
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
  public void move() {
    xpos += vx;
    ypos += vy;

    if (xpos < 0 || xpos > window.width) {
      vx *= -1;
    }

    if (ypos < 0 || ypos > window.height) {
      vy *= -1;
    }
  }


  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setFloat("xPos", xPos);
    json.setFloat("yPos", yPos);
    json.setFloat("vy", vy);
    json.setFloat("vx", vx);
    return json.toString();
  }

  @Override
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    float xPos = jsonObject.getFloat("xPos");
    float yPos = jsonObject.getFloat("yPos");
    float vy = jsonObject.getFloat("vy");
    float vx = jsonObject.getFloat("vx");
    Ball ball = new Ball(window);
    ball.setXPos(xPos);
    ball.setYPos(yPos);
    ball.setVy(vy);
    ball.setVx(vx);

    return ball;
  }
}



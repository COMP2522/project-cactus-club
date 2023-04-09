package cactus.slabslayer;

import java.util.Random;
import processing.data.JSONObject;

/**
 * Represents a PowerUp object.
 */
public class PowerUp extends GameElement implements Moveable, Collidable, Jsonable {
  /**
   * Type of power up.
   */
  int type;

  /**
   * x-position of power up.
   */
  float xpos;

  /**
   * y-position of power up.
   */
  float ypos;

  /**
   * y-velocity of power up.
   */
  float yvel;

  /**
   * Diameter of power up.
   */
  float diameter;

  int health;

  /**
   * Constructs a PowerUp object with the given parameters.
   *
   * @param type type
   * @param xpos x-position
   * @param ypos y-position
   * @param yvel y-velocity
   * @param diameter diameter
   * @param window window
   */
  public PowerUp(int type, float xpos, float ypos, float yvel, float diameter, Window window) {
    this.type = type;
    this.xpos = xpos;
    this.ypos = ypos;
    this.yvel = yvel;
    this.diameter = diameter;
    this.window = window;
    health = 1;
  }

  /**
   * Set the type of power up.
   *
   * @param type type
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Set the y-velocity of the power up.
   *
   * @param yvel y-velocity
   */
  public void setYvel(float yvel) {
    this.yvel = yvel;
  }

  Random randomType = new Random();
  Window window;

  /**
   * Constructs a power up object with a random type.
   */
  public PowerUp() {
    this.type = randomType.nextInt(4);
    this.xpos = 0;
    this.ypos = 0;
    this.yvel = 2;
    this.diameter = 10;
  }

  /**
   * Gets the x-position of the power up.
   *
   * @return x-position
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Gets the y-position of the power up.
   *
   * @return y-position
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Gets the y-velocity of the power up.
   *
   * @return y-velocity
   */
  public float getYvel() {
    return yvel;
  }

  /**
   * Get the type of power up.
   *
   * @return type
   */
  public int getType() {
    return type;
  }

  /**
   * Set x-position of power up.
   *
   * @param xpos x-position
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Set y-position of power up.
   *
   * @param ypos y-position
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Set the window of the power up.
   *
   * @param window window
   */
  public void setWindow(Window window) {
    this.window = window;
  }

  /**
   * Set the diameter of the power up.
   *
   * @param diameter diameter
   */
  public void setDiameter(float diameter) {
    this.diameter = diameter;
  }

  /**
   * Get the health of the power up.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets diameter of power up.
   *
   * @return diameter
   */
  public float getDiameter() {
    return diameter;
  }

  /**
   * Controls power up movement.
   */
  @Override
  public void move(InputHandler in) {
    ypos += yvel;
  }

  /**
   * Renders the power-up in the window.
   */
  public void render() {
    window.stroke(0);
    window.strokeWeight(4);
    window.fill(255, 255, 128);
    window.ellipse(xpos, ypos, diameter, diameter);
  }

  /**
   * Checks if colliding with another object.
   *
   * @param toCheck the Object to check
   * @return true/false if colliding
   */
  @Override
  public boolean isCollidingWith(Object toCheck) {
    if (toCheck.getClass() == Paddle.class) {
      Paddle p = (Paddle) toCheck;
      if (this.xpos + this.diameter / 2
          > ((Paddle) toCheck).getXpos() - ((Paddle) toCheck).getWidth() / 2
          && this.xpos - this.diameter / 2 < (
              (Paddle) toCheck).getXpos() + ((Paddle) toCheck).getWidth() / 2
          && this.ypos + this.diameter / 2 > (
              (Paddle) toCheck).getYpos() - ((Paddle) toCheck).getHeight() / 2
          && this.ypos - this.diameter / 2 < (
              (Paddle) toCheck).getYpos() + ((Paddle) toCheck).getHeight() / 2) {
        System.out.println("PowerUp collided with paddle");
        return true;
      }
    }
    return false;
//    if (toCheck.getClass() == Paddle.class) {
//        Paddle p = (Paddle) toCheck;
//        // top edge check
//        for (int i = 0; i <= p.getWidth(); i += Math.max(p.getWidth()/100, 1)) {
//        PVector segPos = new PVector(p.getXpos() + i, p.getYpos());
//        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter/2)) {
//          continue;
//        }
//        System.out.println("PowerUp collided with paddle");
//        return true;
//      }
//    }
//    return false;
  }

  /**
   * Executes collision with another object.
   *
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {
    if (collidedWith.getClass() == Paddle.class) {
      health--;
      //for use with different powerups, relocate to Game.java
//      switch (this.type) {
//        case 1:
//          //something here to spawn a new ball.
//          break;
//      }
    }
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
//    constructorVars.setFloat("radius", radius);
    constructorVars.setInt("type", type);
    constructorVars.setFloat("xpos", xpos);
    constructorVars.setFloat("ypos", ypos);
    constructorVars.setFloat("yvel", yvel);
    constructorVars.setFloat("diameter", diameter);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a powerup object.
   *
   * @param json JSON string
   * @return powerup object
   */
  @Override
  public Object fromJson(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("PowerUp".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
//      float radius = constructorVars.getFloat("radius");
      int typeInt = constructorVars.getInt("type");
      float xpos = constructorVars.getFloat("xpos");
      float ypos = constructorVars.getFloat("ypos");
      float yvel = constructorVars.getFloat("yvel");
      float diameter = constructorVars.getFloat("diameter");
      int health = constructorVars.getInt("health");

      PowerUp powerUp = new PowerUp();
//      powerUp.setRadius(radius);
      powerUp.setType(typeInt);
      powerUp.setXpos(xpos);
      powerUp.setYpos(ypos);
      powerUp.setYvel(yvel);
      powerUp.setDiameter(diameter);
      powerUp.setHealth(health);

      return powerUp;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  /**
   * Set the health of the power up.
   *
   * @param health health
   */
  private void setHealth(int health) {
    this.health = health;
  }
}


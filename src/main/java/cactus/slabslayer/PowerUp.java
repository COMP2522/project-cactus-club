package cactus.slabslayer;

import java.util.Random;

public class PowerUp extends GameElement implements Moveable, Collidable, JSONable {
  float radius;
  int type;
  float xpos;
  float ypos;
  float yvel;

  Random randomType = new Random();
  Window window;

  /**
   * Constructs a power up object with a random type.
   */
  public PowerUp() {
    this.type = randomType.nextInt(4);
    this.radius = 10;
    this.xpos = 0;
    this.ypos = 0;
    this.yvel = 5;

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
   * Controls power up movement.
   */
  @Override
  public void move() {

  }

  /**
   * Renders the power up in the window.
   */
  @Override
  public void render() {

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
    return null;
  }

  @Override
  public Object fromJSON(String json) {
    return null;
  }
}

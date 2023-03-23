package cactus.slabslayer;

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
    window.rect(xpos, 480, width, height);
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

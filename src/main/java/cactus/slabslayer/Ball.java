package cactus.slabslayer;

/**
 * Represents a ball object.
 */
public class Ball {
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
}

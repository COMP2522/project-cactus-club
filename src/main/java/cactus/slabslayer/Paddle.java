package cactus.slabslayer;

import processing.data.JSONObject;

/**
 * Represents a paddle object.
 */
public class Paddle implements JSONable {
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
   * Renders the paddle in the window.
   */
  public void render() {
    window.rect(xpos, 480, width, height);
  }

  /**
   * Moves the paddle according to user input.
   *
   * @param input -1 for left, 1 for right, 0 for nothing.
   */
  public void move(int input) {
    if (xpos > 0 && input == -1) {
      xpos--;}
//    } else if (xpos < window.size && input == 1) { //line isn't functional
//      xpos++;
//    }
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

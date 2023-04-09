package cactus.slabslayer;

import java.awt.event.KeyEvent;

/**
 * InputHandler wraps input methods and fields from processing's Window class.
 *
 * @author justin
 * @version 1.0.1
 */
public class InputHandler {
  /**
   * Instance reference to the processing Window object.
   */
  private Window win;

  /**
   * Boolean state of any UP directional button being pressed.
   */
  private boolean upState;

  /**
   * Boolean state of any DOWN directional button being pressed.
   */
  private boolean downState;

  /**
   * Boolean state of any LEFT directional button being pressed.
   */
  private boolean leftState;

  /**
   * Boolean state of any RIGHT directional button being pressed.
   */
  private boolean rightState;

  /**
   * Returns a new InputHandler instance.
   *
   * @param w main window instance to pass in
   */
  public InputHandler(final Window w) {
    this.win = w;

    upState = false;
    downState = false;
    leftState = false;
    rightState = false;
  }

  /**
   * Updates directional boolean values.
   *
   * @param newState boolean value to set pressed key state to.
   */
  public void update(boolean newState) {
    switch (win.keyCode) {
      // UP cases
      case KeyEvent.VK_W:
      case KeyEvent.VK_UP:
        upState = newState;
        break;

      // DOWN cases
      case KeyEvent.VK_S:
      case KeyEvent.VK_DOWN:
        downState = newState;
        break;

      // LEFT cases
      case KeyEvent.VK_A:
      case KeyEvent.VK_LEFT:
        leftState = newState;
        break;

      // RIGHT cases
      case KeyEvent.VK_D:
      case KeyEvent.VK_RIGHT:
        rightState = newState;
        break;

      default:
        break;
    }
  }


  /**
   * Executes a button's function if mouse was clicked in
   * button's box.
   */
  public void checkPressButton() {

    for (Button b : Game.getGameInstance().buttons) {

      if (b.mouseInBounds()) {
        b.execute();
      }

    }

  }


  /**
   * Returns upState field value.
   *
   * @return upState boolean state
   */
  public boolean isUp() {
    return upState;
  }

  /**
   * Returns downState field value.
   *
   * @return downState boolean state
   */
  public boolean isDown() {
    return downState;
  }

  /**
   * Returns leftState field value.
   *
   * @return leftState boolean state
   */
  public boolean isLeft() {
    return leftState;
  }

  /**
   * Returns rightState field value.
   *
   * @return rightState boolean state
   */
  public boolean isRight() {
    return rightState;
  }

}

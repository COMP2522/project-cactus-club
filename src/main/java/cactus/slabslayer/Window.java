package cactus.slabslayer;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Window is the main class to run processing projects.
 *
 * @author justin
 * @version 1.0.1
 */
public class Window extends PApplet {
  /**
   * Instance of Game class.
   */
  Game game;

  /**
   * Instance of InputHandler class.
   */
  InputHandler in;

  /**
   * Loads window settings such as window size.
   */
  public void settings() {
    size(800, 600);
  }

  /**
   * Runs once at the start of execution.
   */
  public void setup() {
    in = new InputHandler(this);

    game = Game.getGameInstance();
    game.setWin(this);
    game.setIn(in);
    game.init();

    game.spawnPaddle();

    game.spawnBall();

    game.spawnScoreBox();




    /*
     * Spawn rows of slabs.
     */

    for (int i = 1; i <= 18; i++) {
      game.spawnSlab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f + this.height / 35f * 2, 5, 0, 0, this);
    }
  }

  /**
   * Periodically runs during program execution.
   */
  public void draw() {
    game.update();
  }

  /**
   * Runs whenever a key is pressed and updates InputHandler accordingly.
   */
  public void keyPressed() {
    in.update(true);
  }

  /**
   * Runs whenever a key is released and updates InputHandler accordingly.
   */
  public void keyReleased() {
    in.update(false);
  }

  /**
   * Runs whenever the mouse is clicked and notifies InputHandler.
   */
  public void mouseClicked() {
    in.checkPressButton();
  }

  /**
   * Main loop for the program.
   *
   * @param args unused
   */
  public static void main(String[] args) {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
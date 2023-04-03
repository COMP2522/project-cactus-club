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
    try {
      SoundManager soundManager = SoundManager.getInstance();
      soundManager.playBGM();
    } catch (Exception e) {
      e.printStackTrace();
    }
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
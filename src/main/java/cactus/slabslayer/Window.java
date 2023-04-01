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
    ArrayList<JSONable> elements = new ArrayList<JSONable>();
    in = new InputHandler(this);

    game = Game.getGameInstance();
    game.setWin(this);
    game.setIn(in);
    game.init();

    game.spawnPaddle();
    elements.add(new Paddle(this));

    game.spawnTextBox(new TextBox("Hello", new PVector(15, 50), 50, this));


    /*
     * Spawn rows of slabs.
     */
    for (int i = 1; i <= 18; i++) {
      game.spawnSlab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f, 5, 0, 0, this);
      elements.add(new Slab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f, 5, 0, 0, this));
    }

    for (int i = 1; i <= 18; i++) {
      game.spawnSlab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f + this.height / 35f * 1, 5, 0, 0, this);
      elements.add(new Slab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f + this.height / 35f * 1, 5, 0, 0, this));
    }

    for (int i = 1; i <= 18; i++) {
      game.spawnSlab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f + this.height / 35f * 2, 5, 0, 0, this);
        elements.add(new Slab(this.width / 20f, this.height / 35f, 1, (this.width / 20f) * i, this.height / 20f + this.height / 35f * 2, 5, 0, 0, this));
    }

    game.spawnBall();
    elements.add(new Ball(this));

    // Clearing everything to test save/load
//    game.init();
    // Used to load a game save
//    GameSaveHandler gsh = new GameSaveHandler(game, "game-save.json");
//    gsh.saveGame(elements, "game-save.json");
//    gsh.loadGame("game-save.json", this, in, game);
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
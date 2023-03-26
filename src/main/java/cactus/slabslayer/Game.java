package cactus.slabslayer;

import java.util.ArrayList;

/**
 * Game will have collections of game elements, and game processing that affect game elements.
 *
 * @author justin
 * @version 1.0.1
 */
public class Game {

  /**
   * Local reference to main window.
   */
  Window win;

  /**
   * Local reference to main program InputHandler.
   */
  InputHandler in;

  /**
   * Paddle instance.
   */
  Paddle pad;

  /**
   * List of all Slab objects
   */
  ArrayList<Slab> slabs;

  /**
   * List of all Ball objects.
   */
  ArrayList<Ball> balls;

  /**
   * List of all Renderable objects
   */
  ArrayList<Renderable> renderables;

  /**
   * List of all Moveable objects.
   */
  ArrayList<Moveable> moveables;

  /**
   * Constructs a new Game object.
   */
  public Game(Window win, InputHandler in) {
    this.win = win;
    this.in = in;

    this.init();
  }

  /**
   * Initializes Game object to initial state.
   * clears all the collections and re-initializes all game processes
   */
  public void init() {
    pad = null;
    slabs = new ArrayList<Slab>();
    balls = new ArrayList<Ball>();
    renderables = new ArrayList<Renderable>();
    moveables = new ArrayList<Moveable>();
  }

  /**
   * Runs periodically in the processing main loop. Handles timing of all process execution.
   */
  public void update() {
    win.background(200, 200, 255);

    for (Moveable m : moveables) {
      m.move(in);
    }

    for (Renderable r : renderables) {
      r.render();
    }
  }

  /**
   * Spawns a paddle with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnPaddle() {
    pad = new Paddle(win);
    renderables.add(pad);
    moveables.add(pad);
  }

    /**
     * Spawns a paddle with arguments and adds it to any necessary ArrayLists
     */
    public void spawnPaddle(Paddle pad) {
      this.pad = pad;
      renderables.add(pad);
    }

  /**
   * Spawns a Slab and adds it to any necessary ArrayLists
   */
  public void spawnSlab(float width, float height, int health, float xpos, float ypos, float pdropChance,
                        float vx, float vy, Window window) {
    Slab tmpSlab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    slabs.add(tmpSlab);
    renderables.add(tmpSlab);
  }

  /**
   * Spawns a Ball and adds it to any necessary ArrayLists
   */
  public void spawnBall() {
    Ball tmpball = new Ball(win);
    balls.add(tmpball);
    renderables.add(tmpball);
    moveables.add(tmpball);
  }
    /**
     * Spawns a Slab with arguments and adds it to any necessary ArrayLists
     */
  public void spawnSlab(Slab slab) {
    slabs.add(slab);
    renderables.add(slab);
  }

/**
   * Spawns a PowerUp with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnPowerUp(PowerUp powerUp) {
    renderables.add(powerUp);
  }

  /**
   * Spawns a Ball with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnBall(Ball ball) {
    renderables.add(ball);
  }

  /**
   * Spawns a Layout with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnLayout(Layout layout) {
    renderables.add(layout);
  }

  /**
   * Spawns a Button with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnButton(Button button) {
    renderables.add(button);
  }

  /**
   * Spawns a TextBox with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnTextBox(TextBox textbox) {
    renderables.add(textbox);
  }


}

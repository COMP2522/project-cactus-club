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
   * List of all Collidable objects.
   */
  ArrayList<Collidable> collidables;

  /**
   * GameProcess that handles collisions.
   */
  CollisionsHandler ch;

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
    collidables = new ArrayList<Collidable>();
    ch = new CollisionsHandler(collidables);
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

    checkDeadSlabs(slabs);

    ch.update();
  }

  /**
   * Spawns a paddle with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnPaddle() {
    pad = new Paddle(win);
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
  }

  /**
   * Spawns a paddle with arguments and adds it to any necessary ArrayLists
   */
  public void spawnPaddle(Paddle pad) {
    this.pad = pad;
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
  }

  /**
   * Spawns a Slab and adds it to any necessary ArrayLists
   */
  public void spawnSlab(float width, float height, int health, float xpos, float ypos, float pdropChance,
                        float vx, float vy, Window window) {
    Slab tmpSlab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    slabs.add(tmpSlab);
    renderables.add(tmpSlab);
    collidables.add(tmpSlab);
  }

  /**
   * Spawns a Ball and adds it to any necessary ArrayLists
   */
  public void spawnBall() {
    Ball tmpball = new Ball(win);
    balls.add(tmpball);
    renderables.add(tmpball);
    moveables.add(tmpball);
    collidables.add(tmpball);
  }

  /**
   * Spawns a Slab with arguments and adds it to any necessary ArrayLists
   */
  public void spawnSlab(Slab slab) {
    slabs.add(slab);
    renderables.add(slab);
    collidables.add(slab);
  }

  public void spawnWall(Wall wall) {
    renderables.add(wall);
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
    balls.add(ball);
    renderables.add(ball);
    moveables.add(ball);
    collidables.add(ball);
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

  /**
   * Checks for dead Slabs and removes them.
   *
   * @param slabs the list of Slabs the check through
   */
  public void checkDeadSlabs(ArrayList<Slab> slabs) {
    ArrayList<Slab> notDead = new ArrayList<Slab>();
    for (Slab s : slabs) {
      if (s.isDead()) {
        renderables.remove(s);
        collidables.remove(s);
        continue;
      }
      notDead.add(s);
    }
    this.slabs = notDead;
  }

}

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
   * List of all Renderable objects
   */
  ArrayList<Renderable> renderables;

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
    renderables = new ArrayList<Renderable>();
  }

  /**
   * Runs periodically in the processing main loop. Handles timing of all process execution.
   */
  public void update() {
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
  }

    /**
     * Spawns a paddle with arguments and adds it to any necessary ArrayLists
     */
    public void spawnPaddle(float width, float height, float xpos) {
      pad = new Paddle(width, height, xpos,win);
      renderables.add(pad);
    }

  /**
   * Spawns a Slab with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnSlab(float width, float height, int health, float xpos, float ypos, float pdropChance,
                        float vx, float vy, Window window) {
    Slab tmpSlab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    slabs.add(tmpSlab);
    renderables.add(tmpSlab);
  }


}

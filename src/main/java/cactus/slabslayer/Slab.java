package cactus.slabslayer;

/**
 * Represents a slab object.
 */
public class Slab {
  /**
   * Width of the slab, will be set to a default value in the constructor.
   */
  float width;

  /**
   * Height of the slab.
   */
  float height;

  /**
   * Amount of hits for the slab to break (make uncollidable).
   * If hp < 0, slab is considered a "wall" and unbreakable.
   */
  int health;

  /**
   * x-position of slab.
   */
  float xpos;

  /**
   * y-position of slab.
   */
  float ypos;

  /**
   * Chance for slab to drop a power-up upon hp reaching 0.
   */
  float pdropChance;

  /**
   * Used for moving walls.
   */
  float vx;

  /**
   * Used for moving walls.
   */
  float vy;

  Window window;

  /**
   * Constructor for a default slab.
   *
   * @param health hit points of slab
   * @param xpos x-coord of slab
   * @param ypos y-coord of slab
   * @param pdropChance chance to drop power-up upon death
   * @param window window to render to
   */
  public Slab(int health, float xpos, float ypos, float pdropChance, Window window) {
    width = 10;
    height = 5;
    vx = 0;
    vy = 0;
    this.health = health;
    this.xpos = xpos;
    this.ypos = ypos;
    this.pdropChance = pdropChance;
    this.window = window;
  }

  /**
   * Constructor for a slab with specific height/width and moving properties.
   *
   * @param width width of slab
   * @param height height of slab
   * @param health hit points of slab
   * @param xpos x-coord of slab
   * @param ypos y-coord of slab
   * @param pdropChance chance to drop power-up upon death
   * @param vx x-velocity of slab
   * @param vy y-velocity of slab
   * @param window window to render to
   */
  public Slab(float width, float height, int health, float xpos, float ypos, float pdropChance,
              float vx, float vy, Window window) {
    this.width = width;
    this.height = height;
    this.health = health;
    this.xpos = xpos;
    this.ypos = ypos;
    this.pdropChance = pdropChance;
    this.vx = vx;
    this.vy = vy;
    this.window = window;
  }

  /**
   * Renders the slab in the window.
   */
  public void render() {
    window.rect(xpos, ypos, width, height);
  }

  /**
   * Reduces hp of slab by 1.
   */
  public void getHit() {
    health--;
  }

  /**
   * Returns slab's state of life.
   *
   * @return if slab has at least 1 hp.
   */
  public boolean isAlive() {
    return (health > 0) ? true : false;
  }
}

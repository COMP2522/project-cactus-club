package cactus.slabslayer;

import java.util.ArrayList;

/**
 *  SlabCollection is a collection of Slab instances.
 *  SlabCollection will manage the Slab object spawning
 *  and despawning based on health and method calls.
 */
public class SlabCollection {

  /**
   * List of all Slab objects.
   */
  private ArrayList<Slab> slabs;

  /**
   * Constructs a new SlabCollection object.
   */
  public SlabCollection() {
    slabs = new ArrayList<Slab>();
  }

  /**
   * Checks for dead slabs and removes them from necessary ArrayLists.
   */
  public void checkDeadSlabs() {
    Game game = Game.getGameInstance();
    ArrayList<Slab> notDead = new ArrayList<Slab>();
    for (Slab s : slabs) {
      if (s.isDead()) {
        game.incrementScore();
        if (Math.random() <= s.getPdropChance() / 100) {
          //divide s.getPdropChance() by 100 to get a percentage, left alone for testing
          game.spawnPowerUp(
              new PowerUp(0, s.getXpos() + s.getWidth() / 2, s.getYpos() + s.getHeight() / 2,
                  2, 10, game.getWin()));
        }
        game.getRenderables().remove(s);
        game.getCollidables().remove(s);
        game.getJsonables().remove(s);
        continue;
      }
      notDead.add(s);
    }
    this.slabs = notDead;
  }

  /**
   * Spawns a slab with given params, and adds it to necessary ArrayLists.
   *
   * @param width width as a float
   * @param height height as a float
   * @param health health as an int
   * @param xpos xpos as a float
   * @param ypos ypos as a float
   * @param pdropChance pdropChance as a float
   * @param vx vx as a flaot
   * @param vy vy as a float
   * @param window window as a Window
   */
  public void spawnSlab(float width, float height, int health, float xpos,
                        float ypos, float pdropChance, float vx, float vy, Window window) {
    Game game = Game.getGameInstance();
    Slab tmpSlab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    slabs.add(tmpSlab);
    game.getRenderables().add(tmpSlab);
    game.getCollidables().add(tmpSlab);
    game.getJsonables().add(tmpSlab);
  }

  /**
   * Spawns a slab given a Slab object, and adds it to necessary ArrayLists.
   *
   * @param tmpSlab the Slab to spawn
   */
  public void spawnSlab(Slab tmpSlab) {
    Game game = Game.getGameInstance();
    slabs.add(tmpSlab);
    game.getRenderables().add(tmpSlab);
    game.getCollidables().add(tmpSlab);
    game.getJsonables().add(tmpSlab);
  }

  /**
   * Removes the given slab from the necessary ArraLists.
   *
   * @param tmpSlab the Slab to remove
   */
  public void removeSlab(Slab tmpSlab) {
    Game game = Game.getGameInstance();
    slabs.remove(tmpSlab);
    game.getRenderables().remove(tmpSlab);
    game.getCollidables().remove(tmpSlab);
    game.getJsonables().remove(tmpSlab);
  }

  /**
   * Returns the number of living Slabs.
   *
   * @return size of slabs ArrayList as an int
   */
  public int size() {
    return slabs.size();
  }

}

package cactus.slabslayer;

import java.util.ArrayList;

/**
 * CollisionHandler checks for and executes collisions between GameElements in Game
 *
 * @author justin
 * @version 1.0.1
 */
public class CollisionsHandler extends GameProcess {

  /**
   * Local reference to list of all collidables GameElements
   */
  ArrayList<Collidable> collidables;

  /**
   * Constructs a new CollisionHandler object.
   */
  public CollisionsHandler(ArrayList<Collidable> collidables) {
    this.collidables = collidables;
  }


  /**
   * This method is called by periodically called by Game.
   */
  @Override
  public void update() {
    updateCollisions();
  }

  private void updateCollisions() {
    for (int i = 0; i < collidables.size() - 1; i++) {
      Collidable col1 = collidables.get(i);

      for (int j = i + 1; j < collidables.size(); i++) {
        Collidable col2 = collidables.get(j);
        if (col1.isCollidingWith(col2)) {
          col1.doCollision(col2);
          col2.doCollision(col1);
        }
      }
    }
  }
}

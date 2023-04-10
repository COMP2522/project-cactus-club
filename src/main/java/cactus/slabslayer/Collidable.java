package cactus.slabslayer;

/**
 * An interface containing collision methods between objects.
 */
public interface Collidable {
  boolean isCollidingWith(Object toCheck);

  void doCollision(Object collidedWith);

}

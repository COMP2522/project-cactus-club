package cactus.slabslayer;

/**
 * Represent game processes, containing an update method
 * to be called periodically.
 */
public abstract class GameProcess {
  public abstract void update() throws InterruptedException;
}

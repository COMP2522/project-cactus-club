package cactus.slabslayer;

/**
 * Represents an interface for objects that can be rendered.
 */
public interface Renderable extends Comparable {

  boolean isActive = false;

  void render();

}

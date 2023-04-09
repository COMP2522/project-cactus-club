package cactus.slabslayer;

import processing.core.PVector;

/**
 * Represents a game element, containing common methods and attributes between
 * all game elements.
 */
public abstract class GameElement implements Renderable, Jsonable {
  private PVector position;
  private Window window;

  protected int renderPriority = 0;

  /**
   * Compares the render priority between game elements, throwing an exception
   * if the object to be compared is not a GameElement.
   *
   * @param o the object to be compared.
   * @return 0 if the render priorities are equal, 1 if this object has a higher
   *        priority, and -1 if this object has a lower priority.
   */
  public int compareTo(Object o) {
    if (!(o instanceof GameElement ge)) {
      throw new ClassCastException("Attempt to compare GameElement with non-GameElement");
    }

    if (renderPriority == ge.renderPriority) {
      return 0;
    }

    return renderPriority > ge.renderPriority ? 1 : -1;
  }
}

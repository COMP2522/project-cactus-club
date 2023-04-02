package cactus.slabslayer;

import processing.core.PVector;

public abstract class GameElement implements Renderable, JSONable {
  private PVector position;
  private Window window;

  protected int renderPriority = 0;

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

package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

public abstract class GameElement implements Renderable, JSONable {
  private PVector position;
  private Window window;

}

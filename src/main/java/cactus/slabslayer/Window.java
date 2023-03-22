package cactus.slabslayer;

import processing.core.PApplet;

public class Window extends PApplet
{
  Game game;
  public void settings()
  {
    size(500, 500);
  }

  public void setup()
  {
    game = new Game();
    game.init();
  }

  public void draw()
  {
    game.update();
  }

  public static void main(String[] args)
  {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
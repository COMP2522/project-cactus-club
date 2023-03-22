package cactus.slabslayer;

import processing.core.PApplet;

public class Window extends PApplet
{
  Game game;
  InputHandler in;

  public void settings()
  {
    size(500, 500);
  }

  public void setup()
  {
    in = new InputHandler(this);

    game = new Game(in);
    game.init();
  }

  public void draw()
  {
    game.update();
  }

  public void keyPressed()
  {
    in.update(true);
  }

  public void keyReleased()
  {
    in.update(false);
  }

  public static void main(String[] args)
  {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
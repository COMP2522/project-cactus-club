package cactus.slabslayer;

import processing.core.PApplet;

public class Window extends PApplet
{

  Ball b;

  public void settings()
  {
    size(500, 500);
  }

  public void setup()
  {
    b = new Ball(this);
  }

  public void draw()
  {
    background(127, 127, 127);
    b.render();
    b.move();
  }

  public static void main(String[] args)
  {
    String[] processingArgs = {"window"};
    Window window = new Window();
    PApplet.runSketch(processingArgs, window);
  }
}
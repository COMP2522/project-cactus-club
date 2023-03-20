package cactus.slabslayer;

public class Ball
{
  float xPos;
  float yPos;

  float vy;
  float vx;

  Window window;

  public Ball(Window scene)
  {
    xPos = 100;
    yPos = 100;

    vy = 5;
    vx = 5;

    window = scene;
  }

  public void render()
  {
    window.ellipse(xPos, yPos, 30, 30);
  }

  public void move()
  {
    xPos += vx;
    yPos += vy;

    if (xPos < 0 || xPos > window.width)
    {
      vx *= -1;
    }

    if (yPos < 0 || yPos > window.height)
    {
      vy *= -1;
    }
  }
}

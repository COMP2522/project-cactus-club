package cactus.slabslayer;

import processing.data.JSONObject;

public class Ball implements JSONable
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

  public float getXPos() {
    return xPos;
  }

  public float getYPos() {
    return yPos;
  }

  public float getVy() {
    return vy;
  }

  public float getVx() {
    return vx;
  }

  public void setXPos(float xPos) {
    this.xPos = xPos;
  }

  public void setYPos(float yPos) {
    this.yPos = yPos;
  }

  public void setVx(float vx) {
    this.vx = vx;
  }

  public void setVy(float vy) {
    this.vy = vy;
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


  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setFloat("xPos", xPos);
    json.setFloat("yPos", yPos);
    json.setFloat("vy", vy);
    json.setFloat("vx", vx);
    return json.toString();
  }

  @Override
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    float xPos = jsonObject.getFloat("xPos");
    float yPos = jsonObject.getFloat("yPos");
    float vy = jsonObject.getFloat("vy");
    float vx = jsonObject.getFloat("vx");
    Ball ball = new Ball(window);
    ball.setXPos(xPos);
    ball.setYPos(yPos);
    ball.setVy(vy);
    ball.setVx(vx);

    return ball;
  }
}



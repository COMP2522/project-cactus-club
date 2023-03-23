package cactus.slabslayer;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import processing.data.JSONObject;

public class BallJSONTest {
  private Ball ball;

  @BeforeEach
  public void setUp() {
    ball = new Ball(new Window());
    ball.setVx(1);
    ball.setVy(2);
    ball.setXpos(100);
    ball.setYpos(200);
  }

  @Test
  public void testToJSON() {
    String json = ball.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    assertEquals(ball.getXpos(), jsonObject.getJSONObject("constructorVars").getFloat("xPos"), 0.01f);
    assertEquals(ball.getYpos(), jsonObject.getJSONObject("constructorVars").getFloat("yPos"), 0.01f);
    assertEquals(ball.getVx(), jsonObject.getJSONObject("constructorVars").getFloat("vx"), 0.01f);
    assertEquals(ball.getVy(), jsonObject.getJSONObject("constructorVars").getFloat("vy"), 0.01f);

  }

  @Test
  public void testFromJSON() {
    String json = ball.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    Ball newBall = (Ball) ball.fromJSON(json);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("xPos"), newBall.getXpos(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("yPos"), newBall.getYpos(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("vx"), newBall.getVx(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("vy"), newBall.getVy(), 0.01f);
  }
}


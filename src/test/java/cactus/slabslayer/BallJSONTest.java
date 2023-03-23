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
  }

  @Test
  public void testToJSON() {
    String json = ball.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    assertEquals(ball.getXpos(), jsonObject.getFloat("xPos"), 0.01f);
    assertEquals(ball.getYpos(), jsonObject.getFloat("yPos"), 0.01f);
    assertEquals(ball.getVx(), jsonObject.getFloat("vx"), 0.01f);
    assertEquals(ball.getVy(), jsonObject.getFloat("vy"), 0.01f);
  }

  @Test
  public void testFromJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.setFloat("xPos", 200);
    jsonObject.setFloat("yPos", 300);
    jsonObject.setFloat("vx", -3);
    jsonObject.setFloat("vy", 4);

    Ball newBall = (Ball) ball.fromJSON(jsonObject.toString());
    assertEquals(200, newBall.getXpos(), 0.01f);
    assertEquals(300, newBall.getYpos(), 0.01f);
    assertEquals(-3, newBall.getVx(), 0.01f);
    assertEquals(4, newBall.getVy(), 0.01f);
  }
}


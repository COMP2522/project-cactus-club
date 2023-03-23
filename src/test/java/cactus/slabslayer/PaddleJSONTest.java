package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.data.JSONObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaddleJSONTest {
  private Paddle paddle;

  @BeforeEach
  public void setUp() {
    paddle = new Paddle(new Window());
    paddle.width = 80;
    paddle.height = 15;
    paddle.xpos = 150;
  }

  @Test
  public void testToJSON() {
    String json = paddle.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    assertEquals(paddle.width, jsonObject.getJSONObject("constructorVars").getFloat("width"), 0.01f);
    assertEquals(paddle.height, jsonObject.getJSONObject("constructorVars").getFloat("height"), 0.01f);
    assertEquals(paddle.xpos, jsonObject.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
  }

  @Test
  public void testFromJSON() {
    String json = paddle.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    Paddle newPaddle = (Paddle) paddle.fromJSON(json);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("width"), newPaddle.width, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("height"), newPaddle.height, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("xpos"), newPaddle.xpos, 0.01f);
  }
}

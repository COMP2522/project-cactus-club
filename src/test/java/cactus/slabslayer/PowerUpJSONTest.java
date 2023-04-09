package cactus.slabslayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.data.JSONObject;

public class PowerUpJSONTest {
  private PowerUp powerUp;

  @BeforeEach
  public void setUp() {
    powerUp = new PowerUp();
    powerUp.setXpos(100);
    powerUp.setYpos(200);
    powerUp.setDiameter(10);
    powerUp.setYvel(5);
    powerUp.setType(2);
  }

  @Test
  public void testToJSON() {
    String json = powerUp.toJson();
    JSONObject jsonObject = JSONObject.parse(json);
    assertEquals(powerUp.getXpos(), jsonObject.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
    assertEquals(powerUp.getYpos(), jsonObject.getJSONObject("constructorVars").getFloat("ypos"), 0.01f);
    assertEquals(powerUp.getDiameter(), jsonObject.getJSONObject("constructorVars").getFloat("diameter"), 0.01f);
    assertEquals(powerUp.getYvel(), jsonObject.getJSONObject("constructorVars").getFloat("yvel"), 0.01f);
    assertEquals(powerUp.getType(), jsonObject.getJSONObject("constructorVars").getInt("type"));
  }

  @Test
  public void testFromJSON() {
    String json = powerUp.toJson();
    JSONObject jsonObject = JSONObject.parse(json);
    PowerUp newPowerUp = (PowerUp) powerUp.fromJson(json);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("xpos"), newPowerUp.getXpos(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("ypos"), newPowerUp.getYpos(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("diameter"), newPowerUp.getDiameter(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("yvel"), newPowerUp.getYvel(), 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getInt("type"), newPowerUp.getType());
  }
}

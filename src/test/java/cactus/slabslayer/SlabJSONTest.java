package cactus.slabslayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processing.data.JSONObject;

public class SlabJSONTest {
  private Slab slab;

  @BeforeEach
  public void setUp() {
    slab = new Slab(3, 50, 50, 0.5f, new Window());
    slab.width = 20;
    slab.height = 10;
    slab.vx = 1;
  }

  @Test
  public void testToJSON() {
    String json = slab.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    assertEquals(slab.width, jsonObject.getJSONObject("constructorVars").getFloat("width"), 0.01f);
    assertEquals(slab.height, jsonObject.getJSONObject("constructorVars").getFloat("height"), 0.01f);
    assertEquals(slab.health, jsonObject.getJSONObject("constructorVars").getInt("health"));
    assertEquals(slab.xpos, jsonObject.getJSONObject("constructorVars").getFloat("xpos"), 0.01f);
    assertEquals(slab.ypos, jsonObject.getJSONObject("constructorVars").getFloat("ypos"), 0.01f);
    assertEquals(slab.pdropChance, jsonObject.getJSONObject("constructorVars").getFloat("pdropChance"), 0.01f);
    assertEquals(slab.vx, jsonObject.getJSONObject("constructorVars").getFloat("vx"), 0.01f);
    assertEquals(slab.vy, jsonObject.getJSONObject("constructorVars").getFloat("vy"), 0.01f);
  }

  @Test
  public void testFromJSON() {
    String json = slab.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);
    Slab newSlab = (Slab) slab.fromJSON(json);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("width"), newSlab.width, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("height"), newSlab.height, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getInt("health"), newSlab.health);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("xpos"), newSlab.xpos, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("ypos"), newSlab.ypos, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("pdropChance"), newSlab.pdropChance, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("vx"), newSlab.vx, 0.01f);
    assertEquals(jsonObject.getJSONObject("constructorVars").getFloat("vy"), newSlab.vy, 0.01f);
  }
}


package cactus.slabslayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SlabTest {

  @Test
  void testConstructorWithDefaultValues() {
    Window window = new Window();
    Slab slab = new Slab(10, 20, 30, 40, window);

    assertEquals(10, slab.getWidth());
    assertEquals(5, slab.getHeight());
    assertEquals(10, slab.getHealth());
    assertEquals(40, slab.getPdropChance());
    assertEquals(0, slab.getVx());
    assertEquals(0, slab.getVy());
    assertEquals(window, slab.window);
  }

  @Test
  void testConstructorWithSpecificValues() {
    Window window = new Window();
    Slab slab = new Slab(10, 20, 30, 40, 50, 60, 70, 80, window);

    assertEquals(10, slab.getWidth());
    assertEquals(20, slab.getHeight());
    assertEquals(30, slab.getHealth());
    assertEquals(60, slab.getPdropChance());
    assertEquals(70, slab.getVx());
    assertEquals(80, slab.getVy());
    assertEquals(window, slab.window);
  }

  @Test
  void testGetWidth() {
    Slab slab = new Slab();
    slab.setWidth(10);
    assertEquals(10, slab.getWidth());
  }

  @Test
  void testSetWidth() {
    Slab slab = new Slab();
    slab.setWidth(10);
    assertEquals(10, slab.width);
  }

  @Test
  void testGetHeight() {
    Slab slab = new Slab();
    slab.setHeight(5);
    assertEquals(5, slab.getHeight());
  }

  @Test
  void testSetHeight() {
    Slab slab = new Slab();
    slab.setHeight(5);
    assertEquals(5, slab.height);
  }

  @Test
  void testGetHealth() {
    Slab slab = new Slab();
    slab.setHealth(10);
    assertEquals(10, slab.getHealth());
  }

  @Test
  void testSetHealth() {
    Slab slab = new Slab();
    slab.setHealth(10);
    assertEquals(10, slab.health);
  }

  @Test
  void testGetXpos() {
    Slab slab = new Slab();
    slab.setXpos(20);
    assertEquals(20, slab.getXpos());
  }

  @Test
  void testSetXpos() {
    Slab slab = new Slab();
    slab.setXpos(20);
    assertEquals(20, slab.xpos);
  }

  @Test
  void testGetYpos() {
    Slab slab = new Slab();
    slab.setYpos(30);
    assertEquals(30, slab.getYpos());
  }

  @Test
  void testSetYpos() {
    Slab slab = new Slab();
    slab.setYpos(30);
    assertEquals(30, slab.ypos);
  }

  @Test
  void testGetPdropChance() {
    Slab slab = new Slab();
    slab.setPdropChance(0.5f);
    assertEquals(0.5f, slab.getPdropChance());
  }

  @Test
  void testSetPdropChance() {
    Slab slab = new Slab();
    slab.setPdropChance(0.5f);
    assertEquals(0.5f, slab.pdropChance);
  }

  @Test
  void testGetVx() {
    Slab slab = new Slab();
    slab.setVx(0.5f);
    assertEquals(0.5f, slab.getVx());
  }

  @Test
  void testSetVx() {
    Slab slab = new Slab();
    slab.setVx(0.5f);
    assertEquals(0.5f, slab.vx);
  }

  @Test
  void testGetVy() {
    Slab slab = new Slab();
    slab.setVy(0.5f);
    assertEquals(0.5f, slab.getVy());
  }

  @Test
  void testSetVy() {
    Slab slab = new Slab();
    slab.setVy(0.5f);
    assertEquals(0.5f, slab.vy);
  }

  @Test
  void testTakeDamage() {
    Window window = new Window();
    Slab slab = new Slab(30, 20, 30, 40, window);

    slab.takeDamage(10);
    assertEquals(20, slab.getHealth());

    slab.takeDamage(20);
    assertEquals(0, slab.getHealth());

    slab.takeDamage(10);
    assertEquals(-10, slab.getHealth());
  }

  @Test
  void testIsDead() {
    Window window = new Window();
    Slab slab = new Slab(40, 20, 30, 40, window);

    assertFalse(slab.isDead());

    slab.takeDamage(30);
    assertFalse(slab.isDead());

    slab.takeDamage(10);
    assertTrue(slab.isDead());
  }
}

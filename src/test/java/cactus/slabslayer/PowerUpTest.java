package cactus.slabslayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PowerUpTest {

  PowerUp powerUp;
  Random rand;

  @BeforeEach
  void setUp() {
    powerUp = new PowerUp();
    rand = new Random();
  }

  @Test
  void testGetSetXPos() {
    powerUp.setXpos(10);
    assertEquals(10, powerUp.getXpos());
  }

  @Test
  void testGetSetYPos() {
    powerUp.setYpos(10);
    assertEquals(10, powerUp.getYpos());
  }

  @Test
  void testGetSetDiameter() {
    powerUp.setDiameter(10);
    assertEquals(10, powerUp.getDiameter());
  }

  @Test
  void testGetSetType() {
    int type = rand.nextInt(4);
    powerUp.setType(type);
    assertEquals(type, powerUp.getType());
  }

  @Test
  void testGetSetYVel() {
    powerUp.setYvel(10);
    assertEquals(10, powerUp.getYvel());
  }

  @Test
  void testDefaultConstructor() {
    int type = rand.nextInt(4);
    powerUp.setType(type);
    assertEquals(type, powerUp.getType());
    assertEquals(10, powerUp.getDiameter());
    assertEquals(0, powerUp.getXpos());
    assertEquals(0, powerUp.getYpos());
    assertEquals(2, powerUp.getYvel());
  }
}

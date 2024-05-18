package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallTest {

  Ball ball;
  Window window;
  @BeforeEach
  void setUp() {
    window = new Window();
    ball = new Ball(window);
  }

  @Test
  void testGetSetXPos() {
    ball.setXpos(10);
    assertEquals(10, ball.getXpos());
  }

  @Test
  void testGetSetYPos() {
    ball.setYpos(10);
    assertEquals(10, ball.getYpos());
  }

  @Test
  void testGetSetXVel() {
    ball.setVx(10);
    assertEquals(10, ball.getVx());
  }

  @Test
  void testGetSetYVel() {
    ball.setVy(10);
    assertEquals(10, ball.getVy());
  }

  @Test
  void testDefaultConstructor() {
    assertEquals(window.width / 2.0, ball.getXpos());
    assertEquals(window.height / 2.0, ball.getYpos());
    assertEquals(0f, ball.getVx());
    assertEquals(5.0f, ball.getVy());
    assertEquals(window, ball.window);
  }
}

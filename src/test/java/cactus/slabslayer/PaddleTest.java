package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaddleTest {

  Window window;
  Paddle paddle;

  @BeforeEach
  void setUp() {
    window = new Window();
    paddle = new Paddle(window);
  }

  @Test
  void testGetSetWidth() {
    paddle.setWidth(10);
    assertEquals(10, paddle.getWidth());
  }

  @Test
  void testGetSetHeight() {
    paddle.setHeight(10);
    assertEquals(10, paddle.getHeight());
  }

  @Test
  void testGetSetXpos() {
    paddle.setXpos(10);
    assertEquals(10, paddle.getXpos());
  }

  @Test
  void testGetSetXVel() {
    paddle.setXvel(10);
    assertEquals(10, paddle.getXvel());
  }

  @Test
  void testConstructorWithWindowParams() {
    assertEquals(100, paddle.getWidth());
    assertEquals(20, paddle.getHeight());
    assertEquals(250 - (paddle.getWidth() / 2), paddle.getXpos());
    assertEquals(window, paddle.window);
  }

  @Test
  void testConstructorWithAllParams() {
    Paddle paddle = new Paddle(10, 20, 30, window);
    assertEquals(10, paddle.getWidth());
    assertEquals(20, paddle.getHeight());
    assertEquals(30, paddle.getXpos());
    assertEquals(window, paddle.window);
  }
}

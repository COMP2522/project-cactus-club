package cactus.slabslayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

public class TextBoxTest {
  PVector pos;
  Window window;
  TextBox textBox;

  @BeforeEach
  void setUp() {
    pos = new PVector(10, 10);
    window = new Window();
  }

  @Test
  void testGetSetTextWithDefaultConstructor() {
    textBox = new TextBox(window);
    textBox.setText("Hello World!");
    assertEquals("Hello World!", textBox.getText());
  }

  @Test
  void testGetSetTextWithIntsConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    assertEquals("Hello World!", textBox.getText());
  }

  @Test
  void testGetSetTextWithPVectorConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    assertEquals("Hello World!", textBox.getText());
  }

  @Test
  void testGetSetSizeWithDefaultConstructor() {
    textBox = new TextBox(window);
    textBox.setSize(10);
    assertEquals(10, textBox.getSize());
  }

  @Test
  void testGetSetSizeWithIntsConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    textBox.setSize(10);
    assertEquals(10, textBox.getSize());
  }

  @Test
  void testGetSetSizeWithPVectorConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    textBox.setSize(10);
    assertEquals(10, textBox.getSize());
  }

  @Test
  void testGetLocalPosWithDefaultConstructor() {
    textBox = new TextBox(window);
    assertEquals(0, textBox.getLocalPos().x);
    assertEquals(0, textBox.getLocalPos().y);
  }

  @Test
  void testGetLocalPosWithIntsConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    assertEquals(10, textBox.getLocalPos().x);
    assertEquals(10, textBox.getLocalPos().y);
  }

  @Test
  void testGetLocalPosWithPVectorConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    assertEquals(10, textBox.getLocalPos().x);
    assertEquals(10, textBox.getLocalPos().y);
  }

  @Test
  void testSetLocalXPosWithDefaultConstructor() {
    textBox = new TextBox(window);
    textBox.setLocalXPos(10);
    assertEquals(10, textBox.getLocalPos().x);
  }

  @Test
  void testSetLocalXPosWithIntsConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    textBox.setLocalXPos(10);
    assertEquals(10, textBox.getLocalPos().x);
  }

  @Test
  void testSetLocalXPosWithPVectorConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    textBox.setLocalXPos(10);
    assertEquals(10, textBox.getLocalPos().x);
  }

  @Test
  void testSetLocalYPosWithDefaultConstructor() {
    textBox = new TextBox(window);
    textBox.setLocalYPos(10);
    assertEquals(10, textBox.getLocalPos().y);
  }

  @Test
  void testSetLocalYPosWithIntsConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    textBox.setLocalYPos(10);
    assertEquals(10, textBox.getLocalPos().y);
  }

  @Test
  void testSetLocalYPosWithPVectorConstructor() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    textBox.setLocalYPos(10);
    assertEquals(10, textBox.getLocalPos().y);
  }

  @Test
  void testDefaultConstructor() {
    textBox = new TextBox(window);
    assertEquals("Text goes here!", textBox.getText());
    assertEquals(0, textBox.getLocalPos().x);
    assertEquals(0, textBox.getLocalPos().y);
    assertEquals(50, textBox.getSize());
  }

  @Test
  void testConstructorWithInts() {
    String text = "Hello World!";
    textBox = new TextBox(text, 10, 10, 10, window);
    assertEquals("Hello World!", textBox.getText());
    assertEquals(10, textBox.getLocalPos().x);
    assertEquals(10, textBox.getLocalPos().y);
    assertEquals(10, textBox.getSize());
  }

  @Test
  void testConstructorWithPVector() {
    String text = "Hello World!";
    textBox = new TextBox(text, pos, 10, window);
    assertEquals("Hello World!", textBox.getText());
    assertEquals(10, textBox.getLocalPos().x);
    assertEquals(10, textBox.getLocalPos().y);
    assertEquals(10, textBox.getSize());
  }
}

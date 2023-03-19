package cactus.slabslayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {

  @Test
  void helloWorldTest() {

    HelloWorld hw = new HelloWorld();
    assertEquals(0, hw.returnZero());

  }

}
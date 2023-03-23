package cactus.slabslayer;

public class Button extends GameElement {
  /**
   * Renders the button in the window.
   */
  @Override
  public void render() {
    // to do
    System.out.println("My Button");
  }
  @Override
  public String toJSON() {
    return "{\"type\": \"button\"}";
  }

  @Override
  public Object fromJSON(String json) {
    return null;
  }
}

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
    return "{\"type\": \"Button\"}";
  }

  @Override
  public Object fromJSON(String json) {
    Button button = new Button();
    return button;
  }
}

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

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJSON() {
    return "{\"type\": \"Button\"}";
  }

  /**
   * Converts a JSON string to a button object.
   *
   * @param json JSON string
   * @return button
   */
  @Override
  public Object fromJSON(String json) {
    Button button = new Button();
    return button;
  }
}

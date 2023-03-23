package cactus.slabslayer;

public class TextBox extends GameElement {

  /**
   * Renders the layout in the window.
   */
  @Override
  public void render() {
    // to do
    System.out.println("My Text Box");
  }

  @Override
  public String toJSON() {
    return "{\"type\": \"textbox\"}";
  }

  @Override
  public Object fromJSON(String json) {
    return null;
  }
}

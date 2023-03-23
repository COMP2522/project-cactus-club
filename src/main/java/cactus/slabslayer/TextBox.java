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
    return "{\"type\": \"TextBox\"}";
  }

  @Override
  public Object fromJSON(String json) {
    TextBox textBox = new TextBox();
    return textBox;
  }
}

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

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJSON() {
    return "{\"type\": \"TextBox\"}";
  }

  /**
   * Converts a JSON string to a text box object.
   *
   * @param json JSON string
   * @return text box
   */
  @Override
  public Object fromJSON(String json) {
    TextBox textBox = new TextBox();
    return textBox;
  }
}

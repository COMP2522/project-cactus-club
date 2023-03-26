package cactus.slabslayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class LayoutJSONTest {
  private Layout layout;
  private Button button;
  private TextBox textBox;

  @BeforeEach
  public void setUp() {
    layout = new Layout(new Window());
    button = new Button();
    textBox = new TextBox(new Window());

    layout.addLayoutElement(button);
    layout.addLayoutElement(textBox);
  }

  @Test
  public void testToJSON() {
    String json = layout.toJSON();
    JSONObject jsonObject = JSONObject.parse(json);

    assertEquals(layout.getClass().getSimpleName(), jsonObject.getString("type"));

    JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
    JSONArray layoutElementsArr = constructorVars.getJSONArray("layoutElements");
    assertEquals(2, layoutElementsArr.size());

    JSONObject buttonJson = layoutElementsArr.getJSONObject(0);
    JSONObject textBoxJson = layoutElementsArr.getJSONObject(1);

    assertEquals(button.getClass().getSimpleName(), buttonJson.getString("type"));
    assertEquals(textBox.getClass().getSimpleName(), textBoxJson.getString("type"));
  }

  @Test
  public void testFromJSON() {
    String json = layout.toJSON();
    Layout newLayout = (Layout) layout.fromJSON(json);

    assertEquals(layout.getClass().getSimpleName(), newLayout.getClass().getSimpleName());

    assertEquals(layout.getWindow().getClass().getSimpleName(), newLayout.getWindow().getClass().getSimpleName());

    assertEquals(layout.getLayoutElements().size(), newLayout.getLayoutElements().size());

    Button newButton = (Button) newLayout.getLayoutElements().get(0);
    TextBox newTextBox = (TextBox) newLayout.getLayoutElements().get(1);

    assertEquals(button.getClass().getSimpleName(), newButton.getClass().getSimpleName());
    assertEquals(textBox.getClass().getSimpleName(), newTextBox.getClass().getSimpleName());
  }
}

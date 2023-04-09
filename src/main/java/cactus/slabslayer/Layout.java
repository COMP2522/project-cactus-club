package cactus.slabslayer;

import java.util.ArrayList;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
 * Contains a list of UI game elements to be rendered in the game window.
 */
public class Layout extends GameElement implements Jsonable {

  /**
   * The list of all elements contained in the layout.
   */
  private ArrayList<GameElement> layoutElements;

  /**
   * The game's window.
   */
  private Window window;

  /**
   * Constructs a new Layout.
   *
   * @param window the game window
   */
  public Layout(Window window) {
    layoutElements = new ArrayList<>();
    this.window = window;
  }

  public ArrayList<GameElement> getLayoutElements() {
    return layoutElements;
  }

  public Window getWindow() {
    return window;
  }

  /**
   * Adds new layout element to layoutElements ArrayList.
   *
   * @param toAdd the new GameElement to add to the ArrayList
   */
  public void addLayoutElement(GameElement toAdd) {
    if (!(toAdd instanceof Layout) && !(toAdd instanceof Button) && !(toAdd instanceof TextBox)) {
      throw new IllegalArgumentException(
          "Layout element must be of class Layout, Button, or TextBox!");
    }

    layoutElements.add(toAdd);

  }

  /**
   * Removes a layout element from the layoutElements ArrayList.
   *
   * @param toRemove the GameElement to remove from the ArrayList
   */
  public void removeLayoutElement(GameElement toRemove) {
    if (!layoutElements.contains(toRemove)) {
      throw new NullPointerException("No such element is in this layout.");
    }

    layoutElements.remove(toRemove);

  }

  /**
   * Renders the layout in the window.
   */
  @Override
  public void render() {
    for (GameElement elem : layoutElements) {
      elem.render();
    }
  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJson() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    JSONArray layoutElementsArr = new JSONArray();
    for (GameElement elem : layoutElements) {
      layoutElementsArr.append(JSONObject.parse(elem.toJson()));
    }
    constructorVars.setJSONArray("layoutElements", layoutElementsArr);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a layout object.
   *
   * @param json JSON string
   * @return layout
   */
  @Override
  public Object fromJson(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Layout".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      JSONArray layoutElementsArr = constructorVars.getJSONArray("layoutElements");
      Layout layout = new Layout(window);
      for (int i = 0; i < layoutElementsArr.size(); i++) {
        JSONObject elemJson = layoutElementsArr.getJSONObject(i);
        String typeInner = elemJson.getString("type");
        switch (typeInner) {
          case "Button":
            Button button = new Button(new Window());
            layout.addLayoutElement((Button) (button.fromJson(elemJson.toString())));
            break;
          case "TextBox":
            TextBox textBox = new TextBox(new Window());
            layout.addLayoutElement((TextBox) (textBox.fromJson(elemJson.toString())));
            break;
          default:
            throw new IllegalArgumentException("Unknown type: " + typeInner);
        }
      }
      return layout;
    }

    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

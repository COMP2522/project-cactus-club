package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

public abstract class GameElement implements Renderable, JSONable {
  private PVector position;
  private Window window;
  public GameElement fromJSON(JSONObject json) {
    // Extract the "type" field from the JSON object
    String type = json.getString("type");

    // Use a switch statement to create the appropriate game element object
    switch (type) {
      case "Paddle":
        return (GameElement)new Paddle(new Window()).fromJSON(json.toString());
      case "PowerUp":
        return (GameElement)new PowerUp().fromJSON(json.toString());
      case "Ball":
        return (GameElement)new Ball(new Window()).fromJSON(json.toString());
      case "Slab":
        return (GameElement)new Slab().fromJSON(json.toString());
      case "Layout":
        return (GameElement)new Layout(new Window()).fromJSON(json.toString());
      case "Button":
        return (GameElement)new Button(new Window()).fromJSON(json.toString());
      case "Textbox":
        return (GameElement)new TextBox(new Window()).fromJSON(json.toString());
      default:
        throw new IllegalArgumentException("Unknown game element type: " + type);
    }
  }


}

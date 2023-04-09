package cactus.slabslayer;

import processing.data.JSONObject;

/**
 * Represents a slab object.
 */
public class Wall extends GameElement {
  /**
   * Width of the slab, will be set to a default value in the constructor.
   */
  float width;

  /**
   * Height of the slab.
   */
  float height;

  /**
   * x-position of slab.
   */
  float xpos;

  /**
   * y-position of slab.
   */
  float ypos;

  /**
   * Used for moving walls.
   */
  float vx;

  /**
   * Used for moving walls.
   */
  float vy;

  /**
   * Window to render to.
   */
  Window window;

  /**
   * Constructor for a default slab.
   *
   * @param xpos        x-coord of slab
   * @param ypos        y-coord of slab
   * @param window      window to render to
   */
  public Wall(float xpos, float ypos, Window window) {
    width = 10;
    height = 5;
    vx = 0;
    vy = 0;
    this.xpos = xpos;
    this.ypos = ypos;
    this.window = window;
  }

  /**
   * Constructor for a slab with specific height/width and moving properties.
   *
   * @param width       width of slab
   * @param height      height of slab
   * @param health      hit points of slab
   * @param xpos        x-coord of slab
   * @param ypos        y-coord of slab
   * @param pdropChance chance to drop power-up upon death
   * @param vx          x-velocity of slab
   * @param vy          y-velocity of slab
   * @param window      window to render to
   */
  public Wall(float width, float height, float xpos, float ypos, float vx, float vy, Window window) {
    this.width = width;
    this.height = height;
    this.xpos = xpos;
    this.ypos = ypos;
    this.vx = vx;
    this.vy = vy;
    this.window = window;
  }

  public Wall() {

  }

  /**
   * Renders the slab in the window.
   */
  public void render() {
    window.stroke(0);
    window.strokeWeight(4);
    window.fill(255, 200, 200);
    window.rect(xpos, ypos, width, height);
  }

  /**
   * Converts this object to a JSON string.
   *
   * @return JSON string
   */
  @Override
  public String toJSON() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setFloat("width", width);
    constructorVars.setFloat("height", height);
    constructorVars.setFloat("xpos", xpos);
    constructorVars.setFloat("ypos", ypos);
    constructorVars.setFloat("vx", vx);
    constructorVars.setFloat("vy", vy);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a Slab object.
   *
   * @param json JSON string
   * @return Slab object
   */
  @Override
  public Wall fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Wall".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      float width = constructorVars.getFloat("width");
      float height = constructorVars.getFloat("height");
      float xpos = constructorVars.getFloat("xpos");
      float ypos = constructorVars.getFloat("ypos");
      float vx = constructorVars.getFloat("vx");
      float vy = constructorVars.getFloat("vy");
      Wall wall = new Wall(width, height, xpos, ypos, vx, vy, window);
      return wall;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  public static void main(String[] args) {
    Window window = new Window();
    Wall wall = new Wall(10, 10, 10, 10, 10, 10, window);

    // test toJSON() method
    String json = wall.toJSON();
    System.out.println(json);

    // test fromJSON() method
    Wall newSlab = (Wall) wall.fromJSON(json);
    System.out.println(newSlab.toJSON());
  }
}


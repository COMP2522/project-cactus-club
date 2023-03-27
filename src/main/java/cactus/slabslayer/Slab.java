package cactus.slabslayer;

import processing.data.JSONObject;

/**
 * Represents a slab object.
 */
public class Slab extends GameElement {
  /**
   * Width of the slab, will be set to a default value in the constructor.
   */
  float width;

  /**
   * Height of the slab.
   */
  float height;

  /**
   * Amount of hits for the slab to break (make uncollidable).
   * If hp < 0, slab is considered a "wall" and unbreakable.
   */
  int health;

  /**
   * x-position of slab.
   */
  float xpos;

  /**
   * y-position of slab.
   */
  float ypos;

  /**
   * Chance for slab to drop a power-up upon hp reaching 0.
   */
  float pdropChance;

  /**
   * Used for moving walls.
   */
  float vx;

  /**
   * Used for moving walls.
   */
  float vy;

  Window window;

  /**
   * Constructor for a default slab.
   *
   * @param health      hit points of slab
   * @param xpos        x-coord of slab
   * @param ypos        y-coord of slab
   * @param pdropChance chance to drop power-up upon death
   * @param window      window to render to
   */
  public Slab(int health, float xpos, float ypos, float pdropChance, Window window) {
    width = 10;
    height = 5;
    vx = 0;
    vy = 0;
    this.health = health;
    this.xpos = xpos;
    this.ypos = ypos;
    this.pdropChance = pdropChance;
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
  public Slab(float width, float height, int health, float xpos, float ypos, float pdropChance,
              float vx, float vy, Window window) {
    this.width = width;
    this.height = height;
    this.health = health;
    this.xpos = xpos;
    this.ypos = ypos;
    this.pdropChance = pdropChance;
    this.vx = vx;
    this.vy = vy;
    this.window = window;
  }

  /**
   * Constructor for a slab with default values.
   * To be used with JSON tests.
   */
  public Slab() {
    this.width = 0;
    this.height = 0;
    this.health = 0;
    this.xpos = 0;
    this.ypos = 0;
    this.pdropChance = 0;
    this.vx = 0;
    this.vy = 0;
    this.window = null;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getWidth() {
    return width;
  }

  /**
   * Updates the slab's width.
   *
   * @param width width of the slab
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * Gets the slab's height.
   *
   * @return slab's height
   */
  public float getHeight() {
    return height;
  }

  /**
   * Updates the slab's height.
   *
   * @param height height of the slab
   */
  public void setHeight(float height) {
    this.height = height;
  }

  /**
   * Gets the slab's health.
   *
   * @return slab's health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Updates the slab's health.
   *
   * @param health health of the slab
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Gets the slab's x-position.
   *
   * @return x-position of the slab
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Updates the slab's x-position.
   *
   * @param xpos x-position of the slab
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Gets the slab's y-position.
   *
   * @return y-position of the slab
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Updates the slab's y-position.
   *
   * @param ypos y-position of the slab
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Gets the slab's chance to drop a power-up.
   *
   * @return power-up drop chance
   */
  public float getPdropChance() {
    return pdropChance;
  }

  /**
   * Updates the slab's chance to drop a power-up.
   *
   * @param pdropChance power-up drop chance
   */
  public void setPdropChance(float pdropChance) {
    this.pdropChance = pdropChance;
  }

  /**
   * Gets the slab's x-velocity.
   *
   * @return x-velocity of the slab
   */
  public float getVx() {
    return vx;
  }

  /**
   * Updates the slab's x-velocity.
   *
   * @param vx x-velocity of the slab
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * Gets the slab's y-velocity.
   *
   * @return y-velocity of the slab
   */
  public float getVy() {
    return vy;
  }

  /**
   * Updates the slab's y-velocity.
   *
   * @param vy y-velocity of the slab
   */
  public void setVy(float vy) {
    this.vy = vy;
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
   * Reduces hp of slab by 1.
   */
  public void takeDamage(int damage) {
    health -= damage;
  }

  /**
   * Returns slab's state of life.
   *
   * @return if slab has at least 1 hp.
   */
  public boolean isDead() {
    return health > 0;
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
    constructorVars.setInt("health", health);
    constructorVars.setFloat("xpos", xpos);
    constructorVars.setFloat("ypos", ypos);
    constructorVars.setFloat("pdropChance", pdropChance);
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
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Slab".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      float width = constructorVars.getFloat("width");
      float height = constructorVars.getFloat("height");
      int health = constructorVars.getInt("health");
      float xpos = constructorVars.getFloat("xpos");
      float ypos = constructorVars.getFloat("ypos");
      float pdropChance = constructorVars.getFloat("pdropChance");
      float vx = constructorVars.getFloat("vx");
      float vy = constructorVars.getFloat("vy");
      return new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  /**
   * Quick JSON tests for Slab class.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Window window = new Window();
    Slab slab = new Slab(3, 50, 50, 0.5f, window);

    String jsonSlab = "{\n" +
            "  \"type\": \"Slab\",\n" +
            "  \"constructorVars\": {\n" +
            "    \"vx\": 1,\n" +
            "    \"ypos\": 1,\n" +
            "    \"pdropChance\": 1,\n" +
            "    \"vy\": 0,\n" +
            "    \"xpos\": 1,\n" +
            "    \"width\": 11,\n" +
            "    \"health\": 31,\n" +
            "    \"height\": 51\n" +
            "  }\n" +
            "}";
    // test toJSON() method
    String json = slab.toJSON();

    System.out.println(json);
    System.out.println(jsonSlab);

    // test fromJSON() method
    // Slab newSlab = (Slab) slab.fromJSON(json);
    Slab newSlab2 = (Slab) slab.fromJSON(jsonSlab);
    System.out.println(newSlab2.toJSON());
  }
}


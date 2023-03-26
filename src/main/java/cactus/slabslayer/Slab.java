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

  public Slab() {
    //bro what is this.
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
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setWidth(float width) {
    this.width = width;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getHeight() {
    return height;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setHeight(float height) {
    this.height = height;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public int getHealth() {
    return health;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getPdropChance() {
    return pdropChance;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setPdropChance(float pdropChance) {
    this.pdropChance = pdropChance;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getVx() {
    return vx;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
   */
  public float getVy() {
    return vy;
  }

  /**
   * Updates the slab's position.
   *
   * @return if the slab is still alive
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
    return (health > 0) ? false : true;
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
      Slab slab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
      return slab;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  public static void main(String[] args) {
    Window window = new Window();
    Slab slab = new Slab(3, 50, 50, 0.5f, window);

    // test toJSON() method
    String json = slab.toJSON();
    System.out.println(json);

    // test fromJSON() method
    Slab newSlab = (Slab) slab.fromJSON(json);
    System.out.println(newSlab.toJSON());
  }
}


package cactus.slabslayer;

import java.util.Random;

import processing.data.JSONObject;

public class PowerUp extends GameElement implements Moveable, Collidable, JSONable {
//  float radius;

  /**
   * Type of power up.
   */
  int type;

  /**
   * x-position of power up.
   */
  float xpos;

  /**
   * y-position of power up.
   */
  float ypos;

  /**
   * y-velocity of power up.
   */
  float yvel;

  /**
   * Diameter of power up.
   */
  float diameter;

  /**
   * Set the type of power up.
   *
   * @param type type
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Set the y-velocity of the power up.
   *
   * @param yvel y-velocity
   */
  public void setYvel(float yvel) {
    this.yvel = yvel;
  }

  Random randomType = new Random();
  Window window;

  /**
   * Constructs a power up object with a random type.
   */
  public PowerUp() {
    this.type = randomType.nextInt(4);
    this.xpos = 0;
    this.ypos = 0;
    this.yvel = 5;
    this.diameter = 10;
  }

  /**
   * Gets the x-position of the power up.
   *
   * @return x-position
   */
  public float getXpos() {
    return xpos;
  }

  /**
   * Gets the y-position of the power up.
   *
   * @return y-position
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Gets the y-velocity of the power up.
   *
   * @return y-velocity
   */
  public float getYvel() {
    return yvel;
  }

  /**
   * Get the type of power up.
   *
   * @return type
   */
  public int getType() {
    return type;
  }

  /**
   * Set x-position of power up.
   *
   * @param xpos x-position
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Set y-position of power up.
   *
   * @param ypos y-position
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Set the window of the power up.
   *
   * @param window window
   */
  public void setWindow(Window window) {
    this.window = window;
  }

  /**
   * Set the diameter of the power up.
   *
   * @param diameter diameter
   */
  public void setDiameter(float diameter) {
    this.diameter = diameter;
  }

  /**
   * Controls power up movement.
   */
  @Override
  public void move(InputHandler in) {

  }

  /**
   * Renders the power-up in the window.
   */
  public void render() {
    window.stroke(0);
    window.strokeWeight(4);
    window.fill(100, 100, 255);
    window.ellipse(xpos, ypos, diameter, diameter);
  }

  /**
   * Checks if colliding with another object.
   *
   * @param toCheck the Object to check
   * @return true/false if colliding
   */
  @Override
  public boolean isCollidingWith(Object toCheck) {
    // to do
    return false;
  }

  /**
   * Executes collision with another object.
   *
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {
    // to do
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
//    constructorVars.setFloat("radius", radius);
    constructorVars.setInt("type", type);
    constructorVars.setFloat("xpos", xpos);
    constructorVars.setFloat("ypos", ypos);
    constructorVars.setFloat("yvel", yvel);
    constructorVars.setFloat("diameter", diameter);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Converts a JSON string to a powerup object.
   *
   * @param json JSON string
   * @return powerup object
   */
  @Override
  public Object fromJSON(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("PowerUp".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
//      float radius = constructorVars.getFloat("radius");
      int typeInt = constructorVars.getInt("type");
      float xpos = constructorVars.getFloat("xpos");
      float ypos = constructorVars.getFloat("ypos");
      float yvel = constructorVars.getFloat("yvel");
      float diameter = constructorVars.getFloat("diameter");

      PowerUp powerUp = new PowerUp();
//      powerUp.setRadius(radius);
      powerUp.setType(typeInt);
      powerUp.setXpos(xpos);
      powerUp.setYpos(ypos);
      powerUp.setYvel(yvel);
      powerUp.setDiameter(diameter);

      return powerUp;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }

  public static void main(String[] args) {
    // create a new power up
    PowerUp powerUp = new PowerUp();
    powerUp.setXpos(100);
    powerUp.setYpos(100);

    // serialize the power up to JSON
    String json = powerUp.toJSON();
    System.out.println(json);

    // deserialize the JSON string to a new power up object
    PowerUp newPowerUp = (PowerUp) powerUp.fromJSON(json);
    System.out.println(newPowerUp.toJSON());
  }

}


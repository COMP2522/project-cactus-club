package cactus.slabslayer;

public class PowerUp extends GameElement implements Moveable, Collidable {

  /**
   * Controls power up movement.
   */
  @Override
  public void move() {

  }

  /**
   * Renders the power up in the window.
   */
  @Override
  public void render() {

  }

  /**
   * Checks if colliding with another object.
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
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {
    // to do
  }

  @Override
  public String toJSON() {
    return null;
  }

  @Override
  public Object fromJSON(String json) {
    return null;
  }
}

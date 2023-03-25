package cactus.slabslayer;

/**
 * Interface for classes that can be converted to and from JSON.
 *
 * @author Nicolas
 * @version 2023
 */
public interface JSONable {
  public String toJSON();

  public Object fromJSON(String json);

}

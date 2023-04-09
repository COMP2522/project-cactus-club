package cactus.slabslayer;

/**
 * Interface for classes that can be converted to and from JSON.
 *
 * @author Nicolas
 * @version 2023
 */
public interface Jsonable {
  String toJson();

  Object fromJson(String json);
}

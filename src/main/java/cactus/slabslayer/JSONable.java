package cactus.slabslayer;

public interface JSONable {
  public String toJSON();

  public Object fromJSON(String json);

}

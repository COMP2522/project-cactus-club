package cactus.slabslayer;

public interface Collidable {

    boolean isCollidingWith(Object toCheck);

    void doCollision(Object collidedWith);

}

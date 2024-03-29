package cactus.slabslayer;

import processing.core.PVector;
import processing.data.JSONObject;

/**
 * Represents a ball object.
 */
public class Ball extends GameElement implements Moveable, Collidable {
  /**
   * x-position of ball.
   */
  float xpos;

  /**
   * y-position of ball.
   */
  float ypos;

  /**
   * y-velocity of ball.
   */
  float vy;

  /**
   * x-velocity of ball.
   */
  float vx;

  /**
   * Diameter of ball.
   */
  float diameter;

  /**
   * The resolution at which to check collisions.
   */
  int checkResolution = 100;

  /**
   * How funneled the refletion angle is between ball and paddle.
   * Bigger is wider max angle.
   */
  int funnelFactor = 10;

  /**
   * Activates and deactivates bebug logs.
   */
  boolean isDebugging = false;

  /**
   * Window object to render to.
   */
  Window window;

  /**
   * Time since last collision as a long.
   */
  long timePrevBounce;

  /**
   * Time to wait between bounces as a long.
   */
  public static final long BOUNCE_INTERVAL = 50;


  /**
   * Constructs a new Ball object with initial position and velocity.
   *
   * @param scene the Window object in which the ball will be rendered and moved
   */
  public Ball(Window scene) {
    xpos = scene.width / 2;
    ypos = scene.height / 2;

    vy = 7;
    vx = 2;

    diameter = 30;

    window = scene;

    timePrevBounce = System.currentTimeMillis();

    this.doCollision(new Slab());
  }

  /**
   * Getter for x-coordinate of ball.
   *
   * @return x-coordinate
   */
  public float getXpos() {
    return xpos;

  }

  /**
   * Getter for y-coordinate of ball.
   *
   * @return y-coordinate
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * Getter for x-velocity of ball.
   *
   * @return x-velocity
   */
  public float getVx() {
    return vx;
  }

  /**
   * Getter for y-velocity of ball.
   *
   * @return y-velocity
   */
  public float getVy() {
    return vy;
  }

  /**
   * Getter for diameter of ball.
   *
   * @return diameter
   */
  public float getDiameter() {
    return diameter;
  }

  /**
   * Setter for x-coordinate of ball.
   *
   * @param xpos x-coordinate
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * Setter for y-coordinate of ball.
   *
   * @param ypos y-coordinate
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * Setter for x-velocity of ball.
   *
   * @param vx x-velocity
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * Setter for y-velocity of ball.
   *
   * @param vy y-velocity
   */
  public void setVy(float vy) {
    this.vy = vy;
  }

  /**
   * Renders the ball in the window.
   */
  public void render() {
    window.stroke(0);
    window.strokeWeight(4);
    window.fill(100, 100, 255);
    window.ellipse(xpos, ypos, diameter, diameter);
  }

  /**
   * Moves the ball, reverses velocity if boundary is hit.
   */
  public void move(InputHandler in) {
    xpos += vx;
    ypos += vy;

    edgeDetect();
  }

  /**
   * Executes different code depending on which edge the ball hits.
   */
  private void edgeDetect() {
    // top edge
    if (ypos < 0) {
      vy *= -1;
    }

    // left and right edges
    if (xpos < 0 || xpos > window.width) {
      vx *= -1;
    }
  }

  /**
   * Checks if colliding with another object.
   *
   * @param toCheck the Object to check
   * @return true/false if colliding
   */
  @Override
  public boolean isCollidingWith(Object toCheck) {
    if (toCheck.getClass() == Paddle.class) {
      Paddle p = (Paddle) toCheck;

      // splits the paddle into checkResolution number of points
      // and checks if the ball is contacting any of those points
      for (int i = 0; i <= p.getWidth(); i += Math.max(p.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(p.getXpos() + i, window.height / 100 * 90);
        if (!(PVector.dist(segPos, new PVector(this.xpos, this.ypos)) < diameter / 2)) {
          continue;
        }
        return true;
      }
    }

    if (toCheck.getClass() == Slab.class) {
      if (System.currentTimeMillis() - this.timePrevBounce < BOUNCE_INTERVAL) {
        return false;
      }
      Slab s = (Slab) toCheck;

      // bottom edge check
      for (int i = 0; i <= s.getWidth(); i += Math.max(s.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + i, s.getYpos() + s.getHeight());
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        this.timePrevBounce = System.currentTimeMillis();
        return true;
      }

      // top edge check
      for (int i = 0; i <= s.getWidth(); i += Math.max(s.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + i, s.getYpos());
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        this.timePrevBounce = System.currentTimeMillis();
        return true;
      }

      // left edge check
      for (int i = 0; i <= s.getHeight(); i += Math.max(s.getHeight() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos(), s.getYpos() + i);
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        this.timePrevBounce = System.currentTimeMillis();
        return true;
      }

      // right edge check
      for (int i = 0; i <= s.getHeight(); i += Math.max(s.getHeight() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + s.getWidth(), s.getYpos() + i);
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        this.timePrevBounce = System.currentTimeMillis();
        return true;
      }

    }
    return false;
  }

  /**
   * Executes collision with another object.
   *
   * @param collidedWith the Object to collide with
   */
  @Override
  public void doCollision(Object collidedWith) {

    // reflects the ball at the appropriate angle
    if (collidedWith.getClass() == Paddle.class) {
      Paddle p = (Paddle) collidedWith;
      for (int i = 0; i <= p.getWidth(); i += Math.max(p.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(p.getXpos() + i, p.getYpos());
        if (!(PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        PVector reflectionAngle = new PVector(-1, 0);
        reflectionAngle.setMag(new PVector(vx, vy).mag());
        float theta = window.map(i, 0, p.getWidth(), window.PI / funnelFactor,
            window.PI - window.PI / funnelFactor);
        reflectionAngle.rotate(theta);

        setVx(reflectionAngle.x);
        setVy(reflectionAngle.y);

        // log: debug: renders performed reflection angle
        if (isDebugging) {
          window.stroke(0, 255, 0);
          window.strokeWeight(8);
          PVector lineVector = new PVector(reflectionAngle.x, reflectionAngle.y);
          lineVector.setMag(200);
          window.line(segPos.x, segPos.y, segPos.x + lineVector.x, segPos.y + lineVector.y);
        }
      }
    }

    if (collidedWith.getClass() == Slab.class) {
      Slab s = (Slab) collidedWith;

      boolean reflecty = false;
      boolean reflectx = false;

      // bottom edge check
      for (int i = 0; i <= s.getWidth(); i += Math.max(s.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + i, s.getYpos() + s.getHeight());
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        reflecty = true;
        System.out.println("bottom collided with: " + collidedWith + "at i: " + i);
        break;
      }

      // top edge check
      for (int i = 0; i <= s.getWidth(); i += Math.max(s.getWidth() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + i, s.getYpos());
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        reflecty = true;
        System.out.println("top collided with " + collidedWith + "at i: " + i);
        break;
      }

      // left edge check
      for (int i = 0; i <= s.getHeight(); i += Math.max(s.getHeight() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos(), s.getYpos() + i);
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        reflectx = true;
        System.out.println("left collided with " + collidedWith + "at i: " + i);
        break;
      }

      // right edge check
      for (int i = 0; i <= s.getHeight(); i += Math.max(s.getHeight() / checkResolution, 1)) {
        PVector segPos = new PVector(s.getXpos() + s.getWidth(), s.getYpos() + i);
        if (! (PVector.dist(segPos, new PVector(xpos, ypos)) < diameter / 2)) {
          continue;
        }
        reflectx = true;
        System.out.println("right collided with " + collidedWith + "at i: " + i);
        break;
      }

      if (reflecty) {
        vy *= -1;
        return;
      }
      vx *= reflectx ? -1 : 1;
    }

  }

  /**
   * Gets the time the last bounce occurred.
   *
   * @return last bounce as a long
   */
  public long getTimePrevBounce() {
    return timePrevBounce;
  }

  @Override
  public String toJson() {
    JSONObject json = new JSONObject();
    json.setString("type", getClass().getSimpleName());
    JSONObject constructorVars = new JSONObject();
    constructorVars.setFloat("vx", vx);
    constructorVars.setFloat("xPos", xpos);
    constructorVars.setFloat("vy", vy);
    constructorVars.setFloat("yPos", ypos);
    json.setJSONObject("constructorVars", constructorVars);
    return json.toString();
  }

  /**
   * Creates a new Ball object from a JSON string.
   *
   * @param json JSON string
   * @return new Ball object
   */
  @Override
  public Ball fromJson(String json) {
    JSONObject jsonObject = JSONObject.parse(json);
    String type = jsonObject.getString("type");

    if ("Ball".equals(type)) {
      JSONObject constructorVars = jsonObject.getJSONObject("constructorVars");
      float xpos = constructorVars.getFloat("xPos");
      float ypos = constructorVars.getFloat("yPos");
      float vy = constructorVars.getFloat("vy");
      float vx = constructorVars.getFloat("vx");
      Ball ball = new Ball(window);
      ball.setXpos(xpos);
      ball.setYpos(ypos);
      ball.setVy(vy);
      ball.setVx(vx);
      return ball;
    }
    // handle other types here

    throw new IllegalArgumentException("Unknown type: " + type);
  }
}

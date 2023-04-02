package cactus.slabslayer;

import processing.core.PVector;

import java.util.ArrayList;

/**
 * Game will have collections of game elements, and game processing that affect game elements.
 *
 * @author justin
 * @version 1.0.1
 */
public class Game {

  /**
   * The game instance.
   */
  private static Game gameInstance;

  /**
   * Local reference to main window.
   */
  Window win;

  /**
   * Local reference to main program InputHandler.
   */
  InputHandler in;

  /**
   * Player's score.
   */
  int score;

  /**
   * Paddle instance.
   */
  Paddle pad;

  /**
   * Score instance.
   */
  ScoreBox scoreBox;

  /**
   * List of all Slab objects
   */
  ArrayList<Slab> slabs;

  /**
   * List of all Ball objects.
   */
  ArrayList<Ball> balls;

  /**
   * List of all Renderable objects
   */
  ArrayList<Renderable> renderables;

  /**
   * List of all Moveable objects.
   */
  ArrayList<Moveable> moveables;

  /**
   * List of all Collidable objects.
   */
  ArrayList<Collidable> collidables;

  /**
   * List of all JSONable objects.
   */
  ArrayList<JSONable> jsonables;

  /**
   * GameProcess that handles collisions.
   */
  CollisionsHandler ch;

  /**
   * GameProcess that handles saving and loading saves, including levels
   */
  GameSaveHandler gsh;

  /**
   * Constructs a new Game object.
   */
  private Game() {
    score = 0;
    this.init();
  }

  /**
   * Constructs a new Game object
   * with a window and input handler.
   * @param w as a Window object
   * @param in as an InputHandler object
   */
  private Game(Window w, InputHandler in) {
    score = 0;
    win = w;
    this.in = in;
    this.init();
  }

  /**
   * Gets the game instance.
   * @return gameInstance as a Game object
   */
  public static Game getGameInstance() {

    if (gameInstance == null) {
      gameInstance = new Game();
    }

    return gameInstance;

  }

  /**
   * Gets the game instance.
   * @return gameInstance as a Game object
   */
  public static Game getGameInstance(Window w, InputHandler in) {

    if (gameInstance == null) {
      gameInstance = new Game(w, in);
    }

    return gameInstance;

  }

  /**
   * Gets the window.
   * @return win as a Window object
   */
  public Window getWin() {
    return win;
  }

  /**
   * Sets the window.
   * @param win as a Window object
   */
  public void setWin(Window win) {
    this.win = win;
  }

  /**
   * Gets the input handler.
   * @return in as an InputHandler object
   */
  public InputHandler getIn() {
    return in;
  }

  /**
   * Sets the input handler.
   * @param in as an InputHandler object
   */
  public void setIn(InputHandler in) {
    this.in = in;
  }

  /**
   * Gets the list of JSONables.
   *
   * @return jsonables as ArrayList
   */
  public ArrayList<JSONable> getJsonables() {
    return jsonables;
  }

  /**
   * Gets the player's score.
   * @return score as an int
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the player's score.
   * @param score as an int
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Increments the player's score by 1.`
   */
  public void incrementScore() {
    score++;
    this.scoreBox.setText("Score: " + score);
  }

  /**
   * Get the text box that displays the score.
   * @return scoreBox as a TextBox
   */
  public TextBox getScoreBox() {
    return scoreBox;
  }

  /**
   * Initializes Game object to initial state.
   * clears all the collections and re-initializes all game processes
   */
  public void init() {
    pad = null;
    slabs = new ArrayList<Slab>();
    balls = new ArrayList<Ball>();
    renderables = new ArrayList<Renderable>();
    moveables = new ArrayList<Moveable>();
    collidables = new ArrayList<Collidable>();
    jsonables = new ArrayList<JSONable>();
    ch = new CollisionsHandler(collidables);
    gsh = new GameSaveHandler(this, "game-save.json", System.currentTimeMillis());
  }

  public ArrayList<Renderable> getRenderables() {
    return renderables;
  }

  /**
   * Runs periodically in the processing main loop. Handles timing of all process execution.
   */
  public void update() {
    win.background(200, 200, 255);

    for (Moveable m : moveables) {
      m.move(in);
    }

    renderables.sort((o1, o2) -> o1.compareTo(o2));

    for (Renderable r : renderables) {
      r.render();
    }

    checkDeadSlabs(slabs);

    ch.update();
    gsh.update();

    if (slabs.size() == 0) {
      this.loadLevel(1);
    }
  }

  /**
   * Spawns a paddle with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnPaddle() {
    pad = new Paddle(win);
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
    jsonables.add(pad);
  }

  /**
   * Spawns a paddle with arguments and adds it to any necessary ArrayLists
   */
  public void spawnPaddle(Paddle pad) {
    this.pad = pad;
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
    jsonables.add(pad);
  }

  /**
   * Spawns a Slab and adds it to any necessary ArrayLists
   */
  public void spawnSlab(float width, float height, int health, float xpos, float ypos, float pdropChance,
                        float vx, float vy, Window window) {
    Slab tmpSlab = new Slab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
    slabs.add(tmpSlab);
    renderables.add(tmpSlab);
    collidables.add(tmpSlab);
    jsonables.add(tmpSlab);
  }

  /**
   * Spawns a Ball and adds it to any necessary ArrayLists
   */
  public void spawnBall() {
    Ball tmpball = new Ball(win);
    balls.add(tmpball);
    renderables.add(tmpball);
    moveables.add(tmpball);
    collidables.add(tmpball);
    jsonables.add(tmpball);
  }

  /**
   * Spawns a Slab with arguments and adds it to any necessary ArrayLists
   */
  public void spawnSlab(Slab slab) {
    slabs.add(slab);
    renderables.add(slab);
    collidables.add(slab);
    jsonables.add(slab);
  }

  public void spawnWall(Wall wall) {
    renderables.add(wall);
  }

  /**
   * Spawns a PowerUp with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnPowerUp(PowerUp powerUp) {
    renderables.add(powerUp);
  }

  /**
   * Spawns a Ball with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnBall(Ball ball) {
    balls.add(ball);
    renderables.add(ball);
    moveables.add(ball);
    collidables.add(ball);
    jsonables.add(ball);
  }

  /**
   * Spawns a Layout with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnLayout(Layout layout) {
    renderables.add(layout);
    jsonables.add(layout);
  }

  /**
   * Spawns a Button with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnButton(Button button) {
    renderables.add(button);
    jsonables.add(button);
  }

  /**
   * Spawns a TextBox with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnTextBox(TextBox textbox) {
    renderables.add(textbox);
    jsonables.add(textbox);
  }

  /**
   * Spawns a ScoreBox and adds it to any necessary ArrayLists
   */
  public void spawnScoreBox(ScoreBox sb) {
    this.scoreBox = sb;
    renderables.add(sb);
    jsonables.add(sb);
  }

  /**
   * Spawns a ScoreBox with no arguments and adds it to any necessary ArrayLists
   */
  public void spawnScoreBox() {
    ScoreBox sb = new ScoreBox("Score: " + score, new PVector(15, 50), 50, win);
    this.scoreBox = sb;
    renderables.add(sb);
    jsonables.add(sb);
  }

  /**
   * Checks for dead Slabs and removes them.
   *fthe
   * @param slabs the list of Slabs the check through
   */
  public void checkDeadSlabs(ArrayList<Slab> slabs) {
    ArrayList<Slab> notDead = new ArrayList<Slab>();
    for (Slab s : slabs) {
      if (s.isDead()) {
        incrementScore();
        renderables.remove(s);
        collidables.remove(s);
        continue;
      }
      notDead.add(s);
    }
    this.slabs = notDead;
  }


  /**
   * Loads the first level. TODO: make more generic with multiple levels
   */
  public void loadLevel(int levelIndex) {
    levelIndex = 1;
    this.init();
    gsh.loadGame("levels/temp.level1.json", win, in, this);
  }
}

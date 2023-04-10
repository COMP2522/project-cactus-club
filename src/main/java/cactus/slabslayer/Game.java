package cactus.slabslayer;

import java.io.File;
import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import processing.core.PVector;
import processing.data.JSONArray;

import static processing.core.PApplet.loadJSONArray;

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
   * Possible states that the player's game can be in.
   */
  static enum State {
    START,
    PLAYING,
    GAMEOVER
  }

  /**
   * Current state of the game.
   */
  State currState;

  /**
   * Current level.
   */
  int currLevel;

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
   * List of all Slab objects.
   */
  SlabCollection slabs;

  /**
   * List of all Ball objects.
   */
  ArrayList<Ball> balls;

  /**
   * List of all PowerUp objects.
   */
  ArrayList<PowerUp> powerUps;

  /**
   * A list of all buttons that currently exist.
   * Used for input handler to check if mouse is clicked
   * in each button's bounds.
   */
  ArrayList<Button> buttons;

  /**
   * List of all Renderable objects.
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
  ArrayList<Jsonable> jsonables;

  /**
   * GameProcess that handles collisions.
   */
  CollisionsHandler ch;

  /**
   * GameProcess that handles saving and loading saves, including levels.
   */
  GameSaveHandler gsh;

  /**
   * MongoDB database.
   */
  MongoDatabase db;

  /**
   * Constructs a new Game object.
   */
  private Game() {
    currState = State.START;
  }

  /**
   * Constructs a new Game object
   * with a window and input handler.
   *
   * @param w as a Window object
   * @param in as an InputHandler object
   */
  private Game(Window w, InputHandler in) {
    win = w;
    this.in = in;
    currState = State.START;
  }

  /**
   * Gets the game instance.
   *
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
   *
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
   *
   * @return win as a Window object
   */
  public Window getWin() {
    return win;
  }

  /**
   * Sets the window.
   *
   * @param win as a Window object
   */
  public void setWin(Window win) {
    this.win = win;
  }

  /**
   * Gets the input handler.
   *
   * @return in as an InputHandler object
   */
  public InputHandler getIn() {
    return in;
  }

  /**
   * Sets the input handler.
   *
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
  public ArrayList<Jsonable> getJsonables() {
    return jsonables;
  }

  /**
   * Gets the list of Collidables.
   *
   * @return collidables as ArrayList
   */
  public ArrayList<Collidable> getCollidables() {
    return collidables;
  }

  /**
   * Gets the current state.
   *
   * @return currState as a State enum
   */
  public State getCurrState() {
    return currState;
  }

  /**
   * Set the current state.
   *
   * @param currState as a State enum
   */
  public void setCurrState(State currState) {
    this.currState = currState;
  }

  /**
   * Get the current level.
   *
   * @return currLevel as an int
   */
  public int getCurrLevel() {
    return currLevel;
  }

  /**
   * Sets the current level.
   *
   * @param currLevel as an int
   */
  public void setCurrLevel(int currLevel) {
    this.currLevel = currLevel;
  }

  /**
   * Gets the player's score.
   *
   * @return score as an int
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the player's score.
   *
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
   *
   * @return scoreBox as a TextBox
   */
  public TextBox getScoreBox() {
    return scoreBox;
  }

  /**
   * Initializes Game object to initial state.
   * clears all the collections and re-initializes all game processes
   */
  public void init() throws InterruptedException {
    pad = null;
    slabs = new SlabCollection();
    balls = new ArrayList<Ball>();
    powerUps = new ArrayList<PowerUp>();
    buttons = new ArrayList<Button>();
    renderables = new ArrayList<Renderable>();
    moveables = new ArrayList<Moveable>();
    collidables = new ArrayList<Collidable>();
    jsonables = new ArrayList<Jsonable>();
    ch = new CollisionsHandler(collidables);
    gsh = new GameSaveHandler(this, "game-save.json", System.currentTimeMillis());
    db = DatabaseHandler.getInstance().getDatabase();
  }

  public ArrayList<Renderable> getRenderables() {
    return renderables;
  }

  /**
   * Runs periodically in the processing main loop. Handles timing of all process execution.
   */
  public void update() throws InterruptedException {
    win.background(200, 200, 255);

    for (Moveable m : moveables) {
      m.move(in);
    }

    renderables.sort((o1, o2) -> o1.compareTo(o2));

    for (Renderable r : renderables) {
      r.render();
    }

    slabs.checkDeadSlabs();
    checkPowerUpCollisions(powerUps);
    checkDeadBalls(balls);

    ch.update();
    gsh.update();

    if (currState == State.START) {
      loadStartScreen();
    }

    if (currState == State.PLAYING && slabs.size() == 0) {
      loadNextLevel();
    }

    if (currState == State.PLAYING && balls.size() == 0) {
      currState = State.GAMEOVER;
    }

    if (currState == State.GAMEOVER) {
      loadGameOverScreen();
    }

  }

  /**
   * Spawns a paddle with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnPaddle() {
    pad = new Paddle(win);
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
    jsonables.add(pad);
  }

  /**
   * Spawns a paddle with arguments and adds it to any necessary ArrayLists.
   */
  public void spawnPaddle(Paddle pad) {
    this.pad = pad;
    renderables.add(pad);
    moveables.add(pad);
    collidables.add(pad);
    jsonables.add(pad);
  }

  /**
   * Spawns a Slab.
   */
  public void spawnSlab(float width, float height, int health, float xpos,
                        float ypos, float pdropChance, float vx, float vy, Window window) {

    slabs.spawnSlab(width, height, health, xpos, ypos, pdropChance, vx, vy, window);
  }

  /**
   * Spawns a Slab with arguments.
   */
  public void spawnSlab(Slab slab) {
    slabs.spawnSlab(slab);
  }

  /**
   * Spawns a Ball and adds it to any necessary ArrayLists.
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
   * Spawns a Ball with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnBall(Ball ball) {
    balls.add(ball);
    renderables.add(ball);
    moveables.add(ball);
    collidables.add(ball);
    jsonables.add(ball);
  }

  public void spawnWall(Wall wall) {
    renderables.add(wall);
  }

  /**
   * Spawns a PowerUp with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnPowerUp(PowerUp powerUp) {
    powerUps.add(powerUp);
    renderables.add(powerUp);
    moveables.add(powerUp);
    collidables.add(powerUp);
    jsonables.add(powerUp);
  }

  /**
   * Spawns a Layout with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnLayout(Layout layout) {
    renderables.add(layout);
    jsonables.add(layout);
  }

  /**
   * Spawns a Button with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnButton(Button button) {
    buttons.add(button);
    renderables.add(button);
    jsonables.add(button);
  }

  /**
   * Spawns a TextBox with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnTextBox(TextBox textbox) {
    renderables.add(textbox);
    jsonables.add(textbox);
  }

  /**
   * Spawns a ScoreBox and adds it to any necessary ArrayLists.
   */
  public void spawnScoreBox(ScoreBox sb) {
    this.scoreBox = sb;
    renderables.add(sb);
    jsonables.add(sb);
  }

  /**
   * Spawns a ScoreBox with no arguments and adds it to any necessary ArrayLists.
   */
  public void spawnScoreBox() {
    ScoreBox sb = new ScoreBox("Score: " + score, new PVector(15, 50), 50, 255, win);
    this.scoreBox = sb;
    renderables.add(sb);
    jsonables.add(sb);
  }

  /**
   * Checks for dead Slabs and removes them.
   */
  public void checkDeadSlabs() {
    slabs.checkDeadSlabs();
  }

  /**
   * Checks for collisions between the paddle and powerups.
   *
   * @param powerUps all powerups to check
   */
  public void checkPowerUpCollisions(ArrayList<PowerUp> powerUps) {
    ArrayList<PowerUp> notDead = new ArrayList<PowerUp>();
    for (PowerUp p : powerUps) {
//      if (p.isCollidingWith(pad)) {
//        collidables.remove(p);
//        renderables.remove(p);
//        moveables.remove(p);
//        jsonables.remove(p);
//        spawnBall();
//      }
      if (p.getHealth() < 1) {
        incrementScore();
        collidables.remove(p);
        renderables.remove(p);
        moveables.remove(p);
        jsonables.remove(p);
        spawnBall();
        continue;
      }
      notDead.add(p);
    }
    this.powerUps = notDead;
  }

  /**
   * Checks if all balls have gone past the bottom of the window,
   * and removes them if they have.
   *
   * @param balls all balls to check
   */
  public void checkDeadBalls(ArrayList<Ball> balls) {
    ArrayList<Ball> notDead = new ArrayList<Ball>();
    for (Ball b : balls) {
      if (b.getYpos() > win.height) {
        collidables.remove(b);
        renderables.remove(b);
        moveables.remove(b);
        jsonables.remove(b);
        continue;
      }
      notDead.add(b);
    }
    this.balls = notDead;
  }

  /**
   * Loads the given level.
   *
   * @param levelIndex as an int
   */
  public void loadLevel(int levelIndex) throws InterruptedException {
    currLevel = levelIndex;
    this.init();
    gsh.loadGame(String.format("levels/level%d.json", currLevel), win, in, this);
  }

  /**
   * Loads the next level in the sequence.
   */
  public void loadNextLevel() throws InterruptedException {
    currLevel++;
    this.init();
    gsh.loadGame(String.format("levels/level%d.json", currLevel), win, in, this);
  }

  /**
   * Loads the start screen.
   */
  public void loadStartScreen() throws InterruptedException {

    this.init();
//    gsh.loadGame("levels/startscreen.json", win, in, this);
    Layout startScreen = new Layout(win);
    spawnLayout(startScreen);
    TextBox title = new TextBox("SLAB SLAYER", new PVector(185, 250), 80, 40, win);
    startScreen.addLayoutElement(title);
    PlayButton pb = new PlayButton("Start", 150, 50, new PVector(325, 270), win);
    startScreen.addLayoutElement(pb);
    spawnButton(pb);
//    save("levels/startscreen.json");

  }


  /**
   * Loads the game over screen.
   */
  public void loadGameOverScreen() throws InterruptedException {

    this.init();
//    gsh.loadGame("levels/startscreen.json", win, in, this);
    Layout endScreen = new Layout(win);
    spawnLayout(endScreen);

    TextBox title = new TextBox("GAME OVER", new PVector(205, 250), 80, 0, win);
    endScreen.addLayoutElement(title);

    TextBox finalScore = new TextBox("Your score: " + score, new PVector(300, 300), 40, 0, win);
    endScreen.addLayoutElement(finalScore);

    PlayButton pb = new PlayButton("Replay", 150, 50, new PVector(325, 320), win);
    endScreen.addLayoutElement(pb);
    spawnButton(pb);

//    save("levels/gameoverscreen.json");
  }


  /**
   * Tells GameSaveHandler to save the current game state to a certain directory.
   *
   * @param dir directory to store the .json save file
   */
  public void save(String dir) {
    gsh.saveGame(jsonables, dir);
  }
}

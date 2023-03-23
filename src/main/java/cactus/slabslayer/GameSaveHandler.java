package cactus.slabslayer;


import java.util.ArrayList;

import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PApplet;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static processing.core.PApplet.loadJSONArray;

public class GameSaveHandler extends GameProcess {

  @Override
  public void update() {
    // TODO Auto-generated method stub
  }

  public void saveGame(ArrayList<JSONable> elements, String dir) {
    // TODO Auto-generated method stub
    JSONArray jsonElements = new JSONArray();
    for (JSONable element : elements) {
      jsonElements.append(JSONObject.parse(element.toJSON()));
    }
    saveJSONArray(jsonElements, dir);
  }

  private void saveJSONArray(JSONArray jsonElements, String dir) {
    // TODO Auto-generated method stub
    File file = new File(dir);
    try {
      FileWriter writer = new FileWriter(file);
      writer.write(jsonElements.toString());
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadGame(String dir) {
//    // TODO Auto-generated method stub
//    JSONArray jsonElements = loadJSONArray(new File(dir));
//    for (int i = 0; i < jsonElements.size(); i++) {
//      JSONObject jsonElement = jsonElements.getJSONObject(i);
//      GameElement element = (GameElement)GameElement.fromJSON(jsonElement);
//      GameElement.add(element);
//    }
  }
  public static void main(String[] args) {
    // Create some game elements
    Ball ball = new Ball(new Window());
    Ball ball1 = new Ball(new Window());
    Ball ball2 = new Ball(new Window());

    // Create an ArrayList to store the game elements
    ArrayList<JSONable> gameElements = new ArrayList<>();
    gameElements.add(ball);
    gameElements.add(ball1);
    gameElements.add(ball2);

    // Save the game elements to a file
    GameSaveHandler saveHandler = new GameSaveHandler();
    saveHandler.saveGame(gameElements, "game-save.json");

    // Print a success message
    System.out.println("Game elements saved successfully.");
  }


}






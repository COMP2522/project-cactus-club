package cactus.slabslayer;


import java.util.ArrayList;

import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.core.PApplet;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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



  }
}


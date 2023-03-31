package cactus.slabslayer;

import javax.sound.sampled.*;

/**
 * A class that handles playing sounds.
 *
 * @author Trevor
 */
public class SoundManager {
  private Clip clip;

  public SoundManager(String filePath) {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(filePath));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void play() {
    if (clip != null) {
      clip.setFramePosition(0);
      clip.start();
    }
  }

  public void stop() {
    if (clip != null) {
      clip.stop();
    }
  }
}

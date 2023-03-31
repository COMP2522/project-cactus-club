package cactus.slabslayer;

import javax.sound.sampled.*;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that handles playing sounds.
 *
 * @author Trevor
 */
public class SoundManager {
  /**
   * The background music.
   */
  private final Clip bgm;

  /**
   * The sound that plays when the ball something.
   */
  private final Clip ball;

  /**
   * The sound that plays when the ball bounces off something.
   */
  private final Clip bounce;

  /**
   * The sound that plays when the paddle hits a powerup.
   */
  private final Clip powerup;


  public SoundManager() throws FileNotFoundException, LineUnavailableException {
    Path bgmPath = Paths.get("assets", "audio", "bgm.wav");
    bgm = loadAudio(bgmPath);

    Path ballPath = Paths.get("assets", "audio", "ball.wav");
    ball = loadAudio(ballPath);

    Path bouncePath = Paths.get("assets", "audio", "bounce.m4a");
    bounce = loadAudio(bouncePath);

    Path powerupPath = Paths.get("assets", "audio", "powerup.m4a");
    powerup = loadAudio(powerupPath);
  }

  Clip loadAudio(Path path) throws FileNotFoundException, LineUnavailableException {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path.toFile());
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      return clip;
    } catch (Exception e) {
      throw new FileNotFoundException("Could not find audio file at " + path.toString());
    }
  }

  public void playBGM() {
    bgm.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void playBallSound() {
    if (ball != null) {
      ball.setFramePosition(0);
      ball.start();
    }
  }

  public void playBounceSound() {
    if (bounce != null) {
      bounce.setFramePosition(0);
      bounce.start();
    }
  }

  public void playPowerupSound() {
    if (powerup != null) {
      powerup.setFramePosition(0);
      powerup.start();
    }
  }
}

package cactus.slabslayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/**
 * A class that handles playing sounds.
 *
 * @author Trevor
 */
public class SoundManager {
  /**
   * The singleton instance of the sound manager.
   */
  private static SoundManager instance;

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


  /**
   * Constructs a new sound manager.
   *
   * @throws FileNotFoundException if the audio file is not found
   * @throws LineUnavailableException if the audio line is unavailable
   */
  private SoundManager() throws FileNotFoundException, LineUnavailableException {
    Path bgmPath = Paths.get("assets", "audio", "bgm.wav");
    bgm = loadAudio(bgmPath);

    Path ballPath = Paths.get("assets", "audio", "ball.wav");
    ball = loadAudio(ballPath);

    Path bouncePath = Paths.get("assets", "audio", "bounce.wav");
    bounce = loadAudio(bouncePath);

    Path powerupPath = Paths.get("assets", "audio", "powerup.wav");
    powerup = loadAudio(powerupPath);
  }

  /**
   * Gets the singleton instance of the sound manager.
   *
   * @return the singleton instance of the sound manager
   * @throws FileNotFoundException if the audio file is not found
   * @throws LineUnavailableException if the audio line is unavailable
   */
  public static SoundManager getInstance() throws FileNotFoundException, LineUnavailableException {
    if (instance == null) {
      instance = new SoundManager();
    }
    return instance;
  }

  /**
   * Loads an audio file.
   *
   * @param path the path to the audio file
   * @return the audio clip
   * @throws FileNotFoundException if the audio file is not found
   * @throws LineUnavailableException if the audio line is unavailable
   */
  Clip loadAudio(Path path) throws FileNotFoundException, LineUnavailableException {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
          new File(path.toFile().toURI()));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      return clip;
    } catch (Exception e) {
      throw new FileNotFoundException("Could not find audio file at " + path.toString());
    }
  }

  /**
   * Plays the background music.
   */
  public void playBgm() {
    bgm.loop(Clip.LOOP_CONTINUOUSLY);
  }

  /**
   * Stops the background music.
   */
  public void stopBgm() {
    bgm.stop();
  }

  /**
   * Plays the ball sound.
   */
  public void playBallSound() {
    if (ball != null) {
      ball.setFramePosition(0);
      ball.start();
    }
  }

  /**
   * Plays the bounce sound.
   */
  public void playBounceSound() {
    if (bounce != null) {
      bounce.setFramePosition(0);
      bounce.start();
    }
  }

  /**
   * Plays the powerup sound.
   */
  public void playPowerupSound() {
    if (powerup != null) {
      powerup.setFramePosition(0);
      powerup.start();
    }
  }

  /**
   * Checks if the background music is playing.
   *
   * @return boolean
   */
  public boolean isBgmPlaying() {
    return bgm.isRunning();
  }
}

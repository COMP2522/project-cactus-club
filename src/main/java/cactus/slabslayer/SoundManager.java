package cactus.slabslayer;

/**
 * SoundManager
 * Creates and contains methods to play sounds.
 *
 * @author Trevor
 */
public class SoundManager {
  private static SoundManager instance = null;

  private SoundManager() {
    // Exists only to defeat instantiation.
  }

  /**
   * getInstance
   * Returns the instance of the SoundManager.
   *
   * @return SoundManager
   */
  public static SoundManager getInstance() {
    if (instance == null) {
      instance = new SoundManager();
    }
    return instance;
  }

    /**
     * playSound
     * Plays a sound.
     *
     * @param sound The name of the sound to play.
     */
  public void playSound(String sound) {

  }
}
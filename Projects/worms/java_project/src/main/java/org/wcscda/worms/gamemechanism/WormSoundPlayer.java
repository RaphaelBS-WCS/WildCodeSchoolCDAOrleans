package org.wcscda.worms.gamemechanism;

/*import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;*/

/* NRO 2021-09-30 : Test class to play sound
 *  don't use for the moment as it requires some thread
 *  management, which hasn't been covered yet
 */
public class WormSoundPlayer {

  private static WormSoundPlayer instance;

  public WormSoundPlayer() {}

  /*public void playSound() throws FileNotFoundException, JavaLayerException {
    FileInputStream fis =
        new FileInputStream("src/resources/Nanatsu no Taizai AMV - Human Race.mp3");
    Player playMP3 = new Player(fis);

    playMP3.play();
  }

  public void playWav()
      throws LineUnavailableException, IOException, UnsupportedAudioFileException,
          InterruptedException {
    AudioInputStream audioInputStream =
        AudioSystem.getAudioInputStream(new File("src/resources/Explosion1.wav").getAbsoluteFile());
    Clip clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.start();
    // Thread.sleep(clip.getMicrosecondLength() / 1000 + 1);
    // clip.close();
  }*/

  public static WormSoundPlayer getWormSoundPlayer() {
    if (instance == null) {
      instance = new WormSoundPlayer();
    }

    return instance;
  }

  /*public void playSound2() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
  	AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("src/resources/Nanatsu no Taizai AMV - Human Race.mp3"));
  	Clip clip = AudioSystem.getClip();
  	clip.open(audioIn);
  	clip.start();

  }*/
}

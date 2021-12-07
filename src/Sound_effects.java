import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * This class keeps track of our sounds clips. It can play sounds for
 * background music, planehit sound, and the misslefired sound.
 */
public class Sound_effects {
    private Clip clip;

    /**
     * This is our sound device, when created it can be used to
     * play several different sounds.
     */
    public Sound_effects() {
    }

    /**
     * This will play and load the background music that is used throughout the
     * game.
     */
    public void backGround() {
        try {
            String filepath = ("src/Plane-assets/background.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Reduce volume by 10 decibels.
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * This method will play the sound for a missled being fired.
     */
    public void missileFired() {
        try {
            String filepath = ("src/Plane-assets/ROCKET2.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * This method loads and play the noise for when a plane get hit.
     */
    public void planeHitsound() {
        try {
            String filepath = ("src/Plane-assets/EXPLOSION.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

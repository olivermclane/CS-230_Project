import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound_effects {

    private Clip clip;


    public Sound_effects() {

    }

    public void backGround() {


        try {

            String filepath = ("src/Plane-assets/background.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }


    }

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

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound_effects {

	private Clip clip;
	/**
	 * @param string
	 */
	
	public Sound_effects() {
		
	}
	public void backGround() {
		
			
			//takes an input for file path and attempts to open that file to play music.
			//set to play continuously
			try
			{
				
				String filepath = ("src/Plane-assets/STORM.wav");
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
				clip = AudioSystem.getClip();	
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}
			
		
	}
	public static void main(String[] args) {
	
	}

}

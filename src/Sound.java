import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	void playSound(String soundFile) {
		try {
			//Make the file 
			File soundPath = new File(soundFile); 
			//if the path does exist
			if(soundPath.exists()) {
				//Open audioInput and get sound
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
				//Get clip to be able to use it
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput); 
				clip.start();
;				
			} else {
				System.out.println("Cant find file");
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

/**
 * Admin Number: 2429634
 * Class: DIT/FT/2A/01
 * @author Junkai
 */

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {

    private Clip clip;
    private AudioInputStream audioInputStream;

    final private String filePath;

    public SoundPlayer(String filePath) {
        this.filePath = filePath;
    }

    public void playSound() {
        try {
            audioInputStream = 
                AudioSystem.getAudioInputStream(new File("src/" + filePath).getAbsoluteFile());
        
            // create clip reference
            clip = AudioSystem.getClip();
            
            // open audioInputStream to the clip
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Sound Error: Incorrect file type!");
        }
    }
}

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class test {

    public static void main(String[] args) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("res/sound/gamebackground.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
//            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        } catch (Exception e){
            e.printStackTrace();
        }


    }


}

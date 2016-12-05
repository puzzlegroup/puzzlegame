/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author author
 */
public class JavaSound {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        File file = new File("src/javasound/resources/2-1_communicator.wav");
        Clip clip = null;
        AudioInputStream ais = null;

        try {
            clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            ais = AudioSystem.getAudioInputStream(file);
            clip.open(ais);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(JavaSound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaSound.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(JavaSound.class.getName()).log(Level.SEVERE, null, ex);
        }

        //clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.loop(0);
        
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                // A GUI element to prevent the Clip's daemon Thread 
                // from terminating at the end of the main()
                JOptionPane.showMessageDialog(null, "Close to exit!");
            }
        }
        );
    }

}

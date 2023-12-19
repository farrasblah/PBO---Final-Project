package gamepesawat;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	public Frame() throws IOException, LineUnavailableException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setTitle("Battle Planes");
        setResizable(false);
        setVisible(true);  
        Background background = new Background();
        add(background);
    }
    @SuppressWarnings("unused")
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Frame frame = new Frame();
    }
}

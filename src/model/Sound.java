package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	File filepath;
	AudioInputStream audioIn;
	Clip clip;

	public Sound(String filename) throws Exception {
		filepath = new File(filename);
		audioIn = AudioSystem.getAudioInputStream(filepath);
		clip = AudioSystem.getClip();
		clip.open(audioIn);
	}

	public void play() {
		clip.start();
	}

	public void loop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

	public boolean isplaying() {
		return clip.isRunning();
	}
}

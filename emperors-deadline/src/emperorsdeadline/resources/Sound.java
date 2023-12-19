package emperorsdeadline.resources;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;

public class Sound {

	private final String path;
	private Clip clip;

	public Sound(String path) {
		this.path = path;
	}

	public void soundPlay() {
		if (this.clip != null && !this.clip.isRunning()) {
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}

	public void soundStop() {
		if (this.clip != null && this.clip.isRunning()) {
			this.clip.stop();
			this.clip.setFramePosition(0);
		}
	}

	public void soundLoop() {
		if (this.clip != null && !this.clip.isRunning()) {
			this.clip.setFramePosition(0);
			this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}

	private void loadClip() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(this.path));
			AudioFormat format = audioInputStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			if (!AudioSystem.isLineSupported(info)) {
				AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);

				audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
				format = audioInputStream.getFormat();
				info = new DataLine.Info(Clip.class, format);
			}

			this.clip = (Clip) AudioSystem.getLine(info);
			this.clip.open(audioInputStream);
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_AUDIO);
		}
	}

	private class SoundThread extends Thread {
		@Override
		public void run() {
			loadClip();
			soundStop();
		}
	}

	public void start() {
		SoundThread soundThread = new SoundThread();
		soundThread.start();
	}

}

package emperorsdeadline.resources;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;

public class Audio {

	private final String path;
	private Clip clip;

	public Audio(String path) {
		this.path = path;
	}

	public void audioPlay() {
		if (this.clip != null && !this.clip.isRunning()) {
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}

	public void audioStop() {
		if (this.clip != null && this.clip.isRunning()) {
			this.clip.stop();
			this.clip.setFramePosition(0);
		}
	}

	private void loadClip() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getResource(this.path));
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
			audioStop();
		}
	}

	public void start() {
		SoundThread soundThread = new SoundThread();
		soundThread.start();
	}

}

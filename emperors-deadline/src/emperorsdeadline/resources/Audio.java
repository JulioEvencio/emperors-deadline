package emperorsdeadline.resources;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;

public class Audio {

	private Clip clip;

	public Audio(String fileName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getResource(fileName));

			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			if (!AudioSystem.isLineSupported(info)) {
				AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);

				audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
			}

			this.clip = AudioSystem.getClip();
			this.clip.open(audioInputStream);
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_AUDIO);
		}
	}

	public void play() {
		if (!this.clip.isRunning()) {
			this.clip.setFramePosition(0);
			this.clip.start();
		}
	}

	public void stop() {
		if (this.clip.isRunning()) {
			this.clip.stop();
		}
	}

}

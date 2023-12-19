package emperorsdeadline.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;

public class Spritesheet {

	private BufferedImage spritesheet;

	public Spritesheet(String path) {
		try {
			this.spritesheet = ImageIO.read(this.getClass().getResource(path));
		} catch (Exception e) {
			Game.exitWithError(StringError.ERROR_LOADING_SPRITES);
		}
	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return this.spritesheet.getSubimage(x, y, width, height);
	}

}

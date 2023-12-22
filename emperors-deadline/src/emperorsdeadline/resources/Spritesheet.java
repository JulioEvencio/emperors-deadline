package emperorsdeadline.resources;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringError;

public class Spritesheet {

	private static BufferedImage spritesheet;

	public static BufferedImage getSprite(int x, int y, int width, int height) {
		if (Spritesheet.spritesheet == null) {
			try {
				String path = "/sprites/tilemap-separated.png";

				Spritesheet.spritesheet = ImageIO.read(Spritesheet.class.getResource(path));
			} catch (Exception e) {
				Game.exitWithError(StringError.ERROR_LOADING_SPRITES);
			}
		}

		return Spritesheet.spritesheet.getSubimage(x, y, width, height);
	}

}

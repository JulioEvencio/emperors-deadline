package emperorsdeadline.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class Farm extends Entity {

	private static BufferedImage sprite;

	public Farm(int x, int y) {
		super(x, y);

		if (Farm.sprite == null) {
			Farm.sprite = Spritesheet.getSprite(112, 208, 16, 16);
		}
	}

	@Override
	public int getResources() {
		return Util.generateRandomNumber(2, 7);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Farm.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

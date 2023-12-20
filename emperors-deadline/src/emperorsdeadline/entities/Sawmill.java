package emperorsdeadline.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class Sawmill extends Entity {

	private static BufferedImage sprite;

	public Sawmill(int x, int y) {
		super(x, y);

		if (Sawmill.sprite == null) {
			Sawmill.sprite = Spritesheet.getSprite(16, 208, 16, 16);
		}
	}

	@Override
	public int getResources() {
		return Util.generateRandomNumber(3, 10);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Sawmill.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

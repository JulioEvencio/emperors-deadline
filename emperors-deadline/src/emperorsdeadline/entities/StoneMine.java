package emperorsdeadline.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class StoneMine extends Entity {

	private static BufferedImage sprite;

	public StoneMine(int x, int y) {
		super(x, y);

		if (StoneMine.sprite == null) {
			StoneMine.sprite = Spritesheet.getSprite(64, 208, 16, 16);
		}
	}

	@Override
	public int getResources() {
		return Util.generateRandomNumber(1, 5);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(StoneMine.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

package emperorsdeadline.entities.buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class Farm extends ProductionBuilding {

	private static BufferedImage sprite;

	public Farm(int x, int y, int width, int height) {
		super(x, y, width, height);

		if (Farm.sprite == null) {
			Farm.sprite = Spritesheet.getSprite(112, 208, 16, 16);
		}
	}

	@Override
	public int getProduction() {
		return Util.generateRandomNumber(30, 35);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Farm.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

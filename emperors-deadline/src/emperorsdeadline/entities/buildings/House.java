package emperorsdeadline.entities.buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class House extends ProductionBuilding {

	private static BufferedImage sprite;

	public House(int x, int y) {
		super(x, y);

		if (House.sprite == null) {
			House.sprite = Spritesheet.getSprite(304, 208, 16, 16);
		}
	}

	@Override
	public int getProduction() {
		return Util.generateRandomNumber(1, 3);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(House.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

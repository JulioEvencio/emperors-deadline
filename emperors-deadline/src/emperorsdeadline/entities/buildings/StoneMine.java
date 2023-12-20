package emperorsdeadline.entities.buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class StoneMine extends ProductionBuilding {

	private static BufferedImage sprite;

	public StoneMine(int x, int y, int width, int height) {
		super(x, y, width, height);

		if (StoneMine.sprite == null) {
			StoneMine.sprite = Spritesheet.getSprite(64, 208, 16, 16);
		}
	}

	@Override
	public int getProduction() {
		return Util.generateRandomNumber(1, 5);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(StoneMine.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

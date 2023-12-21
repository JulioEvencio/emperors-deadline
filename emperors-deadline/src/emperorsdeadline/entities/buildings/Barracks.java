package emperorsdeadline.entities.buildings;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;
import emperorsdeadline.util.Util;

public class Barracks extends ProductionBuilding {

	private static BufferedImage sprite;

	public Barracks(int x, int y, int width, int height) {
		super(x, y, width, height);

		if (Barracks.sprite == null) {
			Barracks.sprite = Spritesheet.getSprite(208, 144, 16, 16);
		}
	}

	@Override
	public int getProduction() {
		return Util.generateRandomNumber(1, 5);
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Barracks.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

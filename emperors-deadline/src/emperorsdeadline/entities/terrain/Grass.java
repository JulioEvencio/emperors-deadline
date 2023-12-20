package emperorsdeadline.entities.terrain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;

public class Grass extends Terrain {

	private static BufferedImage sprite;

	public Grass(int x, int y, int width, int height) {
		super(x, y, width, height);

		if (Grass.sprite == null) {
			Grass.sprite = Spritesheet.getSprite(32, 32, 16, 16);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Grass.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

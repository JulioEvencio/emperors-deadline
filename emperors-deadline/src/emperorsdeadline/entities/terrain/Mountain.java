package emperorsdeadline.entities.terrain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;

public class Mountain extends Terrain {

	private static BufferedImage sprite;

	public Mountain(int x, int y) {
		super(x, y);

		if (Mountain.sprite == null) {
			Mountain.sprite = Spritesheet.getSprite(192, 0, 16, 16);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Mountain.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

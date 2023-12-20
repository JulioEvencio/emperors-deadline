package emperorsdeadline.entities.terrain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import emperorsdeadline.resources.Spritesheet;

public class Tree extends Terrain {

	private static BufferedImage sprite;

	public Tree(int x, int y) {
		super(x, y);

		if (Tree.sprite == null) {
			Tree.sprite = Spritesheet.getSprite(160, 0, 16, 16);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Tree.sprite, super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
	}

}

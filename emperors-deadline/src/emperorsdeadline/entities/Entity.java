package emperorsdeadline.entities;

import java.awt.Graphics;

public abstract class Entity {

	private final int x;
	private final int y;

	private final int width;
	private final int height;

	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean wasClicked(int x, int y) {
		return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
	}

	public abstract void render(Graphics graphics);

}

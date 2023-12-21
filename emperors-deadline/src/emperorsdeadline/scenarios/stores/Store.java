package emperorsdeadline.scenarios.stores;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import emperorsdeadline.entities.Entity;
import emperorsdeadline.scenarios.world.World;

public abstract class Store {

	protected final World world;

	protected int clickX;
	protected int clickY;
	protected boolean hasClick;

	public Store(World world) {
		this.world = world;

		this.clickX = 0;
		this.clickY = 0;
		this.hasClick = false;
	}

	protected abstract void mouseClick(Entity entity);

	public void tick(Entity entity) {
		if (this.hasClick) {
			this.mouseClick(entity);

			this.hasClick = false;
		}
	}

	public abstract void render(Graphics graphics);

	public void mouseReleased(MouseEvent e) {
		this.clickX = e.getX();
		this.clickY = e.getY();
		this.hasClick = true;
	}

}

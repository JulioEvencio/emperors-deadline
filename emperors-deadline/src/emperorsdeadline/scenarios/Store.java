package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.interfacecomponents.BuyButton;
import emperorsdeadline.entities.interfacecomponents.DeleteButton;
import emperorsdeadline.entities.terrain.Grass;

public class Store {

	private final World world;

	private final List<Entity> interfaceComponents;

	private int clickX;
	private int clickY;
	private boolean hasClick;

	public Store(World world) {
		this.world = world;

		this.interfaceComponents = new ArrayList<>();

		this.interfaceComponents.add(new BuyButton(200, 200));
		this.interfaceComponents.add(new DeleteButton(200, 250));

		this.clickX = 0;
		this.clickY = 0;
		this.hasClick = false;
	}

	private void buildFarm() {
		List<Entity> list = this.world.getEntities();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Grass) {
				Entity entity = list.remove(i);

				Farm farm = new Farm(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

				list.add(farm);

				return;
			}
		}
	}

	private void deleteFarm() {
		List<Entity> list = this.world.getEntities();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Farm) {
				list.remove(i);
				return;
			}
		}
	}

	public void tick() {
		if (this.hasClick) {
			for (Entity entity : this.interfaceComponents) {
				if (entity instanceof BuyButton && entity.wasClicked(this.clickX, this.clickY)) {
					this.buildFarm();
				}
				
				if (entity instanceof DeleteButton && entity.wasClicked(this.clickX, this.clickY)) {
					this.deleteFarm();
				}
			}

			this.hasClick = false;
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		this.interfaceComponents.forEach(entity -> entity.render(graphics));
	}

	public void mouseReleased(MouseEvent e) {
		this.clickX = e.getX();
		this.clickY = e.getY();
		this.hasClick = true;
	}

}

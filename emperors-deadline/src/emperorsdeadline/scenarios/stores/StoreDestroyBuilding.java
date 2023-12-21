package emperorsdeadline.scenarios.stores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.interfacecomponents.BackButton;
import emperorsdeadline.entities.interfacecomponents.DestroyButton;
import emperorsdeadline.entities.terrain.Grass;
import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.scenarios.world.WorldState;
import emperorsdeadline.strings.StringStore;

public class StoreDestroyBuilding extends Store {

	private final DestroyButton destroyButton;

	private final BackButton backButton;

	private boolean destroyBuilding;

	public StoreDestroyBuilding(World world) {
		super(world);

		this.destroyButton = new DestroyButton(290, 210);

		this.backButton = new BackButton(320, 400);

		this.destroyBuilding = false;
	}

	@Override
	protected void mouseClick(Entity entity) {
		if (this.backButton.wasClicked(super.clickX, super.clickY)) {
			super.world.worldState = WorldState.WORLD;
		} else if (!this.destroyBuilding) {
			this.destroyBuilding = true;

			Grass grass = new Grass(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

			super.world.getEntities().remove(entity);
			super.world.getEntities().add(grass);
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		graphics.setColor(Color.RED);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringStore.DO_YOU_WANT_TO_DESTROY_THE_BUILDING, 190, 200);

		if (!this.destroyBuilding) {
			this.destroyButton.render(graphics);
		} else {
			graphics.setColor(Color.GREEN);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString(StringStore.THE_BUILDING_WAS_DESTROYED, 200, 350);
		}

		this.backButton.render(graphics);
	}

}

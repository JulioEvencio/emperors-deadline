package emperorsdeadline.scenarios.stores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.buildings.House;
import emperorsdeadline.entities.interfacecomponents.BackButton;
import emperorsdeadline.entities.interfacecomponents.BuyButton;
import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.scenarios.world.WorldState;

public class StoreGrass extends Store {

	private final BuyButton buyFarm;
	private final BuyButton buyHouse;
	private final BackButton backButton;

	private boolean purchased;

	public StoreGrass(World world) {
		super(world);

		this.buyFarm = new BuyButton(220, 185);
		this.buyHouse = new BuyButton(230, 285);
		this.backButton = new BackButton(320, 400);

		this.purchased = false;
	}

	@Override
	protected void mouseClick(Entity entity) {
		if (this.backButton.wasClicked(super.clickX, super.clickY)) {
			super.world.worldState = WorldState.WORLD;
		} else if (!this.purchased) {
			if (this.buyFarm.wasClicked(super.clickX, super.clickY)) {
				this.purchased = true;

				Farm farm = new Farm(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

				super.world.getEntities().remove(entity);
				super.world.getEntities().add(farm);
			} else if (this.buyHouse.wasClicked(super.clickX, super.clickY)) {
				this.purchased = true;

				House house = new House(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

				super.world.getEntities().remove(entity);
				super.world.getEntities().add(house);
			}
		}
	}

	protected void tickIntern(Entity entity) {
		// System.out.println("Hello!");
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString("Farm: 8 gold", 100, 200);
		graphics.drawString("House: 5 gold", 100, 300);

		if (!this.purchased) {
			this.buyFarm.render(graphics);
			this.buyHouse.render(graphics);
		} else {
			graphics.setColor(Color.GREEN);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString("Purchased", 300, 350);
		}

		this.backButton.render(graphics);
	}

}

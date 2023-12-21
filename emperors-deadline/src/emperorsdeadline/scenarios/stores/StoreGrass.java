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
import emperorsdeadline.strings.StringStore;

public class StoreGrass extends Store {

	private final BuyButton buyFarm;

	private final int farmGold;
	private final int farmWood;

	private final BuyButton buyHouse;

	private final int houseGold;
	private final int houseWood;

	private final BackButton backButton;

	private boolean purchased;
	private boolean fail;

	public StoreGrass(World world) {
		super(world);

		this.buyFarm = new BuyButton(100, 210);

		this.farmGold = 3;
		this.farmWood = 5;

		this.buyHouse = new BuyButton(100, 310);

		this.houseGold = 5;
		this.houseWood = 8;

		this.backButton = new BackButton(320, 400);

		this.purchased = false;
		this.fail = false;
	}

	@Override
	protected void mouseClick(Entity entity) {
		if (this.backButton.wasClicked(super.clickX, super.clickY)) {
			super.world.worldState = WorldState.WORLD;
		} else if (!this.purchased) {
			if (this.buyFarm.wasClicked(super.clickX, super.clickY)) {
				if (super.world.getGold() >= this.farmGold && super.world.getWood() >= this.farmWood) {
					super.world.removeGold(this.farmGold);
					super.world.removeWood(this.farmWood);

					this.purchased = true;

					Farm farm = new Farm(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

					super.world.getEntities().remove(entity);
					super.world.getEntities().add(farm);
				} else {
					this.fail = true;
				}
			} else if (this.buyHouse.wasClicked(super.clickX, super.clickY)) {
				if (super.world.getGold() >= this.houseGold && super.world.getWood() >= this.houseWood) {
					super.world.removeGold(this.houseGold);
					super.world.removeWood(this.houseWood);

					this.purchased = true;

					House house = new House(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

					super.world.getEntities().remove(entity);
					super.world.getEntities().add(house);
				} else {
					this.fail = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringStore.getPriceFarm(this.farmGold, this.farmWood), 100, 200);
		graphics.drawString(StringStore.getPriceHouse(this.houseGold, this.houseWood), 100, 300);

		if (!this.purchased) {
			this.buyFarm.render(graphics);
			this.buyHouse.render(graphics);

			if (this.fail) {
				graphics.setColor(Color.RED);
				graphics.setFont(new Font("arial", Font.BOLD, 20));
				graphics.drawString(StringStore.INSUFFICIENT_RESOURCES, 250, 350);
			}
		} else {
			graphics.setColor(Color.GREEN);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString(StringStore.PURCHASED, 300, 350);
		}

		this.backButton.render(graphics);
	}

}

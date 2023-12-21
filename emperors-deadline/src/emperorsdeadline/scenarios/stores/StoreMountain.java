package emperorsdeadline.scenarios.stores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Barracks;
import emperorsdeadline.entities.buildings.StoneMine;
import emperorsdeadline.entities.interfacecomponents.BackButton;
import emperorsdeadline.entities.interfacecomponents.BuyButton;
import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.scenarios.world.WorldState;
import emperorsdeadline.strings.StringStore;

public class StoreMountain extends Store {

	private final BuyButton buyStoneMine;

	private final int stoneMineGold;
	private final int stoneMineWood;

	private final BuyButton buyBarracks;

	private final int barracksGold;
	private final int barracksWood;
	private final int barracksStone;

	private final BackButton backButton;

	private boolean purchased;
	private boolean fail;

	public StoreMountain(World world) {
		super(world);

		this.buyStoneMine = new BuyButton(100, 210);

		this.stoneMineGold = 150;
		this.stoneMineWood = 150;

		this.buyBarracks = new BuyButton(100, 310);

		this.barracksGold = 250;
		this.barracksWood = 250;
		this.barracksStone = 250;

		this.backButton = new BackButton(320, 400);

		this.purchased = false;
		this.fail = false;
	}

	@Override
	protected void mouseClick(Entity entity) {
		if (this.backButton.wasClicked(super.clickX, super.clickY)) {
			super.world.worldState = WorldState.WORLD;
		} else if (!this.purchased) {
			if (this.buyStoneMine.wasClicked(super.clickX, super.clickY)) {
				if (super.world.getGold() >= this.stoneMineGold && super.world.getWood() >= this.stoneMineWood) {
					super.world.removeGold(this.stoneMineGold);
					super.world.removeWood(this.stoneMineWood);

					this.purchased = true;

					StoneMine stoneMine = new StoneMine(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

					super.world.getEntities().remove(entity);
					super.world.getEntities().add(stoneMine);
				} else {
					this.fail = true;
				}
			} else if (this.buyBarracks.wasClicked(super.clickX, super.clickY)) {
				if (super.world.getGold() >= this.barracksGold && super.world.getWood() >= this.barracksWood && super.world.getStone() >= this.barracksStone) {
					super.world.removeGold(this.barracksGold);
					super.world.removeWood(this.barracksWood);
					super.world.removeStone(this.barracksStone);

					this.purchased = true;

					Barracks barracks = new Barracks(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());

					super.world.getEntities().remove(entity);
					super.world.getEntities().add(barracks);
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

		graphics.drawString(StringStore.getPriceStoneMine(this.stoneMineGold, this.stoneMineWood), 100, 200);
		graphics.drawString(StringStore.getPriceBarracks(this.barracksGold, this.barracksWood, this.barracksStone), 100, 300);

		if (!this.purchased) {
			this.buyStoneMine.render(graphics);
			this.buyBarracks.render(graphics);

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

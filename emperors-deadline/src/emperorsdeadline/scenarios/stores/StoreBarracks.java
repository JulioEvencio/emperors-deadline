package emperorsdeadline.scenarios.stores;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.interfacecomponents.AddButton;
import emperorsdeadline.entities.interfacecomponents.BackButton;
import emperorsdeadline.entities.interfacecomponents.RecruitSoldierButton;
import emperorsdeadline.entities.interfacecomponents.RemoveButton;
import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.scenarios.world.WorldState;
import emperorsdeadline.strings.StringStore;

public class StoreBarracks extends Store {

	private final RecruitSoldierButton recruitSoldierButton;
	private final AddButton addButton;
	private final RemoveButton removeButton;

	private int soldiersGold;
	private int soldiersPopulation;

	private final BackButton backButton;

	private boolean recruited;
	private boolean fail;

	public StoreBarracks(World world) {
		super(world);

		this.recruitSoldierButton = new RecruitSoldierButton(300, 220);
		this.addButton = new AddButton(200, 220);
		this.removeButton = new RemoveButton(100, 220);

		this.soldiersGold = 10;
		this.soldiersPopulation = 1;

		this.backButton = new BackButton(320, 400);

		this.recruited = false;
		this.fail = false;
	}

	@Override
	protected void mouseClick(Entity entity) {
		if (this.backButton.wasClicked(super.clickX, super.clickY)) {
			super.world.worldState = WorldState.WORLD;
		} else if (this.removeButton.wasClicked(super.clickX, super.clickY)) {
			this.soldiersPopulation -= 1;

			if (this.soldiersPopulation < 1) {
				this.soldiersPopulation = 1;
			}
		} else if (this.addButton.wasClicked(super.clickX, super.clickY)) {
			this.soldiersPopulation += 10;

			if (this.soldiersPopulation > 999) {
				this.soldiersPopulation = 999;
			}
		} else if (this.recruitSoldierButton.wasClicked(super.clickX, super.clickY)) {
			if (super.world.getGold() >= (this.soldiersGold * this.soldiersPopulation) && super.world.getPopulation() >= this.soldiersPopulation) {
				super.world.removeGold(this.soldiersGold * this.soldiersPopulation);
				super.world.removePopulation(this.soldiersPopulation);
				super.world.addSoldiers(this.soldiersPopulation);
				
				this.recruited = true;
				this.fail = false;
			} else {
				this.recruited = false;
				this.fail = true;
			}
		}
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.DARK_GRAY);
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringStore.getPriceSoldiers(this.soldiersGold * this.soldiersPopulation, this.soldiersPopulation), 100, 200);
		
		if (this.recruited) {
			graphics.setColor(Color.GREEN);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString(StringStore.RECRUITED, 300, 350);
		}

		if (this.fail) {
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString(StringStore.INSUFFICIENT_RESOURCES, 250, 350);
		}
		
		this.removeButton.render(graphics);
		this.addButton.render(graphics);
		this.recruitSoldierButton.render(graphics);
		this.backButton.render(graphics);
	}

}

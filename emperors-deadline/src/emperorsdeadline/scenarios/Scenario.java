package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.buildings.Sawmill;
import emperorsdeadline.entities.buildings.StoneMine;
import emperorsdeadline.entities.terrain.Grass;
import emperorsdeadline.entities.terrain.Mountain;
import emperorsdeadline.entities.terrain.Terrain;
import emperorsdeadline.entities.terrain.Tree;
import emperorsdeadline.strings.StringScenario;
import emperorsdeadline.util.Util;

public class Scenario {

	private int daysRemaining;
	private int gameCycle;

	private int gold;
	private int soldiers;

	private int food;
	private int population;

	private int stone;
	private int wood;

	private final List<Entity> entities;

	public Scenario() {
		this.daysRemaining = 7;
		this.gameCycle = 0;

		this.gold = 0;
		this.soldiers = 0;

		this.food = 0;
		this.population = 0;

		this.stone = 0;
		this.wood = 0;

		this.entities = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				switch (Util.generateRandomNumber(1, 3)) {
				case 1:
					this.entities.add(new Grass(20 + (100 * i), 90 + (100 * j)));
					break;
				case 2:
					this.entities.add(new Tree(20 + (100 * i), 90 + (100 * j)));
					break;
				case 3:
					this.entities.add(new Mountain(20 + (100 * i), 90 + (100 * j)));
					break;
				}
			}
		}
	}

	public void tick() {
		this.gameCycle++;

		if (this.gameCycle >= 1000) {
			this.daysRemaining--;
			this.gameCycle = 0;

			// this.gold += (this.gold > 999) ? 999 : this.population;
			// this.soldiers += (this.soldiers > 999) ? 999 : 3;

			// this.food += this.farm.getProduction();
			// this.population += (this.population > 999) ? 999 : 1;

			// this.stone += this.stoneMine.getProduction();
			// this.wood += this.sawmill.getProduction();
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(new Color(50, 140, 25));
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, Game.WIDTH, 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(String.format("%s: %d", StringScenario.GOLD, Math.min(this.gold, 999)), 20, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.SOLDIERS, Math.min(this.soldiers, 999)), 20, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.FOOD, Math.min(this.food, 999)), 200, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.POPULATION, Math.min(this.population, 999)), 200,
				50);

		graphics.drawString(String.format("%s: %d", StringScenario.STONE, Math.min(this.stone, 999)), 400, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.WOOD, Math.min(this.wood, 999)), 400, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.DAYS_REMAINING, this.daysRemaining), 530, 30);

		synchronized (this.entities) {
			this.entities.forEach(entity -> entity.render(graphics));
		}
	}

	public void keyReleased(KeyEvent e) {
		// Code
	}

	public void mouseReleased(MouseEvent e) {
		synchronized (this.entities) {
			int x = e.getX();
			int y = e.getY();

			Entity entityClick = null;

			for (Entity entity : this.entities) {
				if (entity instanceof Terrain && entity.wasClicked(x, y)) {
					entityClick = entity;
				}
			}

			if (entityClick != null) {
				this.entities.remove(entityClick);

				if (entityClick instanceof Grass) {
					Farm farm = new Farm(entityClick.getX(), entityClick.getY());

					this.entities.add(farm);
				} else if (entityClick instanceof Tree) {
					Sawmill sawmill = new Sawmill(entityClick.getX(), entityClick.getY());

					this.entities.add(sawmill);
				} else if (entityClick instanceof Mountain) {
					StoneMine stoneMine = new StoneMine(entityClick.getX(), entityClick.getY());

					this.entities.add(stoneMine);
				}
			}
		}
	}

}

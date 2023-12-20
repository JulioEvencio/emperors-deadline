package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.buildings.House;
import emperorsdeadline.entities.buildings.Sawmill;
import emperorsdeadline.entities.buildings.StoneMine;
import emperorsdeadline.entities.terrain.Grass;
import emperorsdeadline.entities.terrain.Mountain;
import emperorsdeadline.entities.terrain.Tree;
import emperorsdeadline.util.Util;

public class World {

	private int daysRemaining;
	private int gameTime;
	private int gameCycle;

	private int gold;
	private int soldiers;

	private int food;
	private int population;

	private int stone;
	private int wood;

	private final List<Entity> entities;

	public World() {
		this.daysRemaining = 7;
		this.gameTime = 0;
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
					this.entities.add(new Grass(20 + (100 * i), 90 + (100 * j), 80, 80));
					break;
				case 2:
					this.entities.add(new Tree(20 + (100 * i), 90 + (100 * j), 80, 80));
					break;
				case 3:
					this.entities.add(new Mountain(20 + (100 * i), 90 + (100 * j), 80, 80));
					break;
				}
			}
		}
	}

	protected int getDaysRemaining() {
		return daysRemaining;
	}

	protected int getGameTime() {
		return gameTime;
	}

	protected int getGameCycle() {
		return gameCycle;
	}

	protected int getGold() {
		return gold;
	}

	protected int getSoldiers() {
		return soldiers;
	}

	protected int getFood() {
		return food;
	}

	protected int getPopulation() {
		return population;
	}

	protected int getStone() {
		return stone;
	}

	protected int getWood() {
		return wood;
	}

	protected List<Entity> getEntities() {
		return entities;
	}

	public void tick() {
		this.gameCycle++;

		if (this.gameCycle >= 100) {
			this.gameTime++;
			this.gameCycle = 0;

			if (this.gameTime >= 24) {
				this.gameTime = 0;
				this.daysRemaining--;
			}

			if (this.gold < 999) {
				this.gold += this.population * Util.generateRandomNumber(1, 3);
			}

			this.entities.forEach(entity -> {
				if (entity instanceof House && this.population < 999) {
					this.population = ((House) entity).getProduction();
				}

				if (entity instanceof Farm && this.food < 999) {
					this.food += ((Farm) entity).getProduction();
				}

				if (entity instanceof Sawmill && this.wood < 999) {
					this.wood += ((Sawmill) entity).getProduction();
				}

				if (entity instanceof StoneMine && this.stone < 999) {
					this.stone += ((StoneMine) entity).getProduction();
				}
			});
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(new Color(50, 140, 25));
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		this.entities.forEach(entity -> {
			entity.render(graphics);

			if (entity.wasClicked(Game.getMouseX(), Game.getMouseY())) {
				graphics.setColor(Color.BLUE);
				graphics.drawRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
			}
		});

		this.systemDayNight(graphics);
	}

	private void systemDayNight(Graphics graphics) {
		int timeNow = (this.gameTime - 13 + 24) % 24;

		double normalizedTime = (double) timeNow / (24 - 1);
		double intensity = Math.sin(normalizedTime * Math.PI);

		int alpha = (int) (intensity * 255);

		if (alpha > 200) {
			alpha = 200;
		}

		graphics.setColor(new Color(0, 0, 0, alpha));
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);
	}

}

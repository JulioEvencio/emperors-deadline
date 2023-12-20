package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import emperorsdeadline.Game;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.buildings.Sawmill;
import emperorsdeadline.entities.buildings.StoneMine;
import emperorsdeadline.strings.StringScenario;

public class Scenario {

	private int daysRemaining;
	private int gameCycle;

	private int gold;
	private int soldiers;

	private int food;
	private int population;

	private int stone;
	private int wood;

	private Sawmill sawmill;
	private StoneMine stoneMine;
	private Farm farm;

	public Scenario() {
		this.daysRemaining = 7;
		this.gameCycle = 0;

		this.gold = 0;
		this.soldiers = 0;

		this.food = 0;
		this.population = 0;

		this.stone = 0;
		this.wood = 0;

		this.sawmill = new Sawmill(20, 100);
		this.stoneMine = new StoneMine(120, 100);
		this.farm = new Farm(220, 100);
	}

	public void tick() {
		this.gameCycle++;

		if (this.gameCycle >= 1000) {
			this.daysRemaining--;
			this.gameCycle = 0;

			// this.gold += (this.gold > 999) ? 999 : this.population;
			// this.soldiers += (this.soldiers > 999) ? 999 : 3; 

			this.food += this.farm.getProduction();
			// this.population += (this.population > 999) ? 999 : 1;

			this.stone += this.stoneMine.getProduction();
			this.wood += this.sawmill.getProduction();
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, Game.WIDTH, 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(String.format("%s: %d", StringScenario.GOLD, Math.min(this.gold, 999)), 20, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.SOLDIERS, Math.min(this.soldiers, 999)), 20, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.FOOD, Math.min(this.food, 999)), 200, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.POPULATION, Math.min(this.population, 999)), 200, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.STONE, Math.min(this.stone, 999)), 400, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.WOOD, Math.min(this.wood, 999)), 400, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.DAYS_REMAINING, this.daysRemaining), 530, 30);

		this.sawmill.render(graphics);
		this.stoneMine.render(graphics);
		this.farm.render(graphics);
	}

	public void keyReleased(KeyEvent e) {
		// Code
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		/*
		if (this.sawmill.wasClicked(x, y)) {
			System.out.printf("X: %d; Y: %d\n", x, y);
		}
		*/
	}

}

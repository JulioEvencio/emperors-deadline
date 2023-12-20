package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.Sawmill;
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

	private Entity entity;

	public Scenario() {
		this.daysRemaining = 7;
		this.gameCycle = 0;

		this.gold = 10;
		this.soldiers = 0;

		this.food = 10;
		this.population = 10;

		this.stone = 0;
		this.wood = 30;

		this.entity = new Sawmill(300, 300);
	}

	public void tick() {
		this.gameCycle++;

		if (this.gameCycle >= 1000) {
			this.daysRemaining--;
			this.gameCycle = 0;

			this.gold += this.population;
			this.soldiers += 3;

			this.food += 10;
			this.population += 1;

			this.stone += 2;
			this.wood += this.entity.getResources();
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(String.format("%s: %d", StringScenario.GOLD, Math.min(this.gold, 999)), 20, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.SOLDIERS, Math.min(this.soldiers, 999)), 20, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.FOOD, Math.min(this.food, 999)), 200, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.POPULATION, Math.min(this.population, 999)), 200, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.STONE, Math.min(this.stone, 999)), 400, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.WOOD, Math.min(this.wood, 999)), 400, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.DAYS_REMAINING, this.daysRemaining), 530, 30);

		this.entity.render(graphics);
	}

	public void keyReleased(KeyEvent e) {
		// Code
	}

	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (this.entity.wasClicked(x, y)) {
			System.out.printf("X: %d; Y: %d\n", x, y);
		}
	}

}

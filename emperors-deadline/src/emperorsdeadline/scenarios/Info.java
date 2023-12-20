package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringScenario;

public class Info {

	private final World world;

	public Info(World world) {
		this.world = world;
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, Game.WIDTH, 80);

		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(String.format("%s: %d", StringScenario.GOLD, Math.min(this.world.getGold(), 999)), 20, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.SOLDIERS, Math.min(this.world.getSoldiers(), 999)), 20, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.FOOD, Math.min(this.world.getFood(), 999)), 200, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.POPULATION, Math.min(this.world.getPopulation(), 999)), 200, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.STONE, Math.min(this.world.getStone(), 999)), 400, 30);
		graphics.drawString(String.format("%s: %d", StringScenario.WOOD, Math.min(this.world.getWood(), 999)), 400, 50);

		graphics.drawString(String.format("%s: %d", StringScenario.DAYS_REMAINING, this.world.getDaysRemaining()), 530, 30);
		graphics.drawString(String.format("%s: %02d:00", StringScenario.TIME, this.world.getGameTime()), 530, 50);
	}

}

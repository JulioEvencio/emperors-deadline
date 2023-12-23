package emperorsdeadline.scenarios.lore;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.scenarios.Scenario;
import emperorsdeadline.scenarios.ScenarioState;
import emperorsdeadline.strings.StringInstructions;
import emperorsdeadline.strings.StringIntroduction;

public class Instructions {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringInstructions.INSTRUCTIONS, 240, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringInstructions.LINE_1, Game.WIDTH / 2 - 330, 140);
		graphics.drawString(StringInstructions.LINE_2, Game.WIDTH / 2 - 330, 160);
		graphics.drawString(StringInstructions.LINE_3, Game.WIDTH / 2 - 330, 180);
		graphics.drawString(StringInstructions.LINE_4, Game.WIDTH / 2 - 330, 200);
		graphics.drawString(StringInstructions.LINE_5, Game.WIDTH / 2 - 330, 220);
		graphics.drawString(StringInstructions.LINE_6, Game.WIDTH / 2 - 330, 240);
		graphics.drawString(StringInstructions.LINE_7, Game.WIDTH / 2 - 330, 260);
		
		graphics.drawString(StringInstructions.LINE_8, Game.WIDTH / 2 - 330, 300);
		graphics.drawString(StringInstructions.LINE_9, Game.WIDTH / 2 - 330, 320);
		graphics.drawString(StringInstructions.LINE_10, Game.WIDTH / 2 - 330, 340);
		graphics.drawString(StringInstructions.LINE_11, Game.WIDTH / 2 - 330, 360);

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringIntroduction.CONTINUE, 240, 414);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Scenario.scenarioState = ScenarioState.WORLD;
		}
	}

}

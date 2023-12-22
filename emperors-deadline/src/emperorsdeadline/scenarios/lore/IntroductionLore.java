package emperorsdeadline.scenarios.lore;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.scenarios.Scenario;
import emperorsdeadline.scenarios.ScenarioState;
import emperorsdeadline.strings.StringIntroduction;

public class IntroductionLore {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringIntroduction.INTRODUCTION, 240, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringIntroduction.LINE_1, Game.WIDTH / 2 - 330, 140);
		graphics.drawString(StringIntroduction.LINE_2, Game.WIDTH / 2 - 330, 180);
		graphics.drawString(StringIntroduction.LINE_3, Game.WIDTH / 2 - 330, 200);
		graphics.drawString(StringIntroduction.LINE_4, Game.WIDTH / 2 - 330, 220);
		graphics.drawString(StringIntroduction.LINE_5, Game.WIDTH / 2 - 330, 240);
		graphics.drawString(StringIntroduction.LINE_6, Game.WIDTH / 2 - 330, 260);
		graphics.drawString(StringIntroduction.LINE_7, Game.WIDTH / 2 - 330, 280);
		graphics.drawString(StringIntroduction.LINE_8, Game.WIDTH / 2 - 330, 320);

		graphics.drawString(StringIntroduction.HOW_TO_PLAY, Game.WIDTH / 2 - 330, 340);

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

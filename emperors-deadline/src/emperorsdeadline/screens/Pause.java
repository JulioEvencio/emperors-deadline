package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.GameState;
import emperorsdeadline.scenarios.Scenario;
import emperorsdeadline.scenarios.ScenarioState;
import emperorsdeadline.strings.StringGame;
import emperorsdeadline.strings.StringPause;
import emperorsdeadline.strings.StringScreen;

public class Pause {

	private final String[] options;

	private int maxOption;
	private int currentOption;

	private boolean up;
	private boolean down;
	private boolean enter;

	public Pause() {
		this.options = new String[3];

		this.maxOption = this.options.length - 1;
		this.currentOption = 0;

		this.up = false;
		this.down = false;
		this.enter = false;

		this.options[0] = StringPause.RESUME;
		this.options[1] = StringPause.MENU;
		this.options[2] = StringScreen.EXIT;
	}

	public void tick() {
		if (this.up) {
			this.up = false;
			this.currentOption--;

			if (this.currentOption < 0) {
				this.currentOption = 0;
			}
		}

		if (this.down) {
			this.down = false;
			this.currentOption++;

			if (this.currentOption > maxOption) {
				this.currentOption = maxOption;
			}
		}

		if (this.enter) {
			this.enter = false;

			if (this.currentOption == 0) {
				Scenario.scenarioState = ScenarioState.WORLD;
			} else if (this.currentOption == 1) {
				Game.gameState = GameState.MENU;
			} else if (this.currentOption == 2) {
				Game.gameState = GameState.EXIT;
			}
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringGame.TITLE, Game.WIDTH / 2 - 180, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 24));

		graphics.drawString(options[0], Game.WIDTH / 2 - 50, 150);
		graphics.drawString(options[1], Game.WIDTH / 2 - 50, 200);
		graphics.drawString(options[2], Game.WIDTH / 2 - 50, 250);

		graphics.setColor(Color.YELLOW);

		if (currentOption == 0) {
			graphics.drawString("-> ", Game.WIDTH / 2 - 90, 150);
			graphics.drawString(options[0], Game.WIDTH / 2 - 50, 150);
		} else if (currentOption == 1) {
			graphics.drawString("-> ", Game.WIDTH / 2 - 90, 200);
			graphics.drawString(options[1], Game.WIDTH / 2 - 50, 200);
		} else if (currentOption == 2) {
			graphics.drawString("-> ", Game.WIDTH / 2 - 90, 250);
			graphics.drawString(options[2], Game.WIDTH / 2 - 50, 250);
		}

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringScreen.NAVIGATE_THE_MENU, Game.WIDTH / 2 - 180, 414);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			this.up = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_S) {
			this.down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.enter = true;
		}
	}

}
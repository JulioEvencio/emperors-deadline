package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.GameState;
import emperorsdeadline.strings.StringGame;
import emperorsdeadline.strings.StringScreen;

public class Menu {

	private final String[] options;

	private int maxOption;
	private int currentOption;

	private boolean up;
	private boolean down;
	private boolean enter;

	public Menu() {
		this.options = new String[4];

		this.maxOption = this.options.length - 1;
		this.currentOption = 0;

		this.up = false;
		this.down = false;
		this.enter = false;

		this.options[0] = StringScreen.NEW_GAME;
		this.options[1] = StringScreen.TUTORIAL;
		this.options[2] = StringScreen.CREDITS;
		this.options[3] = StringScreen.EXIT;
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
				Game.newGame();
				Game.setGameState(GameState.RUN);
			} else if (this.currentOption == 1) {
				Game.setGameState(GameState.TUTORIAL);
			} else if (this.currentOption == 2) {
				Game.setGameState(GameState.CREDITS);
			} else if (this.currentOption == 3) {
				Game.setGameState(GameState.EXIT);
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
		graphics.drawString(options[3], Game.WIDTH / 2 - 50, 300);

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
		} else if (currentOption == 3) {
			graphics.drawString("-> ", Game.WIDTH / 2 - 90, 300);
			graphics.drawString(options[3], Game.WIDTH / 2 - 50, 300);
		}

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringScreen.NAVIGATE_THE_MENU, 100, 414);
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

package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.strings.StringCredits;

public class Credits {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringCredits.CREDITS, Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringCredits.PROGRAMMER, Game.WIDTH / 2 - 330, 160);
		graphics.drawString(StringCredits.PROGRAMMER_GITHUB, Game.WIDTH / 2 - 330, 180);

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringCredits.INFO, Game.WIDTH / 2 - 180, 414);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Game.gameState = Game.GAME_MENU;
		}
	}

}

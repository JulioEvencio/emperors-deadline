package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.GameState;
import emperorsdeadline.strings.StringScreen;

public class Tutorial {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringScreen.TUTORIAL, Game.WIDTH / 2 - 70, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringScreen.PAUSE, Game.WIDTH / 2 - 330, 160);
		graphics.drawString(StringScreen.SHOW_HIDE_FPS, Game.WIDTH / 2 - 330, 180);
		graphics.drawString(StringScreen.ENABLE_DISABLE_MUSIC, Game.WIDTH / 2 - 330, 200);

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringScreen.RETURN_TO_MENU, Game.WIDTH / 2 - 180, 414);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Game.gameState = GameState.MENU;
		}
	}

}

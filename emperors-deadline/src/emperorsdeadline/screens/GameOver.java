package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.GameState;
import emperorsdeadline.strings.StringGame;
import emperorsdeadline.strings.StringScreen;

public class GameOver {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringGame.TITLE, Game.WIDTH / 2 - 180, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.RED);
		graphics.setFont(new Font("arial", Font.BOLD, 30));

		graphics.drawString(StringScreen.GAME_OVER, 280, 160);
		
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		
		graphics.drawString(StringScreen.GAME_OVER_STRING, 230, 200);

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

package emperorsdeadline.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import emperorsdeadline.Game;
import emperorsdeadline.GameState;
import emperorsdeadline.strings.StringScreen;

public class Credits {

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.YELLOW);
		graphics.setFont(new Font("arial", Font.BOLD, 36));
		graphics.drawString(StringScreen.CREDITS, Game.WIDTH / 2 - 50, Game.HEIGHT / 2 - 150);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(StringScreen.PROGRAMMER, Game.WIDTH / 2 - 330, 160);
		graphics.drawString(StringScreen.PROGRAMMER_GITHUB, Game.WIDTH / 2 - 330, 180);

		graphics.drawString(StringScreen.SPRITES, Game.WIDTH / 2 - 330, 230);
		graphics.drawString(StringScreen.SPRITES_LINK, Game.WIDTH / 2 - 330, 250);

		graphics.drawString(StringScreen.AUDIO, Game.WIDTH / 2 - 330, 300);
		graphics.drawString(StringScreen.AUDIO_LINK, Game.WIDTH / 2 - 330, 320);
		
		graphics.drawString(StringScreen.SOURCE_CODE, Game.WIDTH / 2 - 330, 370);

		graphics.setColor(Color.CYAN);
		graphics.setFont(new Font("arial", Font.BOLD, 16));
		graphics.drawString(StringScreen.RETURN_TO_MENU, Game.WIDTH / 2 - 180, 414);
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			Game.setGameState(GameState.MENU);
		}
	}

}

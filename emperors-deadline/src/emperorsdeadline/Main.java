package emperorsdeadline;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import emperorsdeadline.resources.Language;
import emperorsdeadline.strings.StringGame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Language.load();

			JFrame frame = new JFrame();
			Game game = new Game();

			frame.setTitle(StringGame.TITLE);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

			try {
				Image imageIcon = ImageIO.read(Main.class.getResource("/sprites/icon.png"));
				frame.setIconImage(imageIcon);
			} catch (Exception e) {
				Game.exitWithError("Error loading files");
			}

			new Thread(game).start();
		});
	}

}

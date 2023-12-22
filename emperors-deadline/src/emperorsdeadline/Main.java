package emperorsdeadline;

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

			new Thread(game).start();
		});
	}

}

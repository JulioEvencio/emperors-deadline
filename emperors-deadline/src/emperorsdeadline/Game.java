package emperorsdeadline;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import emperorsdeadline.screens.Credits;
import emperorsdeadline.screens.Menu;
import emperorsdeadline.screens.Tutorial;
import emperorsdeadline.strings.StringError;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;

	public static final String VERSION = "0.1";

	public static final int SCALE = 1;
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;

	public static GameState gameState;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

	private final Menu menu;
	private final Tutorial tutorial;
	private final Credits credits;

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		Game.gameState = GameState.MENU;
		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.menu = new Menu();
		this.tutorial = new Tutorial();
		this.credits = new Credits();
	}

	private void tick() {
		if (Game.gameState == GameState.MENU) {
			this.menu.tick();
		} else if (Game.gameState == GameState.EXIT) {
			Game.exitGame();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics render = this.renderer.getGraphics();

		render.setColor(Color.BLACK);
		render.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		// Code

		render.dispose();

		Graphics graphics = bs.getDrawGraphics();
		graphics.drawImage(this.renderer, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);

		if (Game.gameState == GameState.MENU) {
			this.menu.render(graphics);
		} else if (Game.gameState == GameState.TUTORIAL) {
			this.tutorial.render(graphics);
		} else if (Game.gameState == GameState.CREDITS) {
			this.credits.render(graphics);
		}

		if (this.showFPS) {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString("FPS: " + this.fps, Game.WIDTH - 100, 32);
		}

		bs.show();
	}

	@Override
	public void run() {
		this.requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000.0 / amountOfTicks;
		double delta = 0.0;

		double timer = System.currentTimeMillis();

		int frames = 0;

		while (true) {
			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				this.tick();
				this.render();

				delta--;
				frames++;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				timer = System.currentTimeMillis();

				this.fps = frames;
				frames = 0;
			}

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Code
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Game.gameState == GameState.MENU) {
			this.menu.keyReleased(e);
		} else if (Game.gameState == GameState.TUTORIAL) {
			this.tutorial.keyReleased(e);
		} else if (Game.gameState == GameState.CREDITS) {
			this.credits.keyReleased(e);
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Code
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Code
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Hello, world!");
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR, JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}

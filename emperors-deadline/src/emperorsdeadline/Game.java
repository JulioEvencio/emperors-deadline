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

	public static int gameState;

	public static final int GAME_MENU = 1;
	public static final int GAME_RUN = 2;
	public static final int GAME_PAUSED = 3;
	public static final int GAME_OVER = 4;
	public static final int GAME_EXIT = 5;
	public static final int GAME_CREDITS = 6;
	public static final int GAME_TUTORIAL = 7;
	public static final int GAME_VICTORY = 8;

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

		Game.gameState = Game.GAME_MENU;
		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.menu = new Menu();
		this.tutorial = new Tutorial();
		this.credits = new Credits();
	}

	private void tick() {
		switch (Game.gameState) {
			case Game.GAME_MENU:
				this.menu.tick();
				break;
			case Game.GAME_EXIT:
				Game.exitGame();
				break;
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

		switch (Game.gameState) {
			case Game.GAME_MENU:
				this.menu.render(graphics);
				break;
			case Game.GAME_TUTORIAL:
				this.tutorial.render(graphics);
				break;
			case Game.GAME_CREDITS:
				this.credits.render(graphics);
				break;
		}

		if (showFPS) {
			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString("FPS: " + fps, Game.WIDTH - 100, 32);
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
		switch (Game.gameState) {
			case Game.GAME_MENU:
				this.menu.keyReleased(e);
				break;
			case Game.GAME_TUTORIAL:
				this.tutorial.keyReleased(e);
				break;
			case Game.GAME_CREDITS:
				this.credits.keyReleased(e);
				break;
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

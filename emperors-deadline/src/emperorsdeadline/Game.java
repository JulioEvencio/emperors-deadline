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
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

import emperorsdeadline.resources.Audio;
import emperorsdeadline.scenarios.Scenario;
import emperorsdeadline.screens.Credits;
import emperorsdeadline.screens.Menu;
import emperorsdeadline.screens.Tutorial;
import emperorsdeadline.strings.StringError;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	public static final String VERSION = "1.0";

	public static final int SCALE = 1;
	public static final int WIDTH = 720;
	public static final int HEIGHT = 480;

	private static GameState gameState;

	private static int mouseX;
	private static int mouseY;

	private int fps;
	private boolean showFPS;

	private final BufferedImage renderer;

	private final Menu menu;
	private final Tutorial tutorial;
	private final Credits credits;

	private static Scenario scenario;

	private boolean enableAudio;

	private static Audio audioNow;
	private static Audio audioMenu;
	private static Audio audioGame;

	public Game() {
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

		Game.gameState = GameState.MENU;
		Game.mouseX = 0;
		Game.mouseY = 0;
		this.renderer = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		this.menu = new Menu();
		this.tutorial = new Tutorial();
		this.credits = new Credits();

		this.enableAudio = true;

		Game.audioMenu = new Audio("/audios/menu.wav");
		Game.audioGame = new Audio("/audios/game.wav");

		Game.setAudio(Game.audioMenu);
	}

	public static void newGame() {
		Game.scenario = new Scenario();
	}

	public static int getMouseX() {
		return Game.mouseX;
	}

	public static int getMouseY() {
		return Game.mouseY;
	}

	public static void setGameState(GameState gameState) {
		if (Game.audioNow != Game.audioGame && gameState == GameState.RUN) {
			Game.setAudio(Game.audioGame);
		} else if (Game.audioNow != Game.audioMenu) {
			Game.setAudio(audioMenu);
		}

		Game.gameState = gameState;
	}

	public static void setAudio(Audio audio) {
		if (Game.audioNow != null) {
			Game.audioNow.stop();
		}

		Game.audioNow = audio;
	}

	private void tick() {
		if (this.enableAudio) {
			Game.audioNow.play();
		} else {
			Game.audioNow.stop();
		}

		if (Game.gameState == GameState.RUN) {
			Game.scenario.tick();
		} else if (Game.gameState == GameState.MENU) {
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

		if (Game.gameState == GameState.RUN) {
			Game.scenario.render(render);
		}

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
			graphics.setColor(Color.BLACK);
			graphics.fillRect(Game.WIDTH - 105, 10, 100, 30);

			graphics.setColor(Color.WHITE);
			graphics.setFont(new Font("arial", Font.BOLD, 20));
			graphics.drawString("FPS: " + this.fps, Game.WIDTH - 100, 32);
			
			graphics.setColor(Color.WHITE);
			graphics.drawRect(Game.WIDTH - 105, 10, 100, 30);
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
		if (Game.gameState == GameState.RUN) {
			Game.scenario.keyReleased(e);
		} else if (Game.gameState == GameState.MENU) {
			this.menu.keyReleased(e);
		} else if (Game.gameState == GameState.TUTORIAL) {
			this.tutorial.keyReleased(e);
		} else if (Game.gameState == GameState.CREDITS) {
			this.credits.keyReleased(e);
		}

		if (e.getKeyCode() == KeyEvent.VK_F3) {
			this.showFPS = !this.showFPS;
		}

		if (e.getKeyCode() == KeyEvent.VK_F4) {
			this.enableAudio = !this.enableAudio;
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
		if (Game.gameState == GameState.RUN) {
			Game.scenario.mouseReleased(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Code
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Game.gameState == GameState.RUN) {
			Game.mouseX = e.getX();
			Game.mouseY = e.getY();
		}
	}

	public static void exitGame() {
		System.exit(0);
	}

	public static void exitWithError(String error) {
		JOptionPane.showMessageDialog(null, error, StringError.ERROR, JOptionPane.ERROR_MESSAGE);
		Game.exitGame();
	}

}

package emperorsdeadline.scenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import emperorsdeadline.Game;

public class Scenario {

	private int daysRemaining;
	private int gameCycle;

	private int ouro;
	private int soldados;

	private int comida;
	private int populacao;

	private int pedra;
	private int madeira;

	public Scenario() {
		this.daysRemaining = 7;
		this.gameCycle = 0;

		this.ouro = 10;
		this.soldados = 0;

		this.comida = 10;
		this.populacao = 10;

		this.pedra = 0;
		this.madeira = 30;
	}

	public void tick() {
		this.gameCycle++;

		if (this.gameCycle >= 1000) {
			this.daysRemaining--;
			this.gameCycle = 0;

			this.ouro += this.populacao;
			this.soldados += 3;

			this.comida += 10;
			this.populacao += 1;

			this.pedra += 2;
			this.madeira += 6;
		}
	}

	public void render(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("arial", Font.BOLD, 16));

		graphics.drawString(String.format("Ouro: %d", Math.min(this.ouro, 999)), 20, 20);
		graphics.drawString(String.format("Soldados: %d", Math.min(this.soldados, 999)), 20, 40);

		graphics.drawString(String.format("Comida: %d", Math.min(this.comida, 999)), 220, 20);
		graphics.drawString(String.format("População: %d", Math.min(this.populacao, 999)), 220, 40);

		graphics.drawString(String.format("Pedra: %d", Math.min(this.pedra, 999)), 420, 20);
		graphics.drawString(String.format("Madeira: %d", Math.min(this.madeira, 999)), 420, 40);

		graphics.drawString(String.format("Days remaining: %d", this.daysRemaining), 550, 20);
	}

	public void keyReleased(KeyEvent e) {
		// Code
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("Hello, world!");
	}

}

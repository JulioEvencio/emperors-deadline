package emperorsdeadline.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import emperorsdeadline.scenarios.lore.IntroductionLore;
import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.screens.GameOver;
import emperorsdeadline.screens.Pause;
import emperorsdeadline.screens.Victory;

public class Scenario {

	private final IntroductionLore introductionLore;
	private final GameOver gameOver;
	private final Victory victory;

	private final World world;
	private final Info info;

	public static ScenarioState scenarioState;

	private final Pause pause;

	public Scenario() {
		this.introductionLore = new IntroductionLore();
		this.gameOver = new GameOver();
		this.victory = new Victory();

		this.world = new World();
		this.info = new Info(this.world);

		Scenario.scenarioState = ScenarioState.INTRODUCTION_LORE;

		this.pause = new Pause();
	}

	public void tick() {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.world.tick();
		} else if (Scenario.scenarioState == ScenarioState.PAUSED) {
			this.pause.tick();
		}
	}

	public void render(Graphics graphics) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.info.render(graphics);
			this.world.render(graphics);
		} else if (Scenario.scenarioState == ScenarioState.PAUSED) {
			this.pause.render(graphics);
		} else if (Scenario.scenarioState == ScenarioState.INTRODUCTION_LORE) {
			this.introductionLore.render(graphics);
		} else if (Scenario.scenarioState == ScenarioState.GAME_OVER) {
			this.gameOver.render(graphics);
		} else if (Scenario.scenarioState == ScenarioState.VICTORY) {
			this.victory.render(graphics);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (Scenario.scenarioState == ScenarioState.PAUSED) {
			this.pause.keyReleased(e);
		} else if (Scenario.scenarioState == ScenarioState.INTRODUCTION_LORE) {
			this.introductionLore.keyReleased(e);
		} else if (Scenario.scenarioState == ScenarioState.GAME_OVER) {
			this.gameOver.keyReleased(e);
		} else if (Scenario.scenarioState == ScenarioState.VICTORY) {
			this.victory.keyReleased(e);
		}

		if ((Scenario.scenarioState == ScenarioState.WORLD || Scenario.scenarioState == ScenarioState.PAUSED) && (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P)) {
			if (Scenario.scenarioState == ScenarioState.WORLD) {
				Scenario.scenarioState = ScenarioState.PAUSED;
			} else {
				Scenario.scenarioState = ScenarioState.WORLD;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.world.mouseReleased(e);
		}
	}

}

package emperorsdeadline.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import emperorsdeadline.scenarios.world.World;
import emperorsdeadline.screens.Pause;

public class Scenario {

	private final World world;
	private final Info info;

	public static ScenarioState scenarioState;

	private final Pause pause;

	public Scenario() {
		this.world = new World();
		this.info = new Info(this.world);

		Scenario.scenarioState = ScenarioState.WORLD;

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
		}
	}

	public void keyReleased(KeyEvent e) {
		if (Scenario.scenarioState == ScenarioState.PAUSED) {
			this.pause.keyReleased(e);
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_P) {
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

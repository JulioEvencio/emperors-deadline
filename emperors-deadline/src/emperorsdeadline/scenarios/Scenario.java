package emperorsdeadline.scenarios;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Scenario {

	private final World world;
	private final Info info;
	private final Store store;

	protected static ScenarioState scenarioState;

	public Scenario() {
		this.world = new World();
		this.info = new Info(this.world);
		this.store = new Store(this.world);

		Scenario.scenarioState = ScenarioState.WORLD;
	}

	public void tick() {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.world.tick();
		} else if (Scenario.scenarioState == ScenarioState.STORE) {
			this.store.tick();
		}
	}

	public void render(Graphics graphics) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.info.render(graphics);
			this.world.render(graphics);
		} else if (Scenario.scenarioState == ScenarioState.STORE) {
			this.store.render(graphics);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			Scenario.scenarioState = ScenarioState.STORE;
		} else if (Scenario.scenarioState == ScenarioState.STORE) {
			Scenario.scenarioState = ScenarioState.WORLD;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (Scenario.scenarioState == ScenarioState.STORE) {
			this.store.mouseReleased(e);
		}
	}

}

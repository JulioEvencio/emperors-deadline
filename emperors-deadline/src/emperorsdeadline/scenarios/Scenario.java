package emperorsdeadline.scenarios;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import emperorsdeadline.scenarios.world.World;

public class Scenario {

	private final World world;
	private final Info info;

	protected static ScenarioState scenarioState;

	public Scenario() {
		this.world = new World();
		this.info = new Info(this.world);

		Scenario.scenarioState = ScenarioState.WORLD;
	}

	public void tick() {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.world.tick();
		}
	}

	public void render(Graphics graphics) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.info.render(graphics);
			this.world.render(graphics);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (Scenario.scenarioState == ScenarioState.WORLD) {
			this.world.mouseReleased(e);
		}
	}

}

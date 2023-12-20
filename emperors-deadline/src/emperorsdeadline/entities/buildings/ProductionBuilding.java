package emperorsdeadline.entities.buildings;

import emperorsdeadline.entities.Entity;

public abstract class ProductionBuilding extends Entity {

	public ProductionBuilding(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public abstract int getProduction();

}

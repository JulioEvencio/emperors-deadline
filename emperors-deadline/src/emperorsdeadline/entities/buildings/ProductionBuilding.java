package emperorsdeadline.entities.buildings;

import emperorsdeadline.entities.Entity;

public abstract class ProductionBuilding extends Entity {

	public ProductionBuilding(int x, int y) {
		super(x, y);
	}

	public abstract int getProduction();

}

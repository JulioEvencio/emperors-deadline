package emperorsdeadline.scenarios.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import emperorsdeadline.Game;
import emperorsdeadline.entities.Entity;
import emperorsdeadline.entities.buildings.Barracks;
import emperorsdeadline.entities.buildings.Farm;
import emperorsdeadline.entities.buildings.House;
import emperorsdeadline.entities.buildings.Sawmill;
import emperorsdeadline.entities.buildings.StoneMine;
import emperorsdeadline.entities.terrain.Grass;
import emperorsdeadline.entities.terrain.Mountain;
import emperorsdeadline.entities.terrain.Tree;
import emperorsdeadline.scenarios.Scenario;
import emperorsdeadline.scenarios.ScenarioState;
import emperorsdeadline.scenarios.stores.StoreBarracks;
import emperorsdeadline.scenarios.stores.StoreDestroyBuilding;
import emperorsdeadline.scenarios.stores.StoreGrass;
import emperorsdeadline.scenarios.stores.StoreMountain;
import emperorsdeadline.scenarios.stores.StoreTree;
import emperorsdeadline.util.Util;

public class World {

	public WorldState worldState;
	
	private int clickX;
	private int clickY;
	private boolean hasClick;
	private Entity entitySelected;
	
	private int daysRemaining;
	private int gameTime;
	private int gameCycle;

	private int gold;
	private int soldiers;

	private int food;
	private int population;

	private int stone;
	private int wood;

	private final List<Entity> entities;
	
	private StoreGrass storeGrass;
	private StoreTree storeTree;
	private StoreMountain storeMountain;
	private StoreBarracks storeBarracks;
	private StoreDestroyBuilding storeDestroyBuilding;

	public World() {
		this.worldState = WorldState.WORLD;
		
		this.clickX = 0;
		this.clickY = 0;
		this.hasClick = false;
		
		this.daysRemaining = 7;
		this.gameTime = 0;
		this.gameCycle = 0;

		this.gold = 999;
		this.soldiers = 0;

		this.food = 999;
		this.population = 999;

		this.stone = 999;
		this.wood = 999;

		this.entities = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				switch (Util.generateRandomNumber(1, 3)) {
					case 1:
						this.entities.add(new Grass(20 + (100 * i), 90 + (100 * j), 80, 80));
						break;
					case 2:
						this.entities.add(new Tree(20 + (100 * i), 90 + (100 * j), 80, 80));
						break;
					case 3:
						this.entities.add(new Mountain(20 + (100 * i), 90 + (100 * j), 80, 80));
						break;
				}
			}
		}
		
		this.storeGrass = new StoreGrass(this);
		this.storeTree = new StoreTree(this);
		this.storeMountain = new StoreMountain(this);
		this.storeMountain.resetBarrack();
		this.storeBarracks = new StoreBarracks(this);
		this.storeDestroyBuilding = new StoreDestroyBuilding(this);
	}

	public int getDaysRemaining() {
		return daysRemaining;
	}

	public int getGameTime() {
		return gameTime;
	}

	public int getGameCycle() {
		return gameCycle;
	}

	public int getGold() {
		return gold;
	}
	
	public void removeGold(int value) {
		this.gold -= value;
	}

	public int getSoldiers() {
		return soldiers;
	}
	
	public void addSoldiers(int soldiers) {
		this.soldiers += soldiers;
	}

	public int getFood() {
		return food;
	}

	public int getPopulation() {
		return population;
	}
	
	public void removePopulation(int value) {
		this.population -= value;
	}

	public int getStone() {
		return stone;
	}
	
	public void removeStone(int value) {
		this.stone -= value;
	}

	public int getWood() {
		return wood;
	}
	
	public void removeWood(int value) {
		this.wood -= value;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	private void mouseClick() {
		this.entities.forEach(entity -> {
			if (entity.wasClicked(this.clickX, this.clickY)) {
				this.entitySelected = entity;

				if (entity instanceof Grass) {
					this.storeGrass = new StoreGrass(this);
					this.worldState = WorldState.STORE_GRASS;
				} else if (entity instanceof Tree) {
					this.storeTree = new StoreTree(this);
					this.worldState = WorldState.STORE_TREE;
				} else if (entity instanceof Mountain) {
					this.storeMountain = new StoreMountain(this);
					this.worldState = WorldState.STORE_MOUNTAIN;
				} else if (entity instanceof Barracks) {
					this.storeBarracks = new StoreBarracks(this);
					this.worldState = WorldState.STORE_BARRACKS;
				} else {
					this.storeDestroyBuilding = new StoreDestroyBuilding(this);
					this.worldState = WorldState.STORE_DESTROY_BUILDING;
				}
			}
		});
	}
	
	private void gameCycle() {
		this.gameCycle++;

		// if (this.gameCycle >= 100) {
		if (this.gameCycle >= 10) {
			this.gameTime++;
			this.gameCycle = 0;

			if (this.gameTime >= 24) {
				this.gameTime = 0;
				this.daysRemaining--;
			}

			this.entities.forEach(entity -> {
				if (entity instanceof House) {
					if (this.population < 999) {
						int newPopulation = ((House) entity).getProduction();
						
						if ((this.food - newPopulation) >= 0) {
							this.population += newPopulation;
						}
					}
					
					if (this.gold < 999 && this.population > 0) {
						this.gold += ((House) entity).getProduction();
					}
				}

				if (entity instanceof Farm && this.food < 999) {
					this.food += ((Farm) entity).getProduction();
				}

				if (entity instanceof Sawmill && this.wood < 999) {
					this.wood += ((Sawmill) entity).getProduction();
				}

				if (entity instanceof StoneMine && this.stone < 999) {
					this.stone += ((StoneMine) entity).getProduction();
				}
			});
			
			this.food -= this.population;
			
			if (this.food < 0) {
				this.food = 0;
				this.population -= 5;
				
				if (this.population < 0) {
					this.population = 0;
				}
			}
		}
	}

	public void tick() {
		if (this.daysRemaining < 0) {
			if (this.soldiers > Util.generateRandomNumber(80, 120)) {
				Scenario.scenarioState = ScenarioState.VICTORY;
			} else {
				Scenario.scenarioState = ScenarioState.GAME_OVER;
			}
		}
		
		if (this.worldState == WorldState.WORLD) {
			if (this.hasClick) {
				this.mouseClick();

				this.hasClick = false;
			} else {
				this.gameCycle();
			}
		} else if (this.worldState == WorldState.STORE_GRASS) {
			this.storeGrass.tick(this.entitySelected);
		} else if (this.worldState == WorldState.STORE_TREE) {
			this.storeTree.tick(this.entitySelected);
		} else if (this.worldState == WorldState.STORE_MOUNTAIN) {
			this.storeMountain.tick(this.entitySelected);
		} else if (this.worldState == WorldState.STORE_BARRACKS) {
			this.storeBarracks.tick(this.entitySelected);
		} else if (this.worldState == WorldState.STORE_DESTROY_BUILDING) {
			this.storeDestroyBuilding.tick(this.entitySelected);
		}
	}
	
	private void renderWorld(Graphics graphics) {
		graphics.setColor(new Color(50, 140, 25));
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);

		this.entities.forEach(entity -> {
			entity.render(graphics);

			if (entity.wasClicked(Game.getMouseX(), Game.getMouseY())) {
				graphics.setColor(Color.BLUE);
				graphics.drawRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
			}
		});

		this.systemDayNight(graphics);
	}

	private void systemDayNight(Graphics graphics) {
		int timeNow = (this.gameTime - 13 + 24) % 24;

		double normalizedTime = (double) timeNow / (24 - 1);
		double intensity = Math.sin(normalizedTime * Math.PI);

		int alpha = (int) (intensity * 255);

		if (alpha > 200) {
			alpha = 200;
		}

		graphics.setColor(new Color(0, 0, 0, alpha));
		graphics.fillRect(0, 80, Game.WIDTH, Game.HEIGHT - 80);
	}
	
	public void render(Graphics graphics) {
		if (this.worldState == WorldState.WORLD) {
			this.renderWorld(graphics);
		} else if (this.worldState == WorldState.STORE_GRASS) {
			this.storeGrass.render(graphics);
		} else if (this.worldState == WorldState.STORE_TREE) {
			this.storeTree.render(graphics);
		} else if (this.worldState == WorldState.STORE_MOUNTAIN) {
			this.storeMountain.render(graphics);
		} else if (this.worldState == WorldState.STORE_BARRACKS) {
			this.storeBarracks.render(graphics);
		} else if (this.worldState == WorldState.STORE_DESTROY_BUILDING) {
			this.storeDestroyBuilding.render(graphics);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (this.worldState == WorldState.WORLD) {
			this.clickX = e.getX();
			this.clickY = e.getY();
			this.hasClick = true;
		} else if (this.worldState == WorldState.STORE_GRASS) {
			this.storeGrass.mouseReleased(e);
		} else if (this.worldState == WorldState.STORE_TREE) {
			this.storeTree.mouseReleased(e);
		} else if (this.worldState == WorldState.STORE_MOUNTAIN) {
			this.storeMountain.mouseReleased(e);
		} else if (this.worldState == WorldState.STORE_BARRACKS) {
			this.storeBarracks.mouseReleased(e);
		} else if (this.worldState == WorldState.STORE_DESTROY_BUILDING) {
			this.storeDestroyBuilding.mouseReleased(e);
		}
	}
	
}

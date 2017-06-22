package com.brainplus.spacespuds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.brainplus.spacespuds.gameobjects.Jet;
import com.brainplus.spacespuds.gameobjects.Missile;
import com.brainplus.spacespuds.gameobjects.Potato;
import com.brainplus.spacespuds.gameobjects.Enemy;
import com.brainplus.spacespuds.gameobjects.UFO;

import java.util.ArrayList;

public class GameWorld {
	public Jet jet;
	public float elapsedTime;
	public SpaceSpuds game;

	private class GameLevel {
		public String name;
		public Texture background;
		public int numEnemies;
		public GameLevel(String name, Texture background, int numEnemies) {
			this.name = name;
			this.background = background;
			this.numEnemies = numEnemies;
		}
	}

	public enum GameState {
		START,
		PLAY,
		GAME_OVER,
		WIN
	}

	private ArrayList<GameLevel> levels;
	public ArrayList<Enemy> enemies;
	public ArrayList<Missile> missiles;
	private int currentLevel;

	public GameState state;

	public GameWorld(SpaceSpuds game) {
		this.game = game;

		levels = new ArrayList<GameLevel>();
		levels.add(new GameLevel("Earth", Assets.backgroundEarth, 4));
		levels.add(new GameLevel("Space", Assets.backgroundSpace, 8));
		currentLevel = 0;

		jet = new Jet();
		enemies = new ArrayList<Enemy>();
		missiles = new ArrayList<Missile>();
		restart();

	}

	public String getLevelName() {
		return levels.get(currentLevel).name;
	}

	public Texture getLevelBackground() {
		return levels.get(currentLevel).background;
	}

	public int getLevelNumEnemies() {
		return levels.get(currentLevel).numEnemies;
	}

	public void restart() {
		elapsedTime = 0;

		// add some enemies
		enemies.clear();
		for(int i = 0; i < getLevelNumEnemies(); i++) {
			enemies.add(new UFO(MathUtils.random(getLevelBackground().getWidth()/2), MathUtils.random(getLevelBackground().getHeight()/2)));
		}
		//enemies.add(new UFO(100, 250));
		//enemies.add(new Potato(50, 50));

		// no missiles yet
		missiles.clear();

		// intial speed and position
		jet.init(300, 250);
		//jet.init(100, 550);
		jet.setSpeed(-2, 0);
		jet.setBackgroundHeight(getLevelBackground().getHeight());

		// show the get ready screen
		state = GameState.START;
		Assets.playStartSound();
	}

	public void update(float elapsedTime) {
		this.elapsedTime = elapsedTime;

		if (state == GameState.PLAY) {

			jet.update(elapsedTime);

			for (Enemy e : enemies) {
				e.update(elapsedTime);
			}

			for(Missile m: missiles) {
				m.update(elapsedTime);
			}


			// check if something has been hit
			for(Missile m: missiles) {
				for (Enemy e : enemies) {
					if(Intersector.overlaps(m.getBoundingRectangle(), e.getBoundingRectangle())) {
						m.setX(e.getX());
						m.setY(e.getY());
						m.setExploding(elapsedTime);
						e.setKilled(elapsedTime);
					}
				}
			}


			// remove any dead sprites
			if(!jet.isAlive()) {
				state = GameState.GAME_OVER;
			}

			for(Enemy e: enemies) {
				if(!e.isAlive()) enemies.remove(e);
				break;
			}

			for(Missile m: missiles) {
				if(!m.isAlive()) missiles.remove(m);
				break;
			}

			// check game over conditions
			if(enemies.isEmpty()) {
				if(currentLevel == levels.size() - 1) {
					currentLevel = 0;
					Assets.playGameOverSound();
					state = GameWorld.GameState.WIN;
				} else {
					++currentLevel;
					restart();
				}
			}
		}
	}
}

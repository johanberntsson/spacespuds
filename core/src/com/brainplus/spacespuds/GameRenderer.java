package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brainplus.spacespuds.gameobjects.Enemy;
import com.brainplus.spacespuds.gameobjects.Missile;

public class GameRenderer {
	private GameWorld world;
	private int width, height; // TODO: remove me and use fixed aspect (GAME_WIDTH, GAME_HEIGHT)
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Viewport viewport;

	public GameRenderer(GameWorld world) {
		this.world = world;
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT);
		viewport = new FitViewport(SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT, camera);
	}

	public void renderLogo(Texture img) {
		spriteBatch.draw(img, (width - img.getWidth()) / 2, (height - img.getHeight()) / 2);
	}

	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		spriteBatch.setProjectionMatrix(camera.combined);

		spriteBatch.begin();

		// displacement of the screen compared to the jet (fixed in the middle)
		int dx = (int) (width/2 - world.jet.getX());
		int dy = (int) (height/2 - world.jet.getY());

		// draw the background
		spriteBatch.draw(world.getLevelBackground(), 0, 0, -dx, world.getLevelBackground().getHeight() - Gdx.graphics.getHeight() + dy, width, height);

		// draw enemies
		for (Enemy e : world.enemies) {
			e.draw(spriteBatch, world.elapsedTime, dx, dy);
		}

		// draw missiles
		for (Missile m : world.missiles) {
			m.draw(spriteBatch, world.elapsedTime, dx, dy);
		}

		// draw enemy count (score)
		Assets.textFont.draw(spriteBatch, Integer.toString(world.enemies.size()), width / 2, height);

		// Draw the jet, flipping it if needed
		world.jet.draw(spriteBatch, world.elapsedTime, dx, dy);

		// draw "get ready" and "game over" logos if needed
		if (world.state == GameWorld.GameState.START) {
			//renderLogo(Assets.logoTexture);
			String name = world.getLevelName();
			Assets.textFont.draw(spriteBatch, name, (width - Assets.textFont.getSpaceWidth()*name.length()) / 2, height / 2);
		}
		if (world.state == GameWorld.GameState.GAME_OVER) {
			renderLogo(Assets.gameOverTexture);
		}
		if (world.state == GameWorld.GameState.WIN) {
			//renderLogo(Assets.youWinTexture);
			world.game.showWinScreen();
		}
		spriteBatch.end();
	}

	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		viewport.update(width, height);
		//Gdx.app.log("Renderer", "resize "+width+" "+height);
	}
	public void dispose() {
		spriteBatch.dispose();
	}
}

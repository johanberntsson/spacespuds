package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

public class GameScreen implements Screen {
	private float elapsedTime;
	private GameWorld world;
	private GameRenderer renderer;
	private GameInputHandler inputHandler;

	public GameScreen(SpaceSpuds game) {
		world = new GameWorld(game);
		renderer = new GameRenderer(world);
		inputHandler = new GameInputHandler(world);
	}

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		InputMultiplexer im = new InputMultiplexer();
		GestureDetector gd = new GestureDetector(inputHandler);
		im.addProcessor(gd);
		im.addProcessor(inputHandler);
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void render(float delta) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		world.update(elapsedTime);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		// everything with dispose methods should be disposed
		renderer.dispose();
		Assets.dispose();
	}
}

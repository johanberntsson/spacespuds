package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class WinScreen implements Screen {
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;

	public WinScreen() {
		Button button;
		batch = new SpriteBatch();

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new FitViewport(SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT));

		button = new TextButton("EXIT", skin);
		button.setPosition(3 * SpaceSpuds.GAME_WIDTH / 6, 100);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(button);
	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(stage.getCamera().combined);
		batch.begin();
		batch.draw(Assets.youWinTexture, (SpaceSpuds.GAME_WIDTH - Assets.youWinTexture.getWidth()) / 2, (SpaceSpuds.GAME_HEIGHT - Assets.youWinTexture.getHeight()) / 2);
		batch.end();

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
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

	}
}

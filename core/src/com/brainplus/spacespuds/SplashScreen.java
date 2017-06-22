package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SplashScreen implements Screen {
	private SpriteBatch batch;

	private Stage stage;
	private Skin skin;
	final SpaceSpuds game;

	public SplashScreen(SpaceSpuds g) {
		game = g;
		Button button;
		final Dialog helpDialog;

		skin = new Skin(Gdx.files.internal("uiskin.json"));
		stage = new Stage(new FitViewport(SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT));

		button = new TextButton("PLAY", skin);
		button.setPosition(1*SpaceSpuds.GAME_WIDTH/6, 100);
		button.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showGame();
			};
		});
		stage.addActor(button);

		helpDialog = new Dialog("ABOUT", skin) {
			protected void result(Object object) {
				System.out.println("Option: " + object);
			};
		};
		helpDialog.text("SPACE SPUDS\n\n"+
				"Written by Johan Berntsson and #tatar\n\n"+
				"The evil potatoe aliens have invaded!\nGet into your jet fighter and destroy them\n"+
				"The world is counting on you, captain!\n\n"+
				"Space Spuds uses free assets from these sources:\n"+
				"Through_Space.ogg by maxstack: http://opengameart.org\n"+
				"Space_Basics: http://www.roencia.com\n"+
				"Prinz Eugn: http://prinzeugn.deviantart.com/\n"+
				"Blender Foundation: http://opengameart.org\n"+
				"Jonas Wagner: http://opengameart.org"

		);
		helpDialog.button("Close", 1L);

		button = new TextButton("ABOUT", skin);
		button.setPosition(3*SpaceSpuds.GAME_WIDTH/6, 100);
		button.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				helpDialog.show(stage);
			}
		});
		stage.addActor(button);

		button = new TextButton("EXIT", skin);
		button.setPosition(5*SpaceSpuds.GAME_WIDTH/6, 100);
		button.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			};
		});
		stage.addActor(button);

		batch = new SpriteBatch();

		/*
 		camera = new OrthographicCamera();
        camera.setToOrtho(false, SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT);
		viewport = new FitViewport(SpaceSpuds.GAME_WIDTH, SpaceSpuds.GAME_HEIGHT, camera);
        batch = new SpriteBatch();
		*/
	}

	@Override
	public void show() {
		//Gdx.input.setInputProcessor(this);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(stage.getCamera().combined);
		batch.begin();
		batch.draw(Assets.splashscreenTexture, 0, 0);
		batch.end();

		stage.act();
		stage.draw();
		/*
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(Assets.splashscreenTexture, 0, 0);
		playButton.draw(batch);
		helpButton.draw(batch);
		exitButton.draw(batch);
		batch.end();
		*/
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
		//viewport.update(width, height);
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

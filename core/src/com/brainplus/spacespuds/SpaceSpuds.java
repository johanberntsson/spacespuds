package com.brainplus.spacespuds;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

public class SpaceSpuds extends Game {
	// What size the game is designed for
	public static int GAME_WIDTH = 540;
	public static int GAME_HEIGHT = 960;

	private Screen gameScreen;
	private Screen splashScreen;
	private Screen winScreen;


	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Gdx.app.log("keyTyped", "test");

		Assets.load();
		//Assets.setMute(true);
		Assets.playMusic();

		gameScreen = new GameScreen(this);
		splashScreen = new SplashScreen(this);
		winScreen = new WinScreen();

		showSplash();
	}

	public void showGame() {
		setScreen(gameScreen);
	}

	public void showSplash() {
		setScreen(splashScreen);
	}

	public void showWinScreen() {
		setScreen(winScreen);
	}
}

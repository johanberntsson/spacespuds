package com.brainplus.spacespuds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.brainplus.spacespuds.gameobjects.Enemy;
import com.brainplus.spacespuds.gameobjects.Missile;

public class GameInputHandler implements InputProcessor, GestureListener {
    // Ask for a reference to the Bird when InputHandler is created.
    private GameWorld world;

    public GameInputHandler(GameWorld gameWorld) {
		world = gameWorld;
    }
	@Override
	public boolean keyDown(int keycode) {
		//Gdx.app.log("keyTyped", "key "+keycode);
		if(keycode == Input.Keys.BACK){
			world.game.showSplash();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Gdx.app.log("InputHandler", "touch");
		if(world.state == GameWorld.GameState.START) {
			world.state = GameWorld.GameState.PLAY;
		} else if(world.state == GameWorld.GameState.GAME_OVER || world.state == GameWorld.GameState.WIN) {
			world.restart();
		} else if(world.state == GameWorld.GameState.PLAY) {
			// check if we hit any enemies
			int x =  (int) (world.jet.getX() + (screenX - Gdx.graphics.getWidth()/2));
			int y =  (int) (world.jet.getY() + Gdx.graphics.getHeight()/2 - screenY);
			world.missiles.add(new Missile(world.jet.getX(), world.jet.getY(), x, y));
			//Gdx.app.log("Coords", "screen "+screenX+", "+screenY+": map  "+x+", "+y+": jet  "+world.jet.x+", "+world.jet.y);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		//Gdx.app.log("InputHandler", "fling "+velocityX+" "+velocityY);

		// normalise the changes
		float max = Math.max(Math.abs(velocityX), Math.abs(velocityY));
		velocityX /= max;
		velocityY /= max;

		// change jet plane velocity
		world.jet.adjustSpeed(5 * velocityX, 5 * velocityY);

		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

	@Override
	public void pinchStop() {

	}
}

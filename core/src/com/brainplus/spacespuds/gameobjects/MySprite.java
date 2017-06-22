package com.brainplus.spacespuds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract public class MySprite extends Sprite {
	public void init(float x, float y) {
		setPosition(x, y);
	}

	abstract public void update(float elapsedTime);
	abstract public void draw(SpriteBatch batch, float elapsedTime, int dx, int dy);
}

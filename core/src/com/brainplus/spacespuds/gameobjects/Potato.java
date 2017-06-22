package com.brainplus.spacespuds.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brainplus.spacespuds.Assets;

public class Potato extends Enemy {
	private int dx, updates;

	public Potato(int x, int y) {
		init(x, y);
	}

	public void init(int x, int y) {
		super.init(x, y);
		updates = 0;
		changeState(State.WALKING, 0);
	}

	@Override
	public void update(float elapsedTime) {
		if(state == State.WALKING) {
			++updates;
			if (updates >= 200) {
				updates = 0;

			}
			if (updates < 100) dx = 1;
			else dx = -1;
			translate(dx, 0);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float elapsedTime, int dx, int dy) {
		TextureRegion frame = null;
		switch(state) {
			case WALKING:
				frame = Assets.potatoAnimation.getKeyFrame(elapsedTime, true);
				break;
			case EXPLODING:
				if(Assets.explosionAnimation.isAnimationFinished(elapsedTime - stateStartTime)) {
					changeState(State.DEAD, 0);
					return;
				} else {
					frame = Assets.explosionAnimation.getKeyFrame(elapsedTime - stateStartTime, false);
				}
				break;
			case DEAD:
				return;
		}
		setSize(frame.getRegionWidth(), frame.getRegionHeight());
		batch.draw(frame, getX() + dx - frame.getRegionWidth()/2, getY() + dy - frame.getRegionHeight()/2);
	}
}


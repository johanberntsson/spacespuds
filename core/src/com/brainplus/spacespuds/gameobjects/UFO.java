package com.brainplus.spacespuds.gameobjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brainplus.spacespuds.Assets;

public class UFO extends Enemy {
	int dy, updates;

	public UFO(int x, int y) {
		init(x, y);
	}

	public void init(int x, int y) {
		super.init(x, y);
		updates = 0;
		changeState(State.FLYING, 0);
	}

	@Override
	public void update(float elapsedTime) {
		++updates;
		if (updates >= 200) {
			updates = 0;

		}
		if (updates < 100) dy = 1;
		else dy = -1;
		translate(0, dy);
	}

	@Override
	public void draw(SpriteBatch batch, float elapsedTime, int dx, int dy) {
		float scale = 0.5f;
		TextureRegion frame = null;
		switch(state) {
			case FLYING:
				frame = Assets.ufoAnimation.getKeyFrame(elapsedTime, true);
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
		batch.draw(frame, getX() + dx, getY() + dy, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), scale, scale, 0);
	}
}


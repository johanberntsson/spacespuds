package com.brainplus.spacespuds.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brainplus.spacespuds.Assets;

public class Missile extends MySprite {
	private int targetX, targetY;
	private float dx, dy;
	private int updates;

	protected enum State {
		FLYING, EXPLODING, DEAD
	};
	protected State state;
	protected float stateStartTime;

	public Missile(float x, float y, int targetX, int targetY) {
		init(x, y, targetX, targetY);
	}

	public void init(float x, float y, int targetX, int targetY) {
		super.init(x, y);
		this.targetX = targetX;
		this.targetY = targetY;

		dx = targetX - x;
		dy = targetY - y;
		float max = Math.max(Math.abs(dx), Math.abs(dy));
		dx /= max;
		dy /= max;

		changeState(State.FLYING, 0);
		updates = 0;
	}

	protected void changeState(State newState, float elapsedTime) {
		if(state != newState) {
			state = newState;
			stateStartTime = elapsedTime;
		}
	}
	public boolean isAlive() {
		return (state != State.DEAD);
	}

	public void setExploding(float elapsedTime) {
		if(state == State.EXPLODING || state == State.DEAD) return;
		Assets.playExplosionSound();
		changeState(State.EXPLODING, elapsedTime);
	}

	public void setDead(float elapsedTime) {
		if(state == State.EXPLODING || state == State.DEAD) return;
		changeState(State.DEAD, elapsedTime);
	}

	@Override
	public void update(float elapsedTime) {
		if(state != State.FLYING) return;

		translate(5 * dx, 5 * dy);
		++updates;
		if((Math.abs(getX() - targetX) < 10 && Math.abs(getY() - targetY) < 10) || updates > 50) {
			setExploding(elapsedTime);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float elapsedTime, int dx, int dy) {
		float scale = 1f;
		TextureRegion frame = null;
		switch(state) {
			case FLYING:
				scale = .7f;
				frame = Assets.missileAnimation.getKeyFrame(elapsedTime - stateStartTime, false);
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
		boolean flip = (this.dx < 0);
		if((flip && !frame.isFlipX()) || (!flip && frame.isFlipX())) frame.flip(true, false);
		setSize(frame.getRegionWidth(), frame.getRegionHeight());
		batch.draw(frame, getX() + dx, getY() + dy, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), scale, scale, 0);
	}
}

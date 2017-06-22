package com.brainplus.spacespuds.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.brainplus.spacespuds.Assets;

public class Jet extends MySprite {
	private float xspeed, yspeed, backgroundHeight;
	private float startTime;

	private enum State {
		FLYING, CRASHING, EXPLODING, DEAD
	}
	private State state;

	public void init(int x, int y) {
		super.init(x, y);
		state = State.FLYING;
	}

	public void setBackgroundHeight(int backgroundHeight) {
		this.backgroundHeight = backgroundHeight;
	}

	public void setSpeed(float xspeed, float yspeed) {
		this.xspeed = xspeed;
		this.yspeed = yspeed;
	}

	public void adjustSpeed(float xchange, float ychange) {
		if(state == State.FLYING) {
			xspeed += xchange;
			yspeed -= ychange;
		}
	}

	public boolean isAlive() {
		return (state != State.DEAD);
	}

	@Override
	public void update(float elapsedTime) {
		if(state == State.EXPLODING || state == State.DEAD) return;

		translate(xspeed, yspeed);

		//Gdx.app.log("Jet", "update x,y "+x+","+y+": "+Assets.backHeight);

		// make sure that we don't fly too far off so we
		// lose track of the enemy sprites
		//x = (x % Assets.backWidth);

		// check if we hit the ground
		if (getY() < 0) {
			setY(0);
			/*
			startTime = elapsedTime;
			state = State.EXPLODING;
			Assets.playExplosionSound();
			*/
		}

		// make sure that we don't fly out in space
		if (getY() > backgroundHeight) {
			setY(backgroundHeight);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float elapsedTime, int dx, int dy) {
		TextureRegion frame = null;
		switch(state) {
			case FLYING:
				frame = Assets.jetAnimation.getKeyFrame(0, true);
				break;
			case CRASHING:
				frame = Assets.jetAnimation.getKeyFrame(elapsedTime, true);
				break;
			case EXPLODING:
				if(Assets.explosionAnimation.isAnimationFinished(elapsedTime - startTime)) {
					state = State.DEAD;
					return;
				} else {
					frame = Assets.explosionAnimation.getKeyFrame(elapsedTime - startTime, false);
				}
				break;
			case DEAD:
				return;
		}

		boolean flip = (xspeed < 0);
		if((flip && !frame.isFlipX()) || (!flip && frame.isFlipX())) frame.flip(true, false);

		setSize(frame.getRegionWidth(), frame.getRegionHeight());
		batch.draw(frame, getX() + dx - frame.getRegionWidth()/2, getY() + dy - frame.getRegionHeight()/2);
	}
}

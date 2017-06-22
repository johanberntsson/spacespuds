package com.brainplus.spacespuds.gameobjects;

import com.brainplus.spacespuds.Assets;

abstract public class Enemy extends MySprite {
	protected enum State {
		APPEARING, FLYING, LANDING, LANDED, WALKING, LEAVING, EXPLODING, DEAD
	};
	protected State state;
	protected float stateStartTime;
	protected void changeState(State newState, float elapsedTime) {
		if(state != newState) {
			state = newState;
			stateStartTime = elapsedTime;
		}
	}
	public boolean isAlive() {
		return (state != State.DEAD);
	}

	public void setKilled(float elapsedTime) {
		if(state == State.EXPLODING || state == State.DEAD) return;
		//Assets.playExplosionSound();
		changeState(State.DEAD, elapsedTime);
	}
}

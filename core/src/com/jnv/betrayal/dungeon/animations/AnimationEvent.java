package com.jnv.betrayal.dungeon.animations;

public class AnimationEvent {

	private float animationDuration;
	private Runnable runnable;

	public AnimationEvent(float animationDuration, Runnable runnable) {
		this.animationDuration = animationDuration;
		this.runnable = runnable;
	}

	public float getAnimationDuration() {
		return animationDuration;
	}

	public Runnable getRunnable() {
		return runnable;
	}
}

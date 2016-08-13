package com.jnv.betrayal.dungeon.animations;

public class AnimationEvent {

	private float animationDuration;
	private Runnable fullRunnable;

	public AnimationEvent(float animationDuration, final Runnable animation, final Runnable eventLogic) {
		this.animationDuration = animationDuration;
		fullRunnable = new Runnable() {
			@Override
			public void run() {
				animation.run();
				eventLogic.run();
			}
		};
	}

	public float getAnimationDuration() {
		return animationDuration;
	}

	public Runnable getRunnable() {
		return fullRunnable;
	}
}

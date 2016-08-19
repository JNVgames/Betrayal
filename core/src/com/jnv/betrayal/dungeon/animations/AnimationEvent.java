package com.jnv.betrayal.dungeon.animations;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class AnimationEvent {

	private float animationDuration;
	private Runnable fullRunnable;

	public AnimationEvent(float animationDuration, final Runnable animation, final Runnable eventLogic) {
		this.animationDuration = animationDuration;
		fullRunnable = new Runnable() {
			@Override
			public void run() {
				if (animation != null) {
					animation.run();
				}
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

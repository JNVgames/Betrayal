package com.jnv.betrayal.dungeon.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class AnimationQueue {

	private List<AnimationEvent> animations;
	private Actor actor;

	public AnimationQueue(Actor actor) {
		animations = new ArrayList<AnimationEvent>();
		this.actor = actor;
	}

	public void queueAnimation(AnimationEvent animation) {
		// Add animation to list
		animations.add(animation);
	}

	/**
	 * Loops through all the animations in order
	 * @return the total time it'll take to loop through all the animations
	 */
	public float startAnimations() {
		float delay = 0;
		// Loop through animations until the end
		for (AnimationEvent event : animations) {
			actor.addAction(Actions.delay(delay, Actions.run(event.getRunnable())));
			delay += event.getAnimationDuration() + 0.3f;
		}
		animations.clear();
		return delay;
	}
}

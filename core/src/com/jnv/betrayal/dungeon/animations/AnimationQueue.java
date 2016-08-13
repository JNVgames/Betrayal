package com.jnv.betrayal.dungeon.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;
import java.util.List;

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

	public void startAnimations() {
		float delay = 0;
		// Loop through animations until the end
		for (AnimationEvent event : animations) {
			actor.addAction(Actions.delay(delay, Actions.run(event.getRunnable())));
			delay += event.getAnimationDuration() + 0.3f;
			System.out.println("--delay = " + delay);
		}
		animations.clear();
	}
}

package com.jnv.betrayal.dungeon.animations;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.dungeon.turns.YourTurn;

import java.util.ArrayList;
import java.util.List;

public class Timeline {

	private List<TimelineEvent> animations;
	private Actor actor;
	private float totalDuration = YourTurn.INITIAL_DELAY;

	public Timeline(Actor actor) {
		animations = new ArrayList<TimelineEvent>();
		this.actor = actor;
	}

	public void addTimelineEvent(TimelineEvent animation) {
		// Add animation to list
		animations.add(animation);
	}

	/**
	 * Loops through all the animations in order
	 */
	public float start() {
		float delay = 0;
		// Loop through animations until the end
		for (TimelineEvent event : animations) {
			actor.addAction(Actions.delay(delay, Actions.run(event.getRunnable())));
			delay += event.getAnimationDuration() + 0.3f;
		}
		animations.clear();
		totalDuration = delay;
		return delay;
	}

	public float getTotalDuration() {
		return totalDuration;
	}
}

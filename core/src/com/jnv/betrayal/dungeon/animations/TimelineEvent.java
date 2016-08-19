package com.jnv.betrayal.dungeon.animations;

public class TimelineEvent {

	private float animationDuration;
	private Runnable fullRunnable, animation, eventLogic;

	public TimelineEvent(float animationDuration, final Runnable animation) {
		this(animationDuration, animation, null);
	}

	public TimelineEvent(float animationDuration, final Runnable animation, final Runnable eventLogic) {
		this.animationDuration = animationDuration;
		this.animation = animation;
		this.eventLogic = eventLogic;
		setFullRunnable();
	}

	public void setEventLogic(Runnable eventLogic) {
		this.eventLogic = eventLogic;
	}

	public float getAnimationDuration() {
		return animationDuration;
	}

	public Runnable getRunnable() {
		return fullRunnable;
	}

	private void setFullRunnable() {
		fullRunnable = new Runnable() {
			@Override
			public void run() {
				if (animation != null) animation.run();
				if (eventLogic != null) eventLogic.run();
			}
		};
	}
}

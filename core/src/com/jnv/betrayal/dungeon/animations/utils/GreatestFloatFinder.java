package com.jnv.betrayal.dungeon.animations.utils;


/**
 * Finds the Largest value from a set of values
 */
public class GreatestFloatFinder {

	private float greatestFloat;

	public GreatestFloatFinder() {
		reset();
	}

	public void reset() {
		greatestFloat = 0;
	}

	public void enterFloat(float value) {
		if (greatestFloat < value) {
			greatestFloat = value;
		}
	}

	public float highest() {
		return greatestFloat;
	}
}

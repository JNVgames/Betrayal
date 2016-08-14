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

	public void enterFloat(float... value) {
		for (float val : value) {
			if (greatestFloat < val) {
				greatestFloat = val;
			}
		}
	}

	public float highest() {
		return greatestFloat;
	}
}

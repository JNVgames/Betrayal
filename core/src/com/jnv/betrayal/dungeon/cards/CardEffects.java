/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CardEffects {

	public static void damaged(Actor target) {
		target.addAction(Actions.color(Color.RED));
		target.addAction(Actions.delay(0.1f, Actions.color(Color.WHITE)));
		target.addAction(Actions.delay(0.2f, Actions.color(Color.RED)));
		target.addAction(Actions.delay(0.3f, Actions.color(Color.WHITE)));
	}

	public static void freeze(Actor target) {
		target.addAction(Actions.color(
				new Color(64 / 255f, 112 / 255f, 236 / 255f, 1)
		));
	}

	public static void poison(Actor target) {
		target.addAction(Actions.color(
				new Color(30 / 255f, 153 / 255f, 73 / 255f, 1)
		));
	}
}

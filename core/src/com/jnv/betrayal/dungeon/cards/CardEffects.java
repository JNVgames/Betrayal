/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CardEffects {

	public static void damaged(Card card) {
		card.getCardImage().addAction(Actions.color(Color.RED));
		card.getCardImage().addAction(Actions.delay(0.1f, Actions.color(Color.WHITE)));
		card.getCardImage().addAction(Actions.delay(0.2f, Actions.color(Color.RED)));
		card.getCardImage().addAction(Actions.delay(0.3f, Actions.color(Color.WHITE)));
	}

	public static void freeze(Card card) {
		card.getCardImage().addAction(Actions.color(
				new Color(64 / 255f, 112 / 255f, 236 / 255f, 1)
		));
	}

	public static void poison(Card card) {
		card.getCardImage().addAction(Actions.color(
				new Color(30 / 255f, 153 / 255f, 73 / 255f, 1)
		));
	}

	public static void attack(Card card){

	}

	public static void defend(Card card ){

	}
}

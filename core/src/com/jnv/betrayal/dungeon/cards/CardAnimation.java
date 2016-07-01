/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CardAnimation {

	public static void damaged(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(Color.RED)));
		card.getCardImage().addAction(Actions.delay(0.6f, Actions.color(Color.WHITE)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.color(Color.RED)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
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

	public static void jump(Card card) {
		int multiplier = 1;
		if (card instanceof MonsterCard) {
			multiplier *= -1;
		}
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, 50 * multiplier, 0.2f)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.moveBy(0, -50 * multiplier, 0.2f)));
	}

	public static void defend(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(Color.GRAY)));
		card.getCardImage().addAction(Actions.delay(0.6f, Actions.color(Color.WHITE)));
	}

	public static void broBlock(Card src, Card dest) {
		src.getCardImage().addAction(Actions.delay(0.5f, Actions.moveTo(dest.getCardImage().getX(), dest.getCardImage().getTop())));
	}

	public static void useItem(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.rotateBy(-30, 1)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.rotateBy(30, 2)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.rotateBy(-30, 1)));
	}

	public static void fadeOut(Card card) {
		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
		card.healthBar.addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
	}
}

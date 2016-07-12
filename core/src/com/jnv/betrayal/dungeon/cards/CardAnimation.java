/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class CardAnimation {

	public static void flashColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.6f, Actions.color(Color.CLEAR)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
	}

	public static void longFlashColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
	}

	public static void changeColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
	}

	public static void removeColor(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(Color.WHITE)));
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

	public static void fadeOut(Card card) {
		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
		card.healthBar.addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
	}

	public static void flee(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.fadeOut(1.f)));
		card.healthBar.addAction(Actions.delay(.5f, Actions.fadeOut(1.f)));
	}

	public static void failToFlee(Card card) {
		flee(card);

		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
		card.healthBar.addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
}

	public static void heal(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(Color.YELLOW)));
		card.getCardImage().addAction(Actions.delay(0.6f, Actions.color(Color.WHITE)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.color(Color.YELLOW)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
	}

	public static void squishHorizontally(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.sizeTo(10, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(64, 0, 0.5f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.sizeTo(128, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.moveBy(-64, 0, 0.5f)));

	}

	public static void squishVertically(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.sizeTo(128, 10, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, 96, 0.2f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.sizeTo(128, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, -96, 0.2f)));
	}
}

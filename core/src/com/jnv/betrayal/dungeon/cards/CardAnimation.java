/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.ui.Image;

public class CardAnimation {

	private BetrayalAssetManager res;

	public CardAnimation(BetrayalAssetManager res) {
		this.res = res;
	}

	public void damaged(Card card) {
		// Display shield image
		Dimension cardDim = card.getGroup().getDimensions();
		final Image damaged = new Image(res.getTexture("damage"));
		damaged.setBounds(cardDim.getX() + cardDim.getWidth() / 2 - 50,
				cardDim.getY() + cardDim.getHeight() / 2 - 50, 100, 100);
		damaged.addAction(Actions.alpha(0));
		damaged.addAction(Actions.delay(0.1f, Actions.alpha(1f)));
		damaged.addAction(Actions.delay(0.2f, Actions.alpha(0)));
		damaged.addAction(Actions.delay(0.3f, Actions.alpha(1f)));
		damaged.addAction(Actions.delay(0.4f, Actions.alpha(0)));
		card.getField().getCardGroup().addActor(damaged);
		damaged.addAction(Actions.delay(1, Actions.run(new Runnable() {
			@Override
			public void run() {
				damaged.remove();
			}
		})));
	}

	public void flashColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.6f, Actions.color(Color.CLEAR)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
	}

	public void longFlashColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
		card.getCardImage().addAction(Actions.delay(0.8f, Actions.color(Color.WHITE)));
	}

	public void changeColor(Card card, Color color) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(color)));
	}

	public void removeColor(Card card) {
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.color(Color.WHITE)));
	}

	public void jump(Card card) {
		int multiplier = 1;
		if (card instanceof MonsterCard) {
			multiplier *= -1;
		}
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, 50 * multiplier, 0.2f)));
		card.getCardImage().addAction(Actions.delay(0.7f, Actions.moveBy(0, -50 * multiplier, 0.2f)));
	}

	public void defend(Card card) {
		// Display shield image
		Dimension cardDim = card.getGroup().getDimensions();
		final Image shield = new Image(res.getTexture("defense"));
		shield.setBounds(cardDim.getX() + cardDim.getWidth() / 2 - 50,
				cardDim.getY() + cardDim.getHeight() / 2 - 50, 100, 100);
		shield.addAction(Actions.alpha(0));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(1f)));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(shield);
		shield.addAction(Actions.delay(0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				shield.remove();
			}
		})));
	}

	public void fadeOut(Card card) {
		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
		card.healthBar.addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
	}

	public void flee(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.fadeOut(1.f)));
		card.healthBar.addAction(Actions.delay(.5f, Actions.fadeOut(1.f)));
	}

	public void failToFlee(Card card) {
		flee(card);

		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
		card.healthBar.addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
	}

	public void heal(Card card) {
		// Display shield image
		Dimension cardDim = card.getGroup().getDimensions();
		final Image healImg = new Image(res.getTexture("heal"));
		healImg.setBounds(cardDim.getX() + cardDim.getWidth() / 2 - 50,
				cardDim.getY() + cardDim.getHeight() / 2 - 50, 100, 100);
		healImg.addAction(Actions.alpha(0));
		healImg.addAction(Actions.alpha(0.7f, 1f));
		healImg.addAction(Actions.delay(1, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(healImg);
		healImg.addAction(Actions.delay(3, Actions.run(new Runnable() {
			@Override
			public void run() {
				healImg.remove();
			}
		})));
	}

	public void squishHorizontally(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.sizeTo(10, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(64, 0, 0.5f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.sizeTo(128, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.moveBy(-64, 0, 0.5f)));

	}

	public void squishVertically(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.sizeTo(128, 10, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, 96, 0.2f)));
		card.getCardImage().addAction(Actions.delay(1f, Actions.sizeTo(128, 192, .5f)));
		card.getCardImage().addAction(Actions.delay(0.5f, Actions.moveBy(0, -96, 0.2f)));
	}

	public void buffDefense(Card card) {
		// Display shield image
		Dimension cardDim = card.getGroup().getDimensions();
		final Image shield = new Image(res.getTexture("defense-boost"));
		shield.setBounds(cardDim.getX() + cardDim.getWidth() / 2 - 50,
				cardDim.getY() + cardDim.getHeight() / 2 - 50, 100, 100);
		shield.addAction(Actions.alpha(0));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(1f)));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(shield);
		shield.addAction(Actions.delay(0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				shield.remove();
			}
		})));
	}

	public void buffAttack(Card card) {
		// Display shield image
		Dimension cardDim = card.getGroup().getDimensions();
		final Image shield = new Image(res.getTexture("attack-boost"));
		shield.setBounds(cardDim.getX() + cardDim.getWidth() / 2 - 50,
				cardDim.getY() + cardDim.getHeight() / 2 - 50, 100, 100);
		shield.addAction(Actions.alpha(0));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(1f)));
		shield.addAction(Actions.delay(0.7f, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(shield);
		shield.addAction(Actions.delay(0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				shield.remove();
			}
		})));
	}
}

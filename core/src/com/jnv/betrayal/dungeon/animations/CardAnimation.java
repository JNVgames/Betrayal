package com.jnv.betrayal.dungeon.animations;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.dungeon.animations.utils.AnimationValues;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.ui.Image;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class CardAnimation {

	private BetrayalAssetManager res;

	public CardAnimation(BetrayalAssetManager res) {
		this.res = res;
	}

	public void damaged(Card card) {
		// Display shield image
		final Image damaged = new Image(res.getTexture("damage"));
		damaged.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
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
		final Image shield = new Image(res.getTexture("defense"));
		shield.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
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
		card.getHealthBar().addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
		card.getCardName().addAction(Actions.delay(1.5f, Actions.fadeOut(2f)));
	}

	public void flee(Card card) {
		fleeAnimation(card);
	}

	public void failToFlee(Card card) {
		fleeAnimation(card);

		card.getCardImage().addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
		card.getHealthBar().addAction(Actions.delay(1.5f, Actions.fadeIn(.5f)));
	}

	private void fleeAnimation(Card card) {
		card.getCardImage().addAction(Actions.delay(.5f, Actions.fadeOut(1f)));
		card.getHealthBar().addAction(Actions.delay(.5f, Actions.fadeOut(1f)));
	}

	public void heal(Card card) {
		// Display shield image
		final Image healImg = new Image(res.getTexture("heal"));
		healImg.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
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
		buffDefenseWithDelay(card, 0);

	}

	private void buffDefenseWithDelay(Card card, float delay) {
		// Display shield image
		final Image shield = new Image(res.getTexture("defense-boost"));
		shield.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
		shield.addAction(Actions.alpha(0));
		shield.addAction(Actions.delay(delay + 0.7f, Actions.alpha(1f)));
		shield.addAction(Actions.delay(delay + 0.7f, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(shield);
		shield.addAction(Actions.delay(delay + 0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				shield.remove();
			}
		})));
	}

	public void buffAttack(Card card) {
		buffAttackWithDelay(card, 0);
	}

	private void buffAttackWithDelay(Card card, float delay) {
		// Display shield image
		final Image shield = new Image(res.getTexture("attack-boost"));
		shield.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
		shield.addAction(Actions.alpha(0));
		shield.addAction(Actions.delay(delay + 0.7f, Actions.alpha(1f)));
		shield.addAction(Actions.delay(delay + 0.7f, Actions.alpha(0, 1)));
		card.getField().getCardGroup().addActor(shield);
		shield.addAction(Actions.delay(delay + 0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				shield.remove();
			}
		})));
	}

	public void buffAttackAndDefense(Card card) {
		buffAttackWithDelay(card, 0);
		buffDefenseWithDelay(card, 0.3f + AnimationValues.BUFF_ATTACK_DURATION);
	}

	public void debuffAttack(Card card) {
		debuffAttackWithDelay(card, 0);
	}

	private void debuffAttackWithDelay(final Card card, float delay) {
		// Display shield image
		final Image attackDebuff = new Image(res.getTexture("attack-debuff"));
		attackDebuff.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
		attackDebuff.addAction(Actions.alpha(0));
		attackDebuff.addAction(Actions.delay(delay + 0.7f, Actions.alpha(1f)));
		attackDebuff.addAction(Actions.delay(delay + 0.7f, new RunnableAction() {
			@Override
			public void run() {
				card.getStatusIcon().setDrawable(new TextureRegionDrawable(
						new TextureRegion(
								new TextureRegion(res.getTexture("attack-debuff"))
						)
				));
			}
		}));
		attackDebuff.addAction(Actions.delay(delay + 0.7f, Actions.alpha(0, 1)));
		attackDebuff.addAction(Actions.delay(delay + 0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				attackDebuff.remove();
			}
		})));
		card.getField().getCardGroup().addActor(attackDebuff);
	}

	public void debuffDefense(Card card) {
		debuffDefenseWithDelay(card, 0);
	}

	private void debuffDefenseWithDelay(final Card card, float delay) {
		// Display shield image
		final Image debuffDefense = new Image(res.getTexture("defense-debuff"));
		debuffDefense.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
		debuffDefense.addAction(Actions.alpha(0));
		debuffDefense.addAction(Actions.delay(delay + 0.7f, Actions.alpha(1f)));
		debuffDefense.addAction(Actions.delay(delay + 0.7f, new RunnableAction() {
			@Override
			public void run() {
				card.getStatusIcon().setDrawable(new TextureRegionDrawable(
						new TextureRegion(
								new TextureRegion(res.getTexture("defense-debuff"))
						)
				));
			}
		}));
		debuffDefense.addAction(Actions.delay(delay + 0.7f, Actions.alpha(0, 1)));
		debuffDefense.addAction(Actions.delay(delay + 0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				debuffDefense.remove();
			}
		})));
		card.getField().getCardGroup().addActor(debuffDefense);
	}

	public void debuffAttackDefense(Card card) {
		debuffAttackDefenseWithDelay(card, 0);
	}

	private void debuffAttackDefenseWithDelay(final Card card, float delay) {
		// Display shield image
		final Image attackDebuffDefense = new Image(res.getTexture("attack-defense-debuff"));
		attackDebuffDefense.setBounds(card.getGroup().getX() + card.getGroup().getWidth() / 2 - 50,
				card.getGroup().getY() + card.getGroup().getHeight() / 2 - 50, 100, 100);
		attackDebuffDefense.addAction(Actions.alpha(0));
		attackDebuffDefense.addAction(Actions.delay(delay + 0.7f, Actions.alpha(1f)));
		attackDebuffDefense.addAction(Actions.delay(delay + 0.7f, new RunnableAction() {
			@Override
			public void run() {
				card.getStatusIcon().setDrawable(new TextureRegionDrawable(
						new TextureRegion(
								new TextureRegion(res.getTexture("attack-defense-debuff"))
						)
				));
			}
		}));
		attackDebuffDefense.addAction(Actions.delay(delay + 0.7f, Actions.alpha(0, 1)));
		attackDebuffDefense.addAction(Actions.delay(delay + 0.7f + 1, Actions.run(new Runnable() {
			@Override
			public void run() {
				attackDebuffDefense.remove();
			}
		})));
		card.getField().getCardGroup().addActor(attackDebuffDefense);
	}

	public void attachBomb(final Card card) {
		addStatusIcon(card, res.getTexture("bomb"));
	}

	public void removeBomb(final Card card) {
		removeStatusIcon(card);
	}

	public void poison(final Card card) {
		addStatusIcon(card, res.getTexture("poison"));
	}

	public void removePoison(final Card card) {
		removeStatusIcon(card);
	}

	private void addStatusIcon(final Card card, final Texture texture) {
		card.getGroup().addAction(Actions.delay(0.7f, Actions.run(new Runnable() {
			@Override
			public void run() {
				card.getStatusIcon().setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
			}
		})));
	}

	private void removeStatusIcon(final Card card) {
		card.getGroup().addAction(Actions.run(new Runnable() {
			@Override
			public void run() {
				card.getStatusIcon().setDrawable(null);
			}
		}));
	}
}

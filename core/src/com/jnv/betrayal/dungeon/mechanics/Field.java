/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.mechanics;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.MonsterManager;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.ui.Image;

public class Field extends Group {

	private Image background;
	private PhaseManager phaseManager;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final SnapshotArray<PlayerCard> playerZone;
	public final SnapshotArray<MonsterCard> monsterZone;

	/**
	 * Creates an empty field that utilizes a stage for its actors
	 */
	public Field(GameStateManager gsm) {
		// Initialize card zones and instance variables
		playerZone = new SnapshotArray<PlayerCard>();
		monsterZone = new SnapshotArray<MonsterCard>();
		res = gsm.game.res;
		this.gsm = gsm;
		background = new Image(res.getTexture("map-1"));
		// Add things to stage
		addActor(background);
		// Load event log button
		float scale = 0.5f;
		Image eventLogButton = new Image(res.getTexture("event-log-button"));
		eventLogButton.layout();
		eventLogButton.setBounds(20, Betrayal.HEIGHT - 30 - 144 * scale, 512 * scale, 144 * scale);
		addActor(eventLogButton);
		phaseManager = new PhaseManager(this);
	}

	public void addCard(Card card) {
		if (card instanceof PlayerCard) playerZone.add((PlayerCard) card);
		else if (card instanceof MonsterCard) monsterZone.add((MonsterCard) card);
		else throw new AssertionError("Card is neither a PlayerCard or MonsterCard");
		addActor(card.getGroup());
		card.setField(this);
	}

	/**
	 * Activates select mode on all cards
	 */
	public void beginSelectMode(int numTargets) {
		playerZone.begin();
		monsterZone.begin();
		for (Card card : playerZone) {
			card.beginSelectMode(numTargets);
		}
		for (Card card : monsterZone) {
			card.beginSelectMode(numTargets);
		}
		playerZone.end();
		monsterZone.end();
	}

	/**
	 * Ends select mode on all cards
	 */
	public void endSelectMode() {
		playerZone.begin();
		monsterZone.begin();
		for (Card card : playerZone) {
			card.endSelectMode();
		}
		for (Card card : monsterZone) {
			card.endSelectMode();
		}
		playerZone.end();
		monsterZone.end();
	}

	/**
	 * Cancels select mode on all cards. Differs from Field.endSelectMode in that this doesn't save the user's
	 * decision in target selecting
	 */
	public void cancelSelectMode() {
		playerZone.begin();
		monsterZone.begin();
		for (Card card : playerZone) {
			card.cancelSelectMode();
		}
		for (Card card : monsterZone) {
			card.cancelSelectMode();
		}
		playerZone.end();
		monsterZone.end();
	}

	/**
	 * @return the amount of cards on the field selected
	 */
	public int getCardsSelected() {
		int amountSelected = 0;
		playerZone.begin();
		for (Card card : playerZone) {
			if (card.isSelected()) amountSelected++;
		}
		playerZone.end();
		monsterZone.begin();
		for (Card card : monsterZone) {
			if (card.isSelected()) amountSelected++;
		}
		monsterZone.end();
		return amountSelected;
	}

	public void unselectAll() {
		for (Card card : getAllCards()) {
			card.unselect();
		}
	}

	public void resetCards() {
		for (Card card : getAllCards()) {
			card.reset();
		}
	}

	public Array<Card> getAllCards() {
		Array<Card> allCards = new Array<Card>(playerZone);
		allCards.addAll(monsterZone);
		return allCards;
	}
}

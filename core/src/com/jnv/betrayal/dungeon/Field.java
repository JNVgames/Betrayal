/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.ui.Image;

public class Field extends Group {

	private Image background;
	private com.jnv.betrayal.dungeon.FieldUI ui;
	private MonsterManager monsterManager;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final SnapshotArray<com.jnv.betrayal.dungeon.PlayerCard> playerZone;
	public final SnapshotArray<com.jnv.betrayal.dungeon.MonsterCard> monsterZone;

	/**
	 * Creates an empty field that utilizes a stage for its actors
	 */
	public Field(GameStateManager gsm) {
		// Initialize card zones and instance variables
		playerZone = new SnapshotArray<com.jnv.betrayal.dungeon.PlayerCard>();
		monsterZone = new SnapshotArray<com.jnv.betrayal.dungeon.MonsterCard>();
		res = gsm.game.res;
		this.gsm = gsm;
		background = new Image(res.getTexture("map-1"));
		ui = new com.jnv.betrayal.dungeon.FieldUI(this);
		// Add things to stage
		addActor(background);
		addActor(ui);
		// Load event log button
		float scale = 0.5f;
		Image eventLogButton = new Image(res.getTexture("event-log-button"));
		eventLogButton.layout();
		eventLogButton.setBounds(20, Betrayal.HEIGHT - 30 - 144 * scale, 512 * scale, 144 * scale);
		addActor(eventLogButton);
		ui.start();
	}

	public void addCard(Card card) {
		if (card instanceof PlayerCard) playerZone.add((com.jnv.betrayal.dungeon.PlayerCard) card);
		else if (card instanceof MonsterCard) monsterZone.add((com.jnv.betrayal.dungeon.MonsterCard) card);
		else throw new AssertionError("Card is neither a PlayerCard or MonsterCard");
		addActor(card);
		card.setField(this);
	}

	/**
	 * Activates select mode on all cards
	 */
	public void beginSelectMode(int numTargets) {
		playerZone.begin();
		monsterZone.begin();
		for (com.jnv.betrayal.dungeon.Card card : playerZone) {
			card.beginSelectMode(numTargets);
		}
		for (com.jnv.betrayal.dungeon.Card card : monsterZone) {
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
		for (com.jnv.betrayal.dungeon.Card card : playerZone) {
			card.endSelectMode();
		}
		for (com.jnv.betrayal.dungeon.Card card : monsterZone) {
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
		for (com.jnv.betrayal.dungeon.Card card : playerZone) {
			card.cancelSelectMode();
		}
		for (com.jnv.betrayal.dungeon.Card card : monsterZone) {
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
		for (com.jnv.betrayal.dungeon.Card card : playerZone) {
			if (card.isSelected()) amountSelected++;
		}
		playerZone.end();
		monsterZone.begin();
		for (com.jnv.betrayal.dungeon.Card card : monsterZone) {
			if (card.isSelected()) amountSelected++;
		}
		monsterZone.end();
		return amountSelected;
	}

	public void unselectAll() {
		for (com.jnv.betrayal.dungeon.Card card : getAllCards()) {
			card.unselect();
		}
	}

	public Array<com.jnv.betrayal.dungeon.Card> getAllCards() {
		Array<com.jnv.betrayal.dungeon.Card> allCards = new Array<com.jnv.betrayal.dungeon.Card>(playerZone);
		allCards.addAll(monsterZone);
		return allCards;
	}
}

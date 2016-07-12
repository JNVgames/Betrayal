/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.mechanics;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.managers.RoundManager;
import com.jnv.betrayal.dungeon.managers.TurnManager;
import com.jnv.betrayal.dungeon.popup.EventLog;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class Field extends Group {

	public final RoundManager roundManager;
	public final TurnManager turnManager;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final Betrayal game;
	public final List<PlayerCard> playerZone;
	public final List<MonsterCard> monsterZone;
	List<Card> allCards;
	private int currentCardTurn;

	/**
	 * Creates an empty field that utilizes a stage for its actors
	 */
	public Field(GameStateManager gsm) {
		// Initialize card zones and instance variables
		playerZone = new ArrayList<PlayerCard>();
		monsterZone = new ArrayList<MonsterCard>();
		this.gsm = gsm;
		game = gsm.game;
		res = gsm.game.res;
		Image background = new Image(res.getTexture("map-1"));
		currentCardTurn = 0;
		allCards = new ArrayList<Card>();

		// Add things to stage
		addActor(background);

		// Load event log button
		float scale = 0.5f;
		Image eventLogButton = new Image(res.getTexture("event-log-button"));
		eventLogButton.layout();
		eventLogButton.setBounds(20, Betrayal.HEIGHT - 30 - 144 * scale, 512 * scale, 144 * scale);
		eventLogButton.addListener(new InputListener(eventLogButton) {
			@Override
			public void doAction() {
				new EventLog(game, roundManager.eventHistory);
			}
		});
		addActor(eventLogButton);
		turnManager = new TurnManager(this);
		roundManager = new RoundManager();
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
		for (Card card : playerZone) {
			card.beginSelectMode(numTargets);
		}
		for (Card card : monsterZone) {
			card.beginSelectMode(numTargets);
		}
	}

	public boolean isMonsterZoneEmpty() {
		return monsterZone.size() == 0;
	}

	public boolean checkCardsSelected(int cardsSelected) {
		return cardsSelected == getCardsSelected().size();
	}

	/**
	 * Ends select mode on all cards
	 */
	public void endSelectMode() {
		refreshAllCards();
		for (Card card : allCards) {
			card.endSelectMode();
			card.unselect();
		}
	}

	/**
	 * @return the array of cards on the field selected
	 */
	public List<Card> getCardsSelected() {
		List<Card> selectedCards = new ArrayList<Card>();
		refreshAllCards();
		for (Card card : allCards) {
			if (card.isSelected()) selectedCards.add(card);
		}
		return selectedCards;
	}

	public void unselectAll() {
		refreshAllCards();
		for (Card card : allCards) {
			card.unselect();
		}
	}

	public void resetCards() {
		for (Card card : getAllCards()) {
			card.reset();
		}
	}

	public void setNextCardIndex() {
		refreshAllCards();
		currentCardTurn = (currentCardTurn + 1) % getAllCards().size();
	}

	public Card getCurrentCard() {
		refreshAllCards();
		return allCards.get(currentCardTurn);
	}

	// refreshed all cards incase a card died.
	public void refreshAllCards() {
		allCards.clear();
		allCards.addAll(playerZone);
		allCards.addAll(monsterZone);
	}

	public List<Card> getAllCards() {
		refreshAllCards();
		return allCards;
	}

	public List<Card> getAllPlayerCards() {
		return new ArrayList<Card>(playerZone);
	}

	public List<Card> getAllMonsterCards() {
		return new ArrayList<Card>(monsterZone);
	}

	/**
	 * Player card death event
	 */
	public void removePlayerCard(PlayerCard card) {
		playerZone.remove(card);
		refreshAllCards();
	}

	/**
	 * Monster card death event
	 */
	public void removeMonsterCard(MonsterCard card) {
		monsterZone.remove(card);
		refreshAllCards();
	}
}

package com.jnv.betrayal.dungeon.managers;

import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.cards.Card;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class NextTurnManager {

	private Field field;
	private Card currentCard;
	private final List<Card> backUpCards;

	public NextTurnManager(Field field) {
		this.field = field;
		backUpCards = new ArrayList<Card>();
	}

	public void setup() {
		for (Card c : field.getAllCards()) backUpCards.add(c);
		currentCard = backUpCards.get(0);
	}

	public void nextTurn() {
		if (field.getAllCards().size() <= 1) {
			return;
		}
		Card nextCard;
		while (true) {
			for (int i = 0; i < backUpCards.size(); i++) {
				//Finds the Current Cards turn

				if (backUpCards.get(i) == currentCard) {
					int j = (i + 1) % backUpCards.size();

					//Looks for the next Cards turn, looping through and making sure
					//the card is still in the dungeon
					while (true) {
						if (field.getAllCards().contains(backUpCards.get(j))) {
							nextCard = backUpCards.get(j);
							break;
						}
						j = (j + 1) % backUpCards.size();
					}

					//sets the next card
					currentCard = nextCard;
					return;
				}
			}
		}
	}

	public Card getCurrentCard() {
		return currentCard;
	}
}

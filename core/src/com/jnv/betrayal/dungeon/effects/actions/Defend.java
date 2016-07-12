package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;

import java.util.List;

public class Defend extends Effect {

	public Defend(Card src, List<Card> dst) {
		super(EventType.ATTACK);
		isHostile = false;
		this.src = src;
		this.dest = dst;
	}

	@Override
	public void startEffect(Card card) {
		((PlayerCard) src).defendCard(card);
		card.addDefender((PlayerCard) getSrc());
	}

	@Override
	public void endEffect(Card card) {
		card.removeDefender(getSrc());
		((PlayerCard) src).defendCard(null);
	}

	@Override
	public void consistentEffect(Card card) {

	}
}

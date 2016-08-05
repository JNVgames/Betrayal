package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Defend extends Effect {

	public Defend(Card src, List<Card> dst) {
		super(EventType.DEFEND);
		turns = 1;
		isHostile = false;
		this.src = src;
		this.dest = dst;
	}

	// JSON Construction
	public Defend(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.DEFEND);
		isHostile = false;
		this.turns = turns;
		this.src = src;
		this.dest = dest;
	}

	@Override
	public void startEffect(Card card) {
		System.out.println("-------------DEFEND START EFFECT-------------");
		card.addDefender((PlayerCard) src);
		System.out.println("src = [" + src + "], dest = [" + dest + "]");
		System.out.println("-------------------------------------------");
	}

	@Override
	public void endEffect(Card card) {
		System.out.println("-------------DEFEND END EFFECT-------------");
		card.removeDefender(src.getID());
		System.out.println("src = [" + src + "], dest = [" + dest + "]");
		System.out.println("-------------------------------------------");
	}

	@Override
	public void consistentEffect(Card card) {

	}

	@Override
	protected void addToObject() {
		try {
			data.put("values", new JSONObject());
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

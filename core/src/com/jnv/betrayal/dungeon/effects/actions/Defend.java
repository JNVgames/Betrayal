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
		super(EventType.DEFEND, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		System.out.println("-------------DEFEND START EFFECT-------------");
		destCard.addDefender((PlayerCard) src);
		System.out.println("src = [" + src + "], dest = [" + dest + "]");
		System.out.println("-------------------------------------------");
	}

	@Override
	public void endEffect(Card destCard) {
		System.out.println("-------------DEFEND END EFFECT-------------");
		destCard.removeDefender(src.getID());
		System.out.println("src = [" + src + "], dest = [" + dest + "]");
		System.out.println("-------------------------------------------");
	}

	@Override
	public void consistentEffect(Card destCard) {

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

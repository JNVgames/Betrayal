package com.jnv.betrayal.dungeon.effects.actions;


import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Attack extends Effect {

	private int attack;

	public Attack(Card src, List<Card> dest) {
		super(EventType.ATTACK);
		isHostile = false;
		attack = src.getCurrentAttack();
		this.src = src;
		this.dest = dest;
	}

	// JSON Constructor
	public Attack(JSONObject values, int turns, Card src, List<Card> dest) {
		super(EventType.ATTACK, turns);
		isHostile = false;
		attack = src.getCurrentAttack();
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		destCard.attack(attack);
		System.out.println("attack!!!!!");
		System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
	}
}

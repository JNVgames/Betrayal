package com.jnv.betrayal.dungeon.effects.actions;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.dungeon.animations.utils.AnimationValues;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Flee extends Effect {

	private static final EventType startEventType = EventType.FLEE;

	public Flee(Card src) {
		super(startEventType);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	// JSON Constructor
	public Flee(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns);
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		for (int i = 0; i < src.getField().playerZone.size(); i++) {
			if (src.getField().playerZone.get(i).getID() == src.getID()) {
				src.getField().addAction(Actions.delay(AnimationValues.FLEE_DURATION, Actions.run(new Runnable() {
					@Override
					public void run() {
						src.getField().removePlayerCard((PlayerCard) src);
					}
				})));
			}
		}
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}
}

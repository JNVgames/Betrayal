package com.jnv.betrayal.dungeon.effects.actions;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jnv.betrayal.dungeon.animations.utils.AnimationValues;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Flee extends Effect {

	public Flee(Card src) {
		super(EventType.FLEE);
		isHostile = false;
		this.src = src;
		dest = new ArrayList<Card>();
		dest.add(src);
	}

	// JSON Constructor
	public Flee(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.FLEE, turns);
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
						src.getField().calibrateCurrentCardTurnIndex();
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

	@Override
	protected void addToObject() {
		try {
			data.put("class", getClass().getCanonicalName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}

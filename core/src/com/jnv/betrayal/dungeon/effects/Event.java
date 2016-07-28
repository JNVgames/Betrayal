package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Event {

	private Effect effect;
	private int turnsLeft;
	private EventType eventType;

	public Event(Effect effect, EventType eventType) {
		this.effect = effect;
		turnsLeft = effect.getTurns();
		this.eventType = eventType;
	}

	public boolean turnIsZero() {
		return turnsLeft == 0;
	}

	public void decreaseTurns() {
		turnsLeft--;
	}

	public Effect getEffect() {
		return effect;
	}

	public Card getSrc() {
		return effect.getSrc();
	}

	public List<Card> getDest() {
		return effect.getDest();
	}

	@Override
	public String toString() {
		String event = "";
		event += effect.getSrc().getName();
		event += " " + eventType.getActionString() + " ";
		if (effect.getDest().size() > 0) {
			for (int i = 0; i < effect.getDest().size(); i++) {
				event += effect.getDest().get(i).getName();
				if (i < effect.getDest().size() - 1)
					event += ", ";
			}
		}
		return event;
	}

	public JSONObject toJSON() {
		JSONObject data = new JSONObject();
		try {
			data.put("turnsLeft", turnsLeft);
			data.put("effect", effect.toJSON());
			data.put("eventType", eventType.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;
	}
}

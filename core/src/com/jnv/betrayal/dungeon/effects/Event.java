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

	public boolean effectEnded() {
		return turnsLeft <= 0;
	}

	public void decreaseTurns() {
		turnsLeft--;
	}

	public int getTurnsLeft() {
		return turnsLeft;
	}

	public Effect getEffect() {
		return effect;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Card getSrc() {
		return effect.getSrc();
	}

	public List<Card> getDest() {
		return effect.getDest();
	}

	public String toEventLogString() {
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

	@Override
	public String toString() {
		return "Event{" +
				"src=" + effect.src.getName() +
				", eventType=" + eventType +
				", turnsLeft=" + turnsLeft +
				'}';
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

package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.utils.EventLogStringGenerator;

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
		return EventLogStringGenerator.generate(getSrc(), getDest(), eventType);
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

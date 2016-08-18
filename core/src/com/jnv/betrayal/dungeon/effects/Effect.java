package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public abstract class Effect {

	protected Card src;
	protected List<Card> dest;
	protected boolean consistent;
	protected int turns = 1;
	protected final EventType startType, consistentType, endType;
	protected boolean isHostile;
	protected String description;
	protected JSONObject data;
	protected JSONArray destData;

	protected Effect(EventType startType) {
		this(startType, false, EventType.NONE, 0, EventType.NONE);
	}

	protected Effect(EventType startType, int turns) {
		this(startType, false, EventType.NONE, turns, EventType.NONE);
	}

	protected Effect(EventType startType, int turns, EventType endType) {
		this(startType, false, EventType.NONE, turns, endType);
	}

	protected Effect(EventType startType, int turns, boolean consistent, EventType consistentType,
					 EventType endType) {
		this(startType, consistent, consistentType, turns, endType);
	}

	protected Effect(EventType startType, boolean consistent, EventType consistentType, int turns,
					 EventType endType) {
		this.startType = startType;
		this.consistentType = consistentType;
		this.endType = endType;
		this.consistent = consistent;
		this.turns = turns;
	}

	protected void init(Card src, List<Card> dest) {
		this.src = src;
		this.dest = dest;
	}

	public boolean isHostile() {
		return isHostile;
	}

	public int getTurns() {
		return turns;
	}

	public EventType getStartType() {
		return startType;
	}

	public EventType getConsistentType() {
		return consistentType;
	}

	public EventType getEndType() {
		return endType;
	}

	public Card getSrc() {
		return src;
	}

	public List<Card> getDest() {
		return dest;
	}

	public String getDescription() {
		return description;
	}

	public void doStartEffect() {
		for (Card card : dest) {
			startEffect(card);
		}
	}

	public void doEndEffect() {
		for (Card card : dest) {
			endEffect(card);
		}
	}

	public void doConsistentEffect() {
		for (Card card : dest) {
			consistentEffect(card);
		}
	}

	public boolean isConsistent() {
		return consistent;
	}

	public void setConsistent(boolean consistent) {
		this.consistent = consistent;
	}

	public void setSrc(Card src) {
		this.src = src;
	}

	public void setDest(List<Card> dest) {
		this.dest = dest;
	}

	public abstract void startEffect(Card destCard);

	public abstract void consistentEffect(Card destCard);

	public abstract void endEffect(Card destCard);

	public JSONObject toJSON() {
		data = new JSONObject();
		destData = new JSONArray();
		if (dest != null)
			for (Card card : dest) {
				destData.put(card.getID());
			}
		try {
			data.put("src", src.getID());
			data.put("dest", destData);
			data.put("consistent", consistent);
			data.put("turns", turns);
			data.put("startType", startType.toString());
			data.put("consistentType", consistentType.toString());
			data.put("endType", endType.toString());
			data.put("isHostile", isHostile);
			data.put("description", description);
			data.put("class", getClass().getCanonicalName());
			data.put("values", new JSONObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		addToObject();

		return data;
	}

	@Override
	public String toString() {
		return "Effect{" +
				"\n\tsrc=" + src +
				",\n\tdest=" + dest +
				",\n\tstartType=" + startType +
				",\n\tconsistentType=" + consistentType +
				",\n\tendType=" + endType +
				"}";
	}

	protected void addToObject() {
		// Use this method to add extra values to json
	}
}

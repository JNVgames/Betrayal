//package com.jnv.betrayal.dungeon.actions;
//
//
//import com.jnv.betrayal.dungeon.cards.Card;
//
//import java.util.List;
//
//public class Action {
//	private Card src;
//	private List<Card> dest;
//	private EventType eventType;
//
//	public Action(Card src, EventType eventType) {
//		this.src = src;
//		this.eventType = eventType;
//	}
//
//	public Action(Card src, List<Card> dest, EventType eventType) {
//		this(src, eventType);
//		this.dest = dest;
//	}
//
//	public Card getSrc() {
//		return src;
//	}
//
//	public List<Card> getDest() {
//		return dest;
//	}
//
//	public EventType getEventType() {
//		return eventType;
//	}
//
//	public boolean destExist() {
//		return dest != null && dest.size() != 0;
//	}
//
//	@Override
//	public String toString() {
//		String event = "";
//		event += src.getName();
//		event += " " + eventType.toString() + " ";
//		if (destExist()) {
//			for (int i = 0; i < dest.size(); i++) {
//				event += dest.get(i).getName();
//				if (i < dest.size() - 1)
//					event += ", ";
//			}
//		}
//		return event;
//	}
//}

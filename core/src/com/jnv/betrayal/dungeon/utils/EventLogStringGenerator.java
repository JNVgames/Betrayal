package com.jnv.betrayal.dungeon.utils;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.EventType;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class EventLogStringGenerator {

	public static String generate(Card src, List<Card> dest, EventType eventType) {
		String generated = "";
		if (eventType.showDestFirst()) {
			generated += generateDestString(dest, eventType);
			generated += " " + eventType.getActionString() + " ";
			if (eventType.showSrc()) {
				generated += generateSrcString(src);
			}
		} else {
			if (eventType.showSrc()) {
				generated += generateSrcString(src);
			}
			generated += " " + eventType.getActionString() + " ";
			generated += generateDestString(dest, eventType);
		}
		return generated;
	}

	private static String generateSrcString(Card src) {
		return src.getName();
	}

	private static String generateDestString(List<Card> dest, EventType eventType) {
		String string = "";
		if (eventType.showDestination() && dest.size() > 0) {
			for (int i = 0; i < dest.size(); i++) {
				string += dest.get(i).getName();
				if (i < dest.size() - 1)
					string += ", ";
			}
		}
		return string;
	}
}

/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A list that represents a last in first out
 * @param <E>
 */
public class Queue<E> {

	private List<E> items;

	/**
	 * Create an empty queue
	 */
	public Queue() {
		items = new ArrayList<E>();
	}

	public void queue(E item) {
		items.add(item);
	}

	public E dequeue() {
		return items.remove(0);
	}

	public int size() {
		return items.size();
	}
}

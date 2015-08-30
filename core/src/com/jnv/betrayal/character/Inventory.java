/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private BetrayalAssetManager res;
	private int gold, items_max;
	private List<Item> items;

	public Inventory(BetrayalAssetManager res) {
		this.res = res;
		gold = 0;
		items_max = 30;

		items = new ArrayList<Item>();
	}

	// Getters
	public int getGold() {
		return gold;
	}

	public Item[][] getItems(int rows, int cols) {
		Item[][] itemArray = new Item[rows][cols];
		int counter = 0;
		for (int row = 0; row < itemArray.length; row++) {
			for (int col = 0; col < itemArray[row].length; col++) {
				if (counter < items.size()) itemArray[row][col] = items.get(counter++);
			}
		}
		return itemArray;
	}

	public boolean isFull() {
		return items.size() == items_max;
	}

	// Setters

	/**
	 * Adds the specific amount of items to inventory and return true.
	 * If inventory is filled, do nothing and return false.
	 *
	 * @param item   item to be added
	 * @param amount how many items to be added
	 */
	public void addItem(Item item, int amount) {
		for (int i = 0; i < amount; i++) if (items.size() < items_max) items.add(item);
	}

	public void addItem(Item item) {
		addItem(item, 1);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}

	/**
	 * Sorts the inventory
	 */
	public void sortItems() {
		if (items != null) {
			items.sort(new Item.ItemComparator());
		}
	}

	/**
	 * Adds specified amount of gold to inventory
	 *
	 * @param amount amount of gold to be added
	 */
	public void addGold(int amount) {
		gold += amount;
	}

}

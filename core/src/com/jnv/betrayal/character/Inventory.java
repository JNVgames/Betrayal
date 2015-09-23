/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.gameobjects.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a character's inventory
 */
public class Inventory implements Json.Serializable {

	private int gold, items_max;
	private List<Item> items;

	public Inventory() {
		gold = 0;
		items_max = 30;

		items = new ArrayList<Item>();
	}

	/**
	 * Buys the requested item
	 * @param item item the character is trying to buy
	 * @return true if character has enough gold, false if not
	 */
	public boolean buyItem(Item item) {
		// if character doesn't have enough gold to buy item
		if (gold < item.getBuyCost()) {
			// fail to buy and return false
			return false;
		}
		// if character has enough gold and return true
		else { // if (gold >= item.getBuyCost()) {
			gold -= item.getBuyCost();
			addItem(item);
			return true;
		}
	}

	/**
	 * Sells the requested item
	 * @param item item to be sold
	 * @throws AssertionError if item isn't in the inventory
	 */
	public void sellItem(Item item) {
		// if item isn't in the inventory, crash the game
		if (!items.contains(item))
			throw new AssertionError("Item does not exist in the character's inventory: " + item.getName());
		items.remove(item);
		gold += item.getSellCost();
	}

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

	/**
	 * Removes and does not sell item from inventory
	 * @param item to remove
	 */
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

	// Getters
	public int getGold() {
		return gold;
	}

	/**
	 * Returns items in a 2D array. *Used for inventory in lobby
	 * @param rows in inventory
	 * @param cols in inventory
	 * @return 2D array of items
	 */
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

	/**
	 * Adds specified amount of gold to inventory
	 *
	 * @param amount amount of gold to be added
	 */
	public void addGold(int amount) {
		gold += amount;
	}

	public void write(Json json) {
		json.writeObjectStart("inventory");
		json.writeField(this, "gold", Integer.class);
		json.writeField(this, "items", List.class);
		json.writeObjectEnd();
	}

	public void read(Json json, JsonValue jsonData) {

	}
}

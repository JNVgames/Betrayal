/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.gameobjects.Item;
import com.jnv.betrayal.gameobjects.Usables;
import com.jnv.betrayal.online.JsonSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a character's inventory
 */
public class Inventory {

	private int gold, maxItems;
	private List<Item> items;

	public Inventory() {
		gold = 10000;
		maxItems = 20;

		items = new ArrayList<Item>();
	}

	/**
	 * Buys the requested item
	 *
	 * @param item item the character is trying to buy
	 * @return 0 - success
	 * 1 - not enough gold
	 * 2 - inventory full
	 */
	public int buyItem(Item item) {
		// if character doesn't have enough gold to buy item
		if (gold < item.getBuyCost()) {
			// fail to buy and return false
			return 1;
		}
		// if character has enough gold and return true
		else { // if (gold >= item.getBuyCost()) {
			gold -= item.getBuyCost();
			if (!addItem(item)) {
				return 2;
			}
			return 0;
		}
	}

	/**
	 * Sells the requested item
	 *
	 * @param item item to be sold
	 * @throws AssertionError if item isn't in the inventory
	 */
	public void sellItem(Item item) {
		// if item isn't in the inventory, crash the game
		if (!items.contains(item))
			throw new AssertionError("Item does not exist in the character's inventory: " + item.getTextureName());
		items.remove(item);
		gold += item.getSellCost();
	}

	/**
	 * Adds the specific amount of items to inventory and return true.
	 * If inventory is filled, do nothing and return false.
	 *
	 * @param item item to be added
	 */
	public boolean addItem(Item item) {
		if (items.size() < maxItems) {
			items.add(item);
			return true;
		}
		return false;
	}

	/**
	 * Removes and does not sell item from inventory
	 *
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

	public void addGold(int amount){
		gold +=amount;
	}

	/**
	 * Returns items in a 2D array. *Used for inventory in lobby
	 *
	 * @param rows in inventory
	 * @param cols in inventory
	 * @return 2D array of items
	 */
	public Item[][] getItems(int rows, int cols, boolean loadEquips) {
		Item[][] itemArray = new Item[rows][cols];
		int counter = 0;
		outer : for (int row = 0; row < itemArray.length; row++) {
			for (int col = 0; col < itemArray[row].length; col++) {
				while (!(counter < items.size() && (loadEquips || items.get(counter) instanceof Usables))) {
					counter++;
					if (counter >= items.size()) break outer;
				}
				itemArray[row][col] = items.get(counter);
				counter++;
			}
		}
		return itemArray;
	}

	public boolean isFull() {
		return items.size() == maxItems;
	}
}

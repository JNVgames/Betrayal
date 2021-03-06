package com.jnv.betrayal.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.utils.EquipSlot;
import com.jnv.betrayal.online.JsonSerializable;
import com.jnv.betrayal.resources.BetrayalAssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.util.Comparator;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public abstract class Item {

	protected BetrayalAssetManager res;
	protected Texture itemIcon;
	protected String textureName, description;
	protected int id, buyCost, sellCost;
	protected boolean isEquippable = false;

	public Item(int id, String textureName, BetrayalAssetManager res, int buyCost, String description) {
		this.res = res;
		this.id = id;
		this.textureName = textureName;
		itemIcon = res.getTexture(textureName);
		this.buyCost = buyCost;
		sellCost = buyCost / 2;
		this.description = description;
		res.loadItem(textureName, this);

	}

	public Item(String textureName, BetrayalAssetManager res) {
		Item src = res.getItem(textureName);
		id = src.getID();
		this.textureName = textureName;
		description = src.getDescription();
		buyCost = src.getBuyCost();
		sellCost = src.getSellCost();
		itemIcon = src.getItemIcon();
		isEquippable = src.isEquippable();
	}

	/**
	 * Compares two items by id and returns -1 if the first item is less than
	 * the second item, 0 if they're equal, and 1 if the first is greater than the second
	 *
	 * @param item1 first item to compare
	 * @param item2 second item to compare
	 * @return the value 0 if item1 == item2
	 * the value -1 if item1 < item2
	 * the value 1 if item1 > item2
	 */
	public static int compare(Item item1, Item item2) {
		int i1 = item1.getID();
		int i2 = item2.getID();
		return (i1 < i2 ? -1 : ((i1 == i2) ? 0 : 1));
	}

	// Getters
	public boolean isEquippable() {
		return isEquippable;
	}

	public int getID() {
		return id;
	}

	public String getTextureName() {
		return textureName;
	}

	// Setters
	public void setName(String name) {
		textureName = name;
	}

	public Texture getItemIcon() {
		return itemIcon;
	}

	public int getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(int new_cost) {
		buyCost = new_cost;
	}

	public int getSellCost() {
		return sellCost;
	}

	public void setSellCost(int new_cost) {
		sellCost = new_cost;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Compares the current item to the specified item by id
	 *
	 * @param item item to be compared to the current item
	 * @return the value 0 if this item == item
	 * the value -1 if this item < item
	 * the value 1 if this item > item
	 */
	public int compareTo(Item item) {
		return this.id < item.getID() ? -1 : ((this.id == item.getID()) ? 0 : 1);
	}

	public static class ItemComparator implements Comparator<Item> {
		public int compare(Item o1, Item o2) {
			int item1 = o1.getID();
			int item2 = o2.getID();
			return item1 - item2;
		}
	}


}

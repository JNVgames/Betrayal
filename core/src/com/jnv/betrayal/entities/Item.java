/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.main.Betrayal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Item {

    private static HashMap<String, Item> allItems = new HashMap<String, Item>();

    private TextureRegion itemImage;
    private String itemName;
    private int id, cost_buy, cost_sell, amount;
    private final int maxAmount = 10;

    public Item(int id, String name) {
        this.id = id;
        itemName = name;
        itemImage = new TextureRegion(Betrayal.res.getTexture(name));
        allItems.put(name, this);
    }
    public Item(String name, int amount) {
        Item src = allItems.get(name);
        id = src.getID();
        itemName = name;
        cost_buy = src.getBuyCost();
        cost_sell = src.getSellCost();
        itemImage = src.getItemImage();
        this.amount = amount;
    }

    public static class ItemComparator implements Comparator<Item> {
        public int compare(Item o1, Item o2) {
            int item1 = o1.getID();
            int item2 = o2.getID();
            return item1 - item2;
        }

    }

    // Getters
    public int getID() { return id; }
    public String getName() { return itemName; }
    public int getBuyCost() { return cost_buy; }
    public int getSellCost() { return cost_sell; }
    public TextureRegion getItemImage() {
        return itemImage;
    }
    public boolean isItemFull() { return amount == maxAmount; }

    // Setters
    public void setName(String name) { itemName = name; }

    /** Adds the specified amount of item to the item. If the item stack overfills,
     * the amount the stack overfills by is returned
     * @param amount amount to be added
     * @return if the stack of items is overfilled, the overfilled amount is returned */
    public int addAmount(int amount) {
        if (this.amount + amount > maxAmount) {
            int diff = this.amount + amount - maxAmount;
            this.amount = maxAmount;
            return diff;
        }
        this.amount += amount;
        return 0;
    }
    public static void setCosts(String name, int newBuyCost, int newSellCost) {
        Item src = allItems.get(name);
        src.setBuyCost(newBuyCost);
        src.setSellCost(newSellCost);
    }
    private void setBuyCost(int new_cost) { cost_buy = new_cost; }
    private void setSellCost(int new_cost) { cost_sell = new_cost; }

    /** Compares two items by id and returns -1 if the first item is less than
     * the second item, 0 if they're equal, and 1 if the first is greater than the second
     * @param item1 first item to compare
     * @param item2 second item to compare
     * @return the value 0 if item1 == item2
     *         the value -1 if item1 < item2
     *         the value 1 if item1 > item2 */
    public static int compare(Item item1, Item item2) {
        int i1 = item1.getID();
        int i2 = item2.getID();
        return (i1 < i2 ? -1 : ((i1 == i2) ? 0 : 1));
    }

    /** Compares the current item to the specified item by id
     * @param item item to be compared to the current item
     * @return the value 0 if this item == item
     *         the value -1 if this item < item
     *         the value 1 if this item > item */
    public int compareTo(Item item) {
        return this.id < item.getID() ? -1 : ((this.id == item.getID()) ? 0 : 1);
    }
}

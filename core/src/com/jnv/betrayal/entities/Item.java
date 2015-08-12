/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jnv.betrayal.main.Betrayal;

import java.util.Comparator;
import java.util.HashMap;

public class Item {

    protected static HashMap<String, Item> allItems = new HashMap<String, Item>();

    protected Texture itemImage;
    protected String itemName;
    protected int id, cost_buy, cost_sell;

    protected Item(int id, String name) {
        this.id = id;
        itemName = name;
        itemImage = Betrayal.res.getTexture(name);
        allItems.put(name, this);
    }
    protected Item(String name) {
        Item src = allItems.get(name);
        id = src.getID();
        itemName = name;
        cost_buy = src.getBuyCost();
        cost_sell = src.getSellCost();
        itemImage = src.getItemImage();
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
    public Texture getItemImage() {
        return itemImage;
    }

    // Setters
    public void setName(String name) { itemName = name; }
    public static void setCosts(String name, int newBuyCost, int newSellCost) {
        Item src = allItems.get(name);
        src.setBuyCost(newBuyCost);
        src.setSellCost(newSellCost);
    }
    public void setBuyCost(int new_cost) { cost_buy = new_cost; }
    public void setSellCost(int new_cost) { cost_sell = new_cost; }

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

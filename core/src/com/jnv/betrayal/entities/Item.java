package com.jnv.betrayal.entities;

import com.badlogic.gdx.graphics.Texture;
import com.jnv.betrayal.main.Betrayal;

import java.util.Comparator;

public class Item {

    private Texture itemImage;
    private int id, cost_buy, cost_sell;

    /** Creates an item with the specified id with other default values
     * @param id item id number */
    public Item(int id) {
        this.id = id;
        cost_buy = 1;
        cost_sell = 1;
        itemImage = Betrayal.res.getTexture("item-id-" + id);
    }
    /** Creates an item with the specified id and shop values
     * @param id item id number
     * @param cost_buy gold it costs to buy from the shop
     * @param cost_sell gold it takes to sell to the shop */
    public Item(int id, int cost_buy, int cost_sell) {
        this.id = id;
        this.cost_buy = cost_buy;
        this.cost_sell = cost_sell;
        itemImage = Betrayal.res.getTexture("item-id-" + id);
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
    public int getBuyCost() { return cost_buy; }
    public int getSellCost() { return cost_sell; }
    public Texture getItemImage() { return itemImage; }

    // Setters
    public void setBuyCost(int new_cost) { cost_buy = new_cost; }
    public void setSellCost(int new_cost) { cost_sell = new_cost; }
}

package com.jnv.betrayal.entities;

public class Weapon extends Item {

    public Weapon(int id) {
        super(id);
    }
    public Weapon(int id, int cost_buy, int cost_sell) {
        super(id, cost_buy, cost_sell);
    }
}

/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.characterhandlers;

public class CharacterStats {

    public enum Stat {
        FLOOR, HEALTH, DEFENSE, ATTACK
    }

    private int health, defense, attack, floor;

    public CharacterStats() {
        health = 25;
        defense = 5;
        attack = 5;
        floor = 1;
    }

    // Getters
    public int getStat(Stat stat) {
        switch (stat) {
            case FLOOR:
                return floor;
            case HEALTH:
                return health;
            case ATTACK:
                return attack;
            case DEFENSE:
                return defense;
            default:
                return -1;
        }
    }
    public String toString(Stat stat) {
        switch (stat) {
            case FLOOR:
                return "Floor: " + floor;
            case HEALTH:
                return "Health: " + health;
            case ATTACK:
                return "Attack: " + attack;
            case DEFENSE:
                return "Defense: " + defense;
            default:
                return null;
        }
    }

    // Setters
    public void advanceFloor() { floor++; }
    public void setFloor(int floor) { this.floor = floor; }
}

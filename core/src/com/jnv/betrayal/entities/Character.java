/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.jnv.betrayal.character.Inventory;
import com.jnv.betrayal.character.Job;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.character.Stats;
import com.jnv.betrayal.character.Equips;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information regarding a game character's traits
 * @author Vincent Wang
 */
public class Character {

    /* If array this was private, a getter would get this array and other classes would
     * still be able to edit it. Either way, this array will function as a public array */
    public static List<Character> characters = new ArrayList<Character>();
    public static Character currentCharacter;

    public enum Trait {
        GENDER,
        HAIR_STYLE,
        HAIR_COLOR,
        JOB
    }

    private String name;

    public Preview preview;
    public Job job;
    public Equips equips;
    public Inventory inventory;
    public Stats stats;

    /** Creates character with default values */
    public Character() {
        equips = new Equips(this);
        preview = new Preview(this);
        job = new Job();
        inventory = new Inventory();
        stats = new Stats();

        job.setJob(Job.Jobs.WARRIOR);
        update();
    }

    public void createCharacter(Job.Jobs job) {
        this.job.setJob(job);
    }
    private void update() {
        preview.update();
    }
    public void saveInfo() {

    }

    // Getters
    public String getName() { return name; }
    public String getJob(Job.Jobs job) {
        switch (job) {
            case WARRIOR:
                return "W";
            case KNIGHT:
                return "K";
            case PRIEST:
                return "P";
            case THIEF:
                return "T";
            default:
                return null;
        }
    }

    // Setters
    public void setName(String name) { this.name = name; }
}
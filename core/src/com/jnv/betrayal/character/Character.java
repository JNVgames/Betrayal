/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

import com.jnv.betrayal.character.Inventory;
import com.jnv.betrayal.character.Job;
import com.jnv.betrayal.character.Preview;
import com.jnv.betrayal.character.Stats;
import com.jnv.betrayal.character.Equips;
import com.jnv.betrayal.resources.BetrayalAssetManager;

/**
 * Holds information regarding a game character's traits
 * @author Vincent Wang
 */
public class Character {

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
    public Character(BetrayalAssetManager res) {
        equips = new Equips(this, res);
        preview = new Preview(this, res);
        job = new Job();
        inventory = new Inventory(res);
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

    public Preview getPreview() {
        return this.preview;
    }

    // Setters
    public void setName(String name) { this.name = name; }
}
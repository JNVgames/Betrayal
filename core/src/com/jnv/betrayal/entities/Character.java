/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.entities;

import com.jnv.betrayal.characterhandlers.CharacterInventory;
import com.jnv.betrayal.characterhandlers.CharacterJob;
import com.jnv.betrayal.characterhandlers.CharacterPreview;
import com.jnv.betrayal.characterhandlers.CharacterStats;
import com.jnv.betrayal.characterhandlers.CharacterEquips;

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

    public CharacterPreview preview;
    public CharacterJob job;
    public CharacterEquips equips;
    public CharacterInventory inventory;
    public CharacterStats stats;

    /** Creates character with default values */
    public Character() {
        equips = new CharacterEquips(this);
        preview = new CharacterPreview(this);
        job = new CharacterJob();
        inventory = new CharacterInventory();
        stats = new CharacterStats();

        job.setJob(CharacterJob.Jobs.WARRIOR);
        update();
    }

    public void createCharacter(CharacterJob.Jobs job) {
        this.job.setJob(job);
    }
    private void update() {
        preview.update();
    }
    public void saveInfo() {

    }

    // Getters
    public String getName() { return name; }
    public String getJob(CharacterJob.Jobs job) {
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
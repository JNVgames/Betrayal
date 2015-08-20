/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.character;

public class Job {

    public enum Jobs {
        WARRIOR, THIEF, KNIGHT, PRIEST
    }
    private Jobs job;

    public Job() {}
    public Job(Jobs job) {
        this.job = job;
    }

    // Getters
    public Jobs getJob() { return job; }
    public String toString() {
        switch (job) {
            case WARRIOR:
                return "Warrior";
            case KNIGHT:
                return "Knight";
            case PRIEST:
                return "Priest";
            case THIEF:
                return "Thief";
            default:
                return null;
        }
    }

    // Setters
    public void setJob(Jobs job) {
        this.job = job;
    }
    public void setPreviousJob() {
        switch (job) {
            case WARRIOR:
                this.job = Jobs.THIEF;
                break;
            case KNIGHT:
                this.job = Jobs.WARRIOR;
                break;
            case PRIEST:
                this.job = Jobs.KNIGHT;
                break;
            case THIEF:
                this.job = Jobs.PRIEST;
                break;
            default:
                break;
        }
    }
    public void setNextJob() {
        switch (job) {
            case WARRIOR:
                this.job = Jobs.KNIGHT;
                break;
            case KNIGHT:
                this.job = Jobs.PRIEST;
                break;
            case PRIEST:
                this.job = Jobs.THIEF;
                break;
            case THIEF:
                this.job = Jobs.WARRIOR;
                break;
            default:
                break;
        }
    }
}
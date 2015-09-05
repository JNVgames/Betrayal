package com.jnv.betrayal.resources;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class MonsterConstants {
    // Tier 0
    public static final Dimensions[] tier0Monsters = {
            new Dimensions(250, 250),
    };
    public static final Vector2[] tier0MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier0Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100)
    };

    // Tier 1
    public static final Dimensions[] tier1Monsters = {
            new Dimensions(350,400),
            new Dimensions(300,350),
            new Dimensions(250,300),
            new Dimensions(450,400),
            new Dimensions(300,350),
            new Dimensions(300,350),
            new Dimensions(300,350),
            new Dimensions(300,350),
            new Dimensions(300,350),
            new Dimensions(300,350),
    };
    public static final Vector2[] tier1MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier1Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[7].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier1Monsters[9].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
    };

    //Tier 2
    public static final Dimensions[] tier2Monsters = {
            new Dimensions(400, 450),
            new Dimensions(350, 450),
            new Dimensions(450, 450),
            new Dimensions(500, 450),
            new Dimensions(650, 550),
            new Dimensions(500, 500),
            new Dimensions(500, 500),
            new Dimensions(400, 600),
            new Dimensions(400, 500)
    };
    public static final Vector2[] tier2MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier2Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier2Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier2Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier2Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 75),
            new Vector2((Betrayal.WIDTH - tier2Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
            new Vector2((Betrayal.WIDTH - tier2Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
            new Vector2((Betrayal.WIDTH - tier2Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 ),
            new Vector2((Betrayal.WIDTH - tier2Monsters[7].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
            new Vector2((Betrayal.WIDTH - tier2Monsters[8].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
    };

    //Tier 3
    public static final Dimensions[] tier3Monsters = {
            new Dimensions(450, 400),
            new Dimensions(600, 500),
            new Dimensions(400, 450),
            new Dimensions(400, 450),
            new Dimensions(550, 500),  //TODO:Change this guy
            new Dimensions(550, 550),
            new Dimensions(500, 500),
    };
    public static final Vector2[] tier3MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier3Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 75),
            new Vector2((Betrayal.WIDTH - tier3Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 ),
            new Vector2((Betrayal.WIDTH - tier3Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
            new Vector2((Betrayal.WIDTH - tier3Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier3Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),
            new Vector2((Betrayal.WIDTH - tier3Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier3Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),
    };

    // Tier 4
    public static final Dimensions[] tier4Monsters = {
            new Dimensions(550, 550),
            new Dimensions(350, 400),
            new Dimensions(550, 550),
            new Dimensions(550, 550),
            new Dimensions(550, 550),
    };
    public static final Vector2[] tier4MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier4Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier4Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier4Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier4Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier4Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 25)
    };

    // Tier 5
    public static final Dimensions[] tier5Monsters = {
            new Dimensions(650, 550),
            new Dimensions(650, 850),
            new Dimensions(720, 600),
            new Dimensions(550, 550),
            new Dimensions(550, 550),
            new Dimensions(650, 650)
    };
    public static final Vector2[] tier5MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier5Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier5Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 - 100),
            new Vector2((Betrayal.WIDTH - tier5Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 ),
            new Vector2((Betrayal.WIDTH - tier5Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier5Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),
            new Vector2((Betrayal.WIDTH - tier5Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 25)
    };
}

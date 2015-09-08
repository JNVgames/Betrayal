package com.jnv.betrayal.resources;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class MonsterConstants {
    // Tier 0
    public static final Dimensions[] tier0Monsters = {
            new Dimensions(250, 250),    //Monster ID:0
    };
    public static final Vector2[] tier0MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier0Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100)    //Monster ID:0
    };

    // Tier 1
    public static final Dimensions[] tier1Monsters = {
            new Dimensions(350,400),    //Monster ID:0
            new Dimensions(300,350),    //Monster ID:1
            new Dimensions(250,300),    //Monster ID:2
            new Dimensions(450,400),    //Monster ID:3
            new Dimensions(300,350),    //Monster ID:4
            new Dimensions(300,350),    //Monster ID:5
            new Dimensions(300,350),    //Monster ID:6
            new Dimensions(300,350),    //Monster ID:7
            new Dimensions(300,350),    //Monster ID:8
            new Dimensions(300,350),    //Monster ID:9
            new Dimensions(10,10),      //Monster ID:10
            new Dimensions(10,10),      //Monster ID:11
            new Dimensions(10,10),      //Monster ID:12
            new Dimensions(10,10),      //Monster ID:13
            new Dimensions(10,10),      //Monster ID:14
            new Dimensions(10,10),      //Monster ID:15
            new Dimensions(10,10),      //Monster ID:16
            new Dimensions(10,10),      //Monster ID:17
            new Dimensions(10,10),      //Monster ID:18
            new Dimensions(300,200),      //Monster ID:19
    };
    public static final Vector2[] tier1MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier1Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:0
            new Vector2((Betrayal.WIDTH - tier1Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:1
            new Vector2((Betrayal.WIDTH - tier1Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:2
            new Vector2((Betrayal.WIDTH - tier1Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:3
            new Vector2((Betrayal.WIDTH - tier1Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:4
            new Vector2((Betrayal.WIDTH - tier1Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:5
            new Vector2((Betrayal.WIDTH - tier1Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:6
            new Vector2((Betrayal.WIDTH - tier1Monsters[7].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:7
            new Vector2((Betrayal.WIDTH - tier1Monsters[8].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),     //Monster ID:8
            new Vector2((Betrayal.WIDTH - tier1Monsters[9].getWidth()) / 2-100, Betrayal.HEIGHT/2 + 100),     //Monster ID:9
            new Vector2((Betrayal.WIDTH - tier1Monsters[10].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:10
            new Vector2((Betrayal.WIDTH - tier1Monsters[11].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:11
            new Vector2((Betrayal.WIDTH - tier1Monsters[12].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:12
            new Vector2((Betrayal.WIDTH - tier1Monsters[13].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:13
            new Vector2((Betrayal.WIDTH - tier1Monsters[14].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:14
            new Vector2((Betrayal.WIDTH - tier1Monsters[15].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:15
            new Vector2((Betrayal.WIDTH - tier1Monsters[16].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:16
            new Vector2((Betrayal.WIDTH - tier1Monsters[17].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:17
            new Vector2((Betrayal.WIDTH - tier1Monsters[18].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:18
            new Vector2((Betrayal.WIDTH - tier1Monsters[19].getWidth()) / 2+100, Betrayal.HEIGHT/2 + 100),    //Monster ID:19
    };

    //Tier 2
    public static final Dimensions[] tier2Monsters = {
            new Dimensions(400, 450),    //Monster ID:0
            new Dimensions(350, 450),    //Monster ID:1
            new Dimensions(450, 450),    //Monster ID:2
            new Dimensions(500, 450),    //Monster ID:3
            new Dimensions(650, 550),    //Monster ID:4
            new Dimensions(500, 500),    //Monster ID:5
            new Dimensions(500, 500),    //Monster ID:6
            new Dimensions(400, 600),    //Monster ID:7
            new Dimensions(400, 500)     //Monster ID:8
    };
    public static final Vector2[] tier2MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier2Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),   //Monster ID:0
            new Vector2((Betrayal.WIDTH - tier2Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),   //Monster ID:1
            new Vector2((Betrayal.WIDTH - tier2Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),   //Monster ID:2
            new Vector2((Betrayal.WIDTH - tier2Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 75),    //Monster ID:3
            new Vector2((Betrayal.WIDTH - tier2Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),    //Monster ID:4
            new Vector2((Betrayal.WIDTH - tier2Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),    //Monster ID:5
            new Vector2((Betrayal.WIDTH - tier2Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 ),        //Monster ID:6
            new Vector2((Betrayal.WIDTH - tier2Monsters[7].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),    //Monster ID:7
            new Vector2((Betrayal.WIDTH - tier2Monsters[8].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),    //Monster ID:8
    };

    //Tier 3
    public static final Dimensions[] tier3Monsters = {
            new Dimensions(450, 400),    //Monster ID:0
            new Dimensions(600, 500),    //Monster ID:1
            new Dimensions(400, 450),    //Monster ID:2
            new Dimensions(400, 450),    //Monster ID:3
            new Dimensions(550, 500),    //Monster ID:4
            new Dimensions(550, 550),    //Monster ID:5
            new Dimensions(500, 500),    //Monster ID:6
            new Dimensions(500, 500)     //Monster ID:7
    };
    public static final Vector2[] tier3MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier3Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 75),     //Monster ID:0
            new Vector2((Betrayal.WIDTH - tier3Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 ),         //Monster ID:1
            new Vector2((Betrayal.WIDTH - tier3Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),     //Monster ID:2
            new Vector2((Betrayal.WIDTH - tier3Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:3
            new Vector2((Betrayal.WIDTH - tier3Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 100),    //Monster ID:4
            new Vector2((Betrayal.WIDTH - tier3Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),     //Monster ID:5
            new Vector2((Betrayal.WIDTH - tier3Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 + 50),     //Monster ID:6
            new Vector2((Betrayal.WIDTH - tier3Monsters[6].getWidth()) / 2, Betrayal.HEIGHT/2 + 50)      //Monster ID:7
    };

    // Tier 4
    public static final Dimensions[] tier4Monsters = {
            new Dimensions(550, 550),    //Monster ID:0
            new Dimensions(350, 400),    //Monster ID:1
            new Dimensions(550, 550),    //Monster ID:2
            new Dimensions(550, 550),    //Monster ID:3
            new Dimensions(550, 550),    //Monster ID:4
            new Dimensions(550, 550)     //Monster ID:5
    };
    public static final Vector2[] tier4MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier4Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:0
            new Vector2((Betrayal.WIDTH - tier4Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:1
            new Vector2((Betrayal.WIDTH - tier4Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:2
            new Vector2((Betrayal.WIDTH - tier4Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:3
            new Vector2((Betrayal.WIDTH - tier4Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:4
            new Vector2((Betrayal.WIDTH - tier4Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 25)     //Monster ID:5
    };

    // Tier 5
    public static final Dimensions[] tier5Monsters = {
            new Dimensions(650, 550),    //Monster ID:0
            new Dimensions(650, 850),    //Monster ID:1
            new Dimensions(720, 600),    //Monster ID:2
            new Dimensions(550, 550),    //Monster ID:3
            new Dimensions(550, 550),    //Monster ID:4
            new Dimensions(650, 650)     //Monster ID:5
    };
    public static final Vector2[] tier5MonstersPosition = {
            new Vector2((Betrayal.WIDTH - tier5Monsters[0].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:0
            new Vector2((Betrayal.WIDTH - tier5Monsters[1].getWidth()) / 2, Betrayal.HEIGHT/2 - 100),   //Monster ID:1
            new Vector2((Betrayal.WIDTH - tier5Monsters[2].getWidth()) / 2, Betrayal.HEIGHT/2 ),        //Monster ID:2
            new Vector2((Betrayal.WIDTH - tier5Monsters[3].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:3
            new Vector2((Betrayal.WIDTH - tier5Monsters[4].getWidth()) / 2, Betrayal.HEIGHT/2 + 25),    //Monster ID:4
            new Vector2((Betrayal.WIDTH - tier5Monsters[5].getWidth()) / 2, Betrayal.HEIGHT/2 + 25)     //Monster ID:5
    };
}

/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utils.constants;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class DungeonCoords {

	public static final float PLAYER_WIDTH = 128;
	public static final float PLAYER_HEIGHT = 192;
	public static final float MONSTER_WIDTH = 300;
	public static final float MONSTER_HEIGHT = 300;

	public static final Vector2 monster = new Vector2((Betrayal.WIDTH - MONSTER_WIDTH) / 2,
			Betrayal.HEIGHT - 300 - MONSTER_HEIGHT / 2);
	public static final Vector2 player5 = new Vector2(30f, 700f);
	public static final Vector2 player1 = new Vector2((Betrayal.WIDTH - PLAYER_WIDTH) / 2, 500);
	public static final Vector2 player3 = new Vector2((player5.x + player1.x) / 2, (player5.y + player1.y) / 2);
	public static final Vector2 player4 = new Vector2(Betrayal.WIDTH - player5.x - PLAYER_WIDTH,
			player5.y);
	public static final Vector2 player2 = new Vector2(Betrayal.WIDTH - player3.x - PLAYER_WIDTH,
			player3.y);
	public static final Vector2 player[] = {player1, player2, player3, player4, player5};
}

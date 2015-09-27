/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utils;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class DungeonCoords {

	public static final float PLAYER_WIDTH = 128;
	public static final float PLAYER_HEIGHT = 192;

	public static final Vector2 player5 = new Vector2(30f, Panel.top.getTopY() + 50);
	public static final Vector2 player1 = new Vector2((Betrayal.WIDTH - PLAYER_WIDTH) / 2, player5.y);
	public static final Vector2 player3 = new Vector2((player5.x + player1.x) / 2, player5.y);
	public static final Vector2 player4 = new Vector2(Betrayal.WIDTH - player5.x - PLAYER_WIDTH,
			player5.y);
	public static final Vector2 player2 = new Vector2(Betrayal.WIDTH - player3.x - PLAYER_WIDTH,
			player5.y);
	public static final Vector2 player[] = {player1, player2, player3, player4, player5};
}

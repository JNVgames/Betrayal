/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.dungeon.utils;

import com.badlogic.gdx.math.Vector2;
import com.jnv.betrayal.main.Betrayal;

public final class DungeonCoords {

	public static final float PLAYER_WIDTH = 128;
	public static final float PLAYER_HEIGHT = 192;

	public static final Vector2 player[][] = {
			// 1 Player
			{
					new Vector2((Betrayal.WIDTH - PLAYER_WIDTH) / 2, Panel.top.getTop() + 10)
			},
			// 2 Players
			{
					new Vector2(68, Panel.top.getTop() + 10),
					new Vector2(Betrayal.WIDTH - PLAYER_WIDTH - 217, Panel.top.getTop() + 10)
			},
			// 3 Players
			{
					new Vector2(0, Panel.top.getTop() + 10),
					new Vector2((Betrayal.WIDTH - PLAYER_WIDTH - 149) / 2 +25, Panel.top.getTop() + 150),
					new Vector2(Betrayal.WIDTH - PLAYER_WIDTH - 149 , Panel.top.getTop() + 10)
			},
			// 4 Players
			{
					new Vector2(0, Panel.top.getTop() + 150),
					new Vector2(100, Panel.top.getTop() + 10),
					new Vector2(450, Panel.top.getTop() + 10),
					new Vector2(350, Panel.top.getTop() + 150)
			}
	};
}

package com.jnv.betrayal.lobby.inventory;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.ui.Image;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public interface InventoryLoadable {

	Label getTitleActor();

	void refresh();

	Character getCharacter();

	Image getBackground();

	Group getPopup();

	Betrayal getGame();
}

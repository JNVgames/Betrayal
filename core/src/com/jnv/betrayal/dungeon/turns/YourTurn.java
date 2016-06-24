package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

public class YourTurn extends Turn {

	private Pool<Label> panelPool;
	private Pool<Button> buttonPool;

	public YourTurn(Pool<Label> panelPool, Pool<Button> buttonPool) {
		this.panelPool = panelPool;
		this.buttonPool = buttonPool;
	}

	@Override
	public void draw(Group group) {

	}

	@Override
	public void erase() {

	}
}

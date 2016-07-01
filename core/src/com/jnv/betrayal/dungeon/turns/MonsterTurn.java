package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

public class MonsterTurn extends Turn {

	public MonsterTurn(Field field, Pool<Label> panelPool, Pool<Button> buttonPool, Group panels, Betrayal game) {
		super(field, panelPool, buttonPool, panels, game);
	}

	@Override
	public void draw() {
		panels.clearChildren();
		createPanel("Monster's turn", 80, Panel.full, new Runnable() {
			@Override
			public void run() {
				field.actionManager.performAction(new Action(field.getCurrentCard(),
						field.getAllPlayerCards(), ActionType.ATTACK));

				// TODO REMOVE THIS LINE
				field.turnManager.nextTurn();
			}
		});
	}
}

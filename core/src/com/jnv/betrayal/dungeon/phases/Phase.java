package com.jnv.betrayal.dungeon.phases;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

public abstract class Phase {

	public final Group group = new Group();
	public final Pool<Label> panelPool;
	public final Pool<Button> buttonPool;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final Field field;

	protected Phase(PhaseManager pm) {
		this.field = pm.field;

		gsm = pm.field.gsm;
		res = field.res;
		panelPool = pm.panelPool;
		buttonPool = pm.buttonPool;
	}
}

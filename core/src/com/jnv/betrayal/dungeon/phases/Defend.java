package com.jnv.betrayal.dungeon.phases;

import com.jnv.betrayal.dungeon.utils.Panel;

public class Defend extends Phase {

	Defend(final PhaseManager pm) {
		super(pm);
		createPanel("Defend Phase", 50, Panel.full, new Runnable() {
			@Override
			public void run() {
				pm.nextPhase();
			}
		});
	}

	public int getPhaseNum() {
		return PhaseConst.DEFEND;
	}
}

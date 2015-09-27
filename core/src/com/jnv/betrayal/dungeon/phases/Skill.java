package com.jnv.betrayal.dungeon.phases;

import com.jnv.betrayal.dungeon.utils.Panel;

public class Skill extends Phase {

	Skill(final PhaseManager pm) {
		super(pm);
		createPanel("Skill Phase", 50, Panel.full, new Runnable() {
			@Override
			public void run() {
				pm.nextPhase();
			}
		});
	}

	public int getPhaseNum() {
		return PhaseConst.SKILL;
	}
}

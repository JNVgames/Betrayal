package com.jnv.betrayal.dungeon.phases;

import com.jnv.betrayal.dungeon.mechanics.Field;

/**
 * Guides the dungeon through the different phases of the dungeon
 * Phases are: Main, Defend, Item, Skill, Attach
 */
public class PhaseManager {

	public final Field field;
	private Phase currentPhase;

	public PhaseManager(Field field) {
		this.field = field;
		currentPhase = new Main(this);
		field.addActor(currentPhase.group);
	}

	public void nextPhase() {
		if (currentPhase.getPhaseNum() + 1 > 4) {
			setPhase(currentPhase.getPhaseNum() - 4);
		} else setPhase(currentPhase.getPhaseNum() + 1);
	}

	public void setPhase(int phaseNum) {
		switch (phaseNum) {
			case PhaseConst.MAIN:
				setPhase(new Main(this));
				break;
			case PhaseConst.ATTACK:
				setPhase(new Attack(this));
				break;
			case PhaseConst.DEFEND:
				setPhase(new Defend(this));
				break;
			case PhaseConst.ITEM:
				setPhase(new Item(this));
				break;
			case PhaseConst.SKILL:
				setPhase(new Skill(this));
				break;
			default:
				throw new IllegalStateException("Dungeon phase does not exist");
		}
	}

	public void setPhase(Phase phase) {
		field.removeActor(currentPhase.group);
		currentPhase = phase;
		field.addActor(currentPhase.group);
	}
}

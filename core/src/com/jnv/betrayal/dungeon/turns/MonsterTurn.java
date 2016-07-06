package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.Random;

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
						monsterAttack(), ActionType.ATTACK));

				// TODO REMOVE THIS LINE
				field.turnManager.nextTurn();
			}
		});
	}

	private ArrayList<Card> monsterAttack() {
		int numTargets = ((MonsterCard) field.getCurrentCard()).getNumAttackTargets();
		int max = field.playerZone.size();
		int x;
		ArrayList<Integer> target = new ArrayList<Integer>();
		ArrayList<Card> dst = new ArrayList<Card>();
		Random randomNumberGenerator = new Random();
		while (target.size() < max && target.size() < numTargets) {
			x = randomNumberGenerator.nextInt(max);
			if (!target.contains(x)) {
				target.add(x);
			}
		}
		for (Integer i : target) {
			dst.add(field.playerZone.get(i));
		}

		return dst;
	}
}

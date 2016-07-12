package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.effects.Attack;
import com.jnv.betrayal.dungeon.effects.Event;
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
				monsterAttack();

				// TODO REMOVE THIS LINE
				field.turnManager.nextTurn();
			}
		});
	}

	private void monsterAttack() {
		MonsterCard card = ((MonsterCard) field.getCurrentCard());
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

		System.out.println(card.effectCounter);
		if (card.hasEffect() && card.effectCounter == card.getEffect().getTurns()) {
			card.effectCounter = 1;
			//do EFFECT
			Event event = new Event(card.getEffect());
			event.getEffect().setSrc(card);
			if (!event.getEffect().isHostile()) {
				dst.clear();
				dst.add(card);
			}
			event.getEffect().setDest(dst);
			card.getField().roundManager.addEvent(event);
		} else {
			card.effectCounter++;

			field.roundManager.addEvent(new Attack(field.getCurrentCard(), dst));
		}

	}
}

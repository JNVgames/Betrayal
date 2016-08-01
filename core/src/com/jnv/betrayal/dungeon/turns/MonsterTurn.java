package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.dungeon.effects.actions.Attack;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
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
		createGrayPanel( "monster's turn", FontManager.getFont80(), Panel.full);
		field.addAction(Actions.delay(3f, new RunnableAction(){
			@Override
			public void run() {
				if (field.gsm.game.getCurrentCharacter().getId() == field.playerZone.get(0).getID()) {
					System.out.println("Monster Sending signal here BEEP BEEP BEEP");
					monsterAttack();
				}
			}
		}));

	}

//	private boolean sendMonsterAttack(){
//		for(Card card : field.playerZone){
//			if (field.gsm.game.getCurrentCharacter().getId() == field.playerZone.get(0).getID())
//				return true;
//		}
//		return false;
//	}

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
			Event event = new Event(card.getEffect(), card.getEffect().getStartType());
			event.getEffect().setSrc(card);
			if (!event.getEffect().isHostile()) {
				dst.clear();
				dst.add(card);
			}
			event.getEffect().setDest(dst);
			System.out.println(event.toJSON().toString());
			card.getField().roundManager.addEvent(event);
			field.turnManager.nextTurn();
		} else {
			card.effectCounter++;
			Effect attack = new Attack(field.getCurrentCard(), dst);
			field.roundManager.addEvent(attack, attack.getStartType());
			field.turnManager.nextTurn();
		}

	}
}

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.graphics.Color;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardAnimation;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class AnimationManager {

	private CardAnimation animation;

	public AnimationManager(BetrayalAssetManager res) {
		animation = new CardAnimation(res);
	}

	public void performAnimation(Event event) {

		switch (event.getEffect().getStartType()) {

			/************************ BASIC MOVES *************************/
			case ATTACK:
			case WARRIOR_SPECIAL:
			case THIEF_SPECIAL:
				animation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					animation.flashColor(card, Color.RED);
					animation.damaged(card);
				}
				break;
			case DEFEND:
			case KNIGHT_SPECIAL:
				animation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					animation.defend(card);
				}
				break;
			case FLEE:
				animation.flee(event.getSrc());
				break;
			case FAIL_TO_FLEE:
				animation.failToFlee(event.getSrc());
				break;
			case DIED:
				animation.fadeOut(event.getSrc());
				break;

			/********************Item abd Skills*************************/
			case HEAL:
			case PRIEST_HEAL_SPECIAL:
				animation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					animation.heal(card);
				}
				break;
			case BOMB:
				break;
			case POISON:
				animation.changeColor(event.getSrc(), Color.GREEN);
				break;
			case BUFF_ATTACK:
			case PRIEST_ATTACK_SPECIAL:
				animation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					animation.buffAttack(card);
				}
				break;
			case BUFF_DEFENSE:
			case PRIEST_DEFENSE_SPECIAL:
				animation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					animation.buffDefense(card);
				}
				break;
			case BUFF_ATTACK_DEFENSE:
				break;
			case DEBUFF_ATTACK:
				animation.jump(event.getSrc());
				break;
			case DEBUFF_DEFENSE:
				break;
			case DEBUFF_ATTACK_DEFENSE:
				break;

			/********************Consistent Effects*********************/
			case C_POISON:
				animation.flashColor(event.getSrc(), Color.RED);
				break;

			/**********************End Effects**************************/
//			case E_BUFF_ATTACK:
//				break;
//			case E_BUFF_ATTACK_DEFENSE:
//				break;
//			case E_BUFF_DEFENSE:
//				break;
//			case E_DEBUFF_ATTACK:
//				break;
//			case E_DEBUFF_ATTACK_DEFENSE:
//				break;
//			case E_DEBUFF_DEFENSE:
//				break;
//			case E_POISON:
//				break;

			/**********************Shouldn't Happen*********************/
			default:
				animation.removeColor(event.getSrc());
				break;
		}
	}
}

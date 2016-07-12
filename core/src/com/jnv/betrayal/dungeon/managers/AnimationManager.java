package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.graphics.Color;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardAnimation;
import com.jnv.betrayal.dungeon.effects.Event;

public class AnimationManager {

	public static void performAnimation(Event event) {

		switch (event.getEffect().getStartType()) {

			/************************ BASIC MOVES *************************/
			case ATTACK:
				CardAnimation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					CardAnimation.flashColor(card, Color.RED);
				}
				break;
			case DEFEND:
				CardAnimation.jump(event.getSrc());
				for (Card card : event.getDest()) {
					CardAnimation.defend(card);
				}
				break;
			case FLEE:
				CardAnimation.flee(event.getSrc());
				break;
			case FAIL_TO_FLEE:
				CardAnimation.failToFlee(event.getSrc());
				break;
			case DIED:
				CardAnimation.fadeOut(event.getSrc());
				break;

			/********************Item abd Skills*************************/
			case HEAL:
				CardAnimation.jump(event.getSrc());
				CardAnimation.flashColor(event.getSrc(), Color.YELLOW);
				break;
			case BOMB:
				break;
			case POISON:
				CardAnimation.changeColor(event.getSrc(), Color.GREEN);
				break;
			case BUFF_ATTACK:
				break;
			case BUFF_DEFENSE:
				break;
			case BUFF_ATTACK_DEFENSE:
				break;
			case DEBUFF_ATTACK:
				CardAnimation.jump(event.getSrc());
				break;
			case DEBUFF_DEFENSE:
				break;
			case DEBUFF_ATTACK_DEFENSE:
				break;

			/********************Consistent Effects*********************/
			case C_POISON:
				CardAnimation.flashColor(event.getSrc(),Color.RED);
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
				CardAnimation.removeColor(event.getSrc());
				break;
		}
	}

//	ATTACK("attacked"),
//	DEFEND("defended"),
//	FLEE("fled"),
//	FAIL_TO_FLEE("failed to flee"),
//	DIED("died"),
//
//	// Item effects
//	HEAL("healed"),
//	BOMB("bombed"),
//	POISON("poisoned"),
//	BUFF_ATTACK("was buffed (atk) by"),
//	BUFF_DEFENSE("was buffed (def) by"),
//	BUFF_ATTACK_DEFENSE("was buffed (atk, def) by"),
//	DEBUFF_ATTACK("was debuffed (atk) by"),
//	DEBUFF_DEFENSE("was debuffed (def) by"),
//	DEBUFF_ATTACK_DEFENSE("was debuffed (atk, def)"),
//	//SKIP_TURN("skipped")
//
//	// Consistent effects
//	C_POISON("dealt poison dmg to"),
//
//	// End effects
//	E_POISON("was freed from the poison of"),
//	E_BUFF_ATTACK("lost their buff (atk) from"),
//	E_BUFF_DEFENSE("lost their buff (def) from"),
//	E_BUFF_ATTACK_DEFENSE("lost their buff (atk, def) from"),
//	E_DEBUFF_ATTACK("regained stats (atk) from debuff of"),
//	E_DEBUFF_DEFENSE("regained stats (def) from debuff of"),
//	E_DEBUFF_ATTACK_DEFENSE("regained stats (atk, def) from debuff of");

}

package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.graphics.Color;
import com.jnv.betrayal.dungeon.animations.CardAnimation;
import com.jnv.betrayal.dungeon.animations.TimelineEvent;
import com.jnv.betrayal.dungeon.animations.utils.AnimationValues;
import com.jnv.betrayal.dungeon.animations.utils.GreatestFloatFinder;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Event;
import com.jnv.betrayal.resources.BetrayalAssetManager;

public class AnimationManager {

	private CardAnimation cardAnimation;
	private GreatestFloatFinder floatFinder;

	public AnimationManager(BetrayalAssetManager res) {
		cardAnimation = new CardAnimation(res);
		floatFinder = new GreatestFloatFinder();
	}

	public CardAnimation getCardAnimation() {
		return cardAnimation;
	}

	public TimelineEvent createAnimationTimelineEvent(final Event event) {

		Runnable animation = null;
		floatFinder.reset();
		switch (event.getEventType()) {

			/************************ BASIC MOVES *************************/
			case ATTACK:
			case WARRIOR_SPECIAL:
			case THIEF_SPECIAL:
			case TRUE_DAMAGE:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							if (card.isBeingDefended()) {
								// Defenders get flashed color animation
								for (int i = 0; i < card.getDefendersSize(); i++) {
									cardAnimation.flashColor(card.getDefender(i), Color.RED);
								}
								cardAnimation.defend(card);
								cardAnimation.damaged(card);
							} else {
								cardAnimation.flashColor(card, Color.RED);
								cardAnimation.damaged(card);
							}
						}
					}
				};

				for (Card card : event.getDest()) {
					if (card.isBeingDefended()) {
						floatFinder.enterFloat(AnimationValues.DEFEND_DURATION);
					}
				}
				floatFinder.enterFloat(AnimationValues.FLASH_COLOR_DURATION,
						AnimationValues.DAMAGED_DURATION,
						AnimationValues.JUMP_DURATION,
						AnimationValues.HEALTH_BAR_ANIM_DURATION);
				break;
			case DEFEND:
			case KNIGHT_SPECIAL:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.defend(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				floatFinder.enterFloat(AnimationValues.DEFEND_DURATION);
				break;
			case FLEE:
				animation = new Runnable() {
					@Override
					public void run() {
						System.out.println("FLEE");
						cardAnimation.flee(event.getSrc());
					}
				};

				floatFinder.enterFloat(AnimationValues.FLEE_DURATION);
				break;
			case FAIL_TO_FLEE:
				animation = new Runnable() {
					@Override
					public void run() {
						System.out.println("FAIL");
						cardAnimation.failToFlee(event.getSrc());
					}
				};

				floatFinder.enterFloat(AnimationValues.FAILED_TO_FLEE_DURATION);
				break;
			case DIED:
//				runnable = new Runnable() {
//					@Override
//					public void run() {
//						cardAnimation.fadeOut(event.getSrc());
//					}
//				};
//
//				floatFinder.enterFloat(AnimationValues.FADE_OUT_DURATION);
				break;

			/********************Item and Skills*************************/
			case HEAL:
			case PRIEST_HEAL_SPECIAL:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.heal(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION,
						AnimationValues.HEAL_DURATION,
						AnimationValues.HEALTH_BAR_ANIM_DURATION);
				break;
			case BOMB:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.attachBomb(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.BOMB_DURATION);
				break;
			case POISON:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.longFlashColor(card, Color.GREEN);
							cardAnimation.poison(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				floatFinder.enterFloat(AnimationValues.LONG_FLASH_COLOR_DURATION);
				floatFinder.enterFloat(AnimationValues.POISON_DURATION);
				break;
			case BUFF_ATTACK:
			case PRIEST_ATTACK_SPECIAL:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.buffAttack(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				floatFinder.enterFloat(AnimationValues.BUFF_ATTACK_DURATION);
				break;
			case BUFF_DEFENSE:
			case PRIEST_DEFENSE_SPECIAL:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.buffDefense(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				floatFinder.enterFloat(AnimationValues.BUFF_DEFENSE_DURATION);
				break;
			case BUFF_ATTACK_DEFENSE:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
						for (Card card : event.getDest()) {
							cardAnimation.buffAttackAndDefense(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				floatFinder.enterFloat(AnimationValues.BUFF_ATTACK_DEFENSE_DURATION);
				break;
			case DEBUFF_ATTACK:
				animation = new Runnable() {
					@Override
					public void run() {
						cardAnimation.jump(event.getSrc());
					}
				};

				floatFinder.enterFloat(AnimationValues.JUMP_DURATION);
				break;
			case DEBUFF_DEFENSE:
				break;
			case DEBUFF_ATTACK_DEFENSE:
				break;

			/********************Consistent Effects*********************/
			case C_POISON:
				System.out.println("C_POISON");
				animation = new Runnable() {
					@Override
					public void run() {
						for (Card card : event.getDest()) {
							cardAnimation.flashColor(card, Color.GREEN);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.FLASH_COLOR_DURATION,
						AnimationValues.HEALTH_BAR_ANIM_DURATION);
				break;

			/**********************End Effects**************************/
			case E_BOMB:
				animation = new Runnable() {
					@Override
					public void run() {
						for (Card card : event.getDest()) {
							cardAnimation.removeBomb(card);
							cardAnimation.damaged(card);
						}
					}
				};

				floatFinder.enterFloat(AnimationValues.DAMAGED_DURATION);
				break;

			case E_POISON:
				animation = new Runnable() {
					@Override
					public void run() {
						for (Card card : event.getDest()) {
							cardAnimation.removePoison(card);
						}
					}
				};
				break;
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
		}

		return new TimelineEvent(floatFinder.highest(), animation);
	}
}

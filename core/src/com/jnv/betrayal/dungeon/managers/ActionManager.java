//package com.jnv.betrayal.dungeon.managers;
//
//
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.jnv.betrayal.dungeon.actions.Action;
//import com.jnv.betrayal.dungeon.cards.Card;
//import com.jnv.betrayal.dungeon.cards.MonsterCard;
//import com.jnv.betrayal.dungeon.cards.PlayerCard;
//import com.jnv.betrayal.dungeon.mechanics.Field;
//import com.jnv.betrayal.gamestates.GameStateManager;
//import com.jnv.betrayal.popup.OKPopup;
//
//import java.util.ArrayDeque;
//import java.util.Deque;
//
///**
// * Takes in all actions performed by cards and communicates with the database. Cards can
// * call functions from this class to determine what happened on other players/monsters phones
// */
//public class ActionManager {
//
//	//for history. Always add to the front of the Deque
//	public final Deque<Action> actionHistory;
//	private Field field;
//
//	public ActionManager(Field field) {
//		this.field = field;
//		actionHistory = new ArrayDeque<Action>();
//	}
//
//	//gets the action that happened most recently
//	public Action getMostRecentAction() {
//		return actionHistory.getFirst();
//	}
//
//	public void addToHistory(Action action) {
//		AnimationManager.performAnimation(action);
//		actionHistory.addLast(action);
//	}
//
//	//update field, update all card's data
//	public void performAction(Action action) {
//		AnimationManager.performAnimation(action);
//		// If dest card is null, perform action on self
//		switch (action.getActionType()) {
//			case ATTACK:
//				attack(action);
//				break;
//			case DEFEND:
//				break;
//			case FLEE:
//				flee(action);
//				break;
//			case FAIL_TO_FLEE:
//				failedToFlee(action);
//				break;
//			case DIED:
//				died(action);
//				break;
//			default:
//				throw new AssertionError();
//		}
//		actionHistory.addLast(action);
//	}
//
//	private void attack(Action action) {
//		for (Card card : action.getDest()) {
//			// Check if there are defenders
//			if (card.isBeingDefended()) {
//				// Apply damage damage appropriately
//				card.damageDefender(action.getSrc());
//			} else { // There are no defenders
//				card.takeDamage(action.getSrc().getCurrentAttack());
//			}
//		}
//	}
//
//	private void failedToFlee(Action action) {
//		final Card card = action.getSrc();
//		Runnable r = new Runnable() {
//			@Override
//			public void run() {
//				new OKPopup(field.game, "Flee Failed");
//				field.turnManager.nextTurn();
//			}
//		};
//		card.getCardImage().addAction(Actions.delay(2.5f, Actions.run(r)));
//	}
//
//	private void flee(Action action) {
//		final Card card = action.getSrc();
//		if (card instanceof PlayerCard && card.getID() == field.game.getCurrentCharacter().getId()) {
//			// Flee Successful
//			field.removePlayerCard((PlayerCard) card);
//
//			Runnable r = new Runnable() {
//				@Override
//				public void run() {
//					new OKPopup(field.game, "Flee Successful") {
//						@Override
//						public void onConfirm() {
//							field.game.gsm.setState(GameStateManager.State.LOBBY);
//						}
//					};
//				}
//			};
//			card.getCardImage().addAction(Actions.delay(2.5f, Actions.run(r)));
//		} else if (card instanceof PlayerCard) {
//			//Teammate died
//			field.removePlayerCard((PlayerCard) card);
//		} else {
//			throw new AssertionError("create assertion error thingy. This shouldnt be happening - means not a playercard");
//		}
//	}
//
//	private void died(Action action) {
//		Card card = action.getSrc();
//		if (card instanceof PlayerCard && card.getID() == field.game.getCurrentCharacter().getId()) {
//			// You have died
//			field.removePlayerCard((PlayerCard) card);
//
//			Runnable r = new Runnable() {
//				@Override
//				public void run() {
//					new OKPopup(field.game, "You Have Died") {
//						@Override
//						public void onConfirm() {
//							field.game.characters.remove(field.game.getCurrentCharacter());
//							field.game.gsm.setState(GameStateManager.State.MENU);
//						}
//					};
//				}
//			};
//			card.getCardImage().addAction(Actions.delay(4f, Actions.run(r)));
//
//		} else if (card instanceof PlayerCard) {
//			//Teammate died
//			field.removePlayerCard((PlayerCard) card);
//
//		} else if (card instanceof MonsterCard) {
//			//Monster Card
//			field.removeMonsterCard((MonsterCard) card);
//			if (field.isMonsterZoneEmpty()) {
//				Runnable r = new Runnable() {
//					@Override
//					public void run() {
//						new OKPopup(field.game, "Floor Completed!") {
//							@Override
//							public void onConfirm() {
//								for (Card c : field.getAllPlayerCards()) {
//									((PlayerCard) c).levelUpCharacter();
//								}
//								field.game.gsm.setState(GameStateManager.State.LOBBY);
//							}
//						};
//					}
//				};
//				card.getCardImage().addAction(Actions.delay(4f, Actions.run(r)));
//			}
//		} else {
//			throw new AssertionError("create assertion error thingy. This shouldnt be happening - means not mosnter or palyercard");
//		}
//	}
//
//}

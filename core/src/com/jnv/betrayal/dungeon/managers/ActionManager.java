package com.jnv.betrayal.dungeon.managers;


import com.jnv.betrayal.dungeon.ActionHandler.Action;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.mechanics.Field;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Takes in all actions performed by cards and communicates with the database. Cards can
 * call functions from this class to determine what happened on other players/monsters phones
 */
public class ActionManager {

	//for history. Always add to the front of the Deque
	public final Deque<Action> actionHistory;
	private Field field;

	public ActionManager(Field field) {
		this.field = field;
		actionHistory = new ArrayDeque<Action>();
	}

	//gets the action that happened most recently
	public Action getMostRecentAction() {
		return actionHistory.getFirst();
	}

	public void addToHistory(Action action) {
		actionHistory.addLast(action);
	}

	//update field, update all card's data
	public void performAction(Action action) {
		actionHistory.addLast(action);
		// todo add to event log

		AnimationManager.performAnimation(action);
		// If dest card is null, perform action on self
		if (action.destExist()) {
			switch (action.getActionType()) {
				case ATTACK:
					for (Card card : action.getDest()) {
						// Check if there are defenders
						if (card.isBeingDefended()) {
							// Apply damage damage appropriately
							card.damageDefender(action.getSrc());
						} else { // There are no defenders
							card.takeDamage(action.getSrc().getCurrentAttack());
						}
					}
					break;
				case DEFEND:
					((PlayerCard) action.getSrc()).defendCard(action.getDest().get(0));
					action.getDest().get(0).addDefender((PlayerCard) action.getSrc());
					break;
				case FLEE:
					break;
				default:
					throw new AssertionError();
			}
		}
	}

}

package com.jnv.betrayal.dungeon.ActionHandler;


import com.jnv.betrayal.dungeon.managers.AnimationManager;
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
	public Action getMostRecentAction(){
		return actionHistory.getFirst();
	}

	//update field, update all card's data
	public void performAction(Action action) {
		actionHistory.addFirst(action);

		// If dest card is null, perform action on self
		if (action.destExist()) {
			switch (action.getActionType()){
				case ATTACK:
					AnimationManager.performAnimation(action);
					break;
				case DEFEND:
					AnimationManager.performAnimation(action);
					break;
				case FLEE:
					AnimationManager.performAnimation(action);
					break;
				default:
					throw new AssertionError();
			}
		}
	}

}

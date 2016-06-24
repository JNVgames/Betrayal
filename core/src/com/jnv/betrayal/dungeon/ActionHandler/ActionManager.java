package com.jnv.betrayal.dungeon.ActionHandler;


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
		actionHistory = new ArrayDeque();
	}

	//gets the action that happened most recently
	public Action getMostRecentAction(){
		return actionHistory.getFirst();
	}

	//adds an action to the history deque and notifies all other cards
	public void addActionToHistory(Action action){
		actionHistory.addFirst(action);
	}

	//update field, update all card's data
	public void performAction() {
		Action action = actionHistory.peekLast();
		// If dest card is null, perform action on self
		if (!action.destExist()) {
			switch (action.getActionType()){
				case ATTACK:
					break;
				case DEFEND:
					break;
				case FLEE:
					break;
				default:
					throw new AssertionError();
			}
		}
	}

}

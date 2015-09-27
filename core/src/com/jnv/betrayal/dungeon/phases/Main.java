package com.jnv.betrayal.dungeon.phases;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.NormalAttack;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.dungeon.utils.State;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.scene2d.Group;

import java.util.Stack;

public class Main extends Phase {

	private Stack<State> menu = new Stack<State>();
	private Action currentAction;

	Main(PhaseManager pm) {
		super(pm);
		draw(State.MAIN);
		pm.field.resetCards();
	}

	private void draw(State state) {
		clearActionBar();
		switch (state) {
			case BACK:
				menu.pop();
				draw(menu.peek());
				break;
			case MAIN:
				drawMainMenu();
				pushMenuState(State.MAIN);
				break;
			case ATTACK:
				//drawAttackMenu();
				//pushMenuState(State.ATTACK);
				break;
			case ITEMS:
				//drawItemsMenu();
				break;
			case EVENT_LOG:
				//drawEventLogMenu();
				break;
			case TARGET_SELECT:
				throw new AssertionError("Use draw(int targets) for target selection");
			default:
				break;
		}
	}

	/**
	 * Use this method for target selection
	 *
	 * @param action used for targets
	 */
	private void draw(Action action) {
		// If currentAction is null, set the action to current action
		if (currentAction == null) currentAction = action;
			// If not null, unselect all
		else if (!currentAction.equals(action)) { // if (currentAction != null)
			field.unselectAll();
		}

		clearActionBar();

		field.beginSelectMode(action.getTargetLimit());
		drawTargetSelect();
		pushMenuState(State.TARGET_SELECT);
	}

	// Helpers
	private void pushMenuState(State state) {
		if (menu.isEmpty() || menu.peek() != state) menu.push(state);
	}

	private void drawMainMenu() {
		createPanel("Items", 70, Panel.bottomLeft, new Runnable() {
			public void run() {
				draw(State.EVENT_LOG);
			}
		});
		createPanel("Attack", 70, Panel.topLeft, new Runnable() {
			public void run() {
				draw(new NormalAttack());
			}
		});
		createPanel("Skills", 70, Panel.topRight, new Runnable() {
			public void run() {
				draw(State.SKILLS);
			}
		});
		createPanel("Flee", 70, Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.game, "Are you sure you want to flee?" + "\n20% Chance") {
					@Override
					public void doSomething() {
						gsm.setState(GameStateManager.State.LOBBY);
					}
				};
			}
		});
	}

	private void drawTargetSelect() {
		final Actor checker = new Actor() {
			private Group doneButton = new Group();
			private boolean isDoneButtonGray = true;

			@Override
			public void act(float delta) {
				if (pm.field.getCardsSelected() > 0 && isDoneButtonGray) {
					doneButton.clear();
					doneButton = createPanel("Done", 70, Panel.top, new Runnable() {
						@Override
						public void run() {
							field.endSelectMode();
							if (field.getCardsSelected() == 0) {
								currentAction = null;
							}
							pm.nextPhase();
						}
					});
					isDoneButtonGray = false;
				} else if (pm.field.getCardsSelected() == 0 && !isDoneButtonGray){
					doneButton.clear();
					doneButton = createGrayPanel("Done", 70, Panel.top);
					isDoneButtonGray = true;
				}
			}
		};
		checker.setVisible(false);
		pm.field.addActor(checker);
		createGrayPanel("Done", 70, Panel.top);
		createPanel("Cancel", 70, Panel.bottom, new Runnable() {
			@Override
			public void run() {
				field.cancelSelectMode();
				if (field.getCardsSelected() == 0) currentAction = null;
				draw(State.BACK);
				checker.remove();
			}
		});
	}

	public int getPhaseNum() {
		return PhaseConst.MAIN;
	}
}

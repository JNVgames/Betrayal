package com.jnv.betrayal.dungeon.managers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.MonsterCard;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.turns.MonsterTurn;
import com.jnv.betrayal.dungeon.turns.TeamMemberTurn;
import com.jnv.betrayal.dungeon.turns.Turn;
import com.jnv.betrayal.dungeon.turns.YourTurn;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import io.socket.client.Socket;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

/**
 * Keeps track of user/party member/monster's turns.
 */
public class UIManager {

	public final Group panels = new Group();
	private Field field;
	private Turn currentTurn;
	private YourTurn yourTurn;
	private TeamMemberTurn teamMemberTurn;
	private MonsterTurn monsterTurn;

	public UIManager(Field field) {
		this.field = field;
		Pool<Button> buttonPool = new Pool<Button>() {
			@Override
			public Button obtain() {
				Button tmp = super.obtain();
				ClickListener clickListener = tmp.getClickListener();
				// Remove all listeners except for the one needed for button to work
				for (EventListener listener : tmp.getListeners())
					if (listener != clickListener) tmp.removeListener(listener);
				return tmp;
			}

			@Override
			protected Button newObject() {
				return new Button();
			}
		};
		Pool<Label> panelPool = new Pool<Label>() {
			@Override
			public Label obtain() {
				Label tmp = super.obtain();
				tmp.clearListeners();
				return tmp;
			}

			@Override
			protected Label newObject() {
				return new Label(null, new Label.LabelStyle(FontManager.getFont70()));
			}
		};
		field.addActor(panels);

		yourTurn = new YourTurn(field, panelPool, buttonPool, panels, field.game);
		teamMemberTurn = new TeamMemberTurn(field, panelPool, buttonPool, panels, field.game);
		monsterTurn = new MonsterTurn(field, panelPool, buttonPool, panels, field.game);
	}

	public void nextTurn() {
		if (currentTurn instanceof YourTurn) {
			yourTurn.decreaseTurnsLeft();
		}
		drawUI();
	}

	public void drawUI() {
		Card currentCard = field.getCurrentCard();

		// Team member turn
		if (currentCard instanceof PlayerCard
				&& currentCard.getID() != field.game.getCurrentCharacter().getId()) {
			currentTurn = teamMemberTurn;
		}
		// Monster turn
		else if (currentCard instanceof MonsterCard) {
			currentTurn = monsterTurn;
		}
		// Your turn
		else {
			currentTurn = yourTurn;
			yourTurn.setFirstAppearance();
			yourTurn.setInitialDelay(field.animationManager.getTotalAnimationDuration());
		}
		currentTurn.draw();
	}

	public void attemptFlee(int fleeChance, Card card) {
		yourTurn.attemptFlee(fleeChance, card);
	}

	public void dungeonEnded() {
		dungeonEndedClient();
		Socket socket = field.getClientCharacter().getRoom().getSocket();
		if (socket != null && socket.connected()) {
			socket.emit("dungeonEnded");
		}
	}

	public void dungeonEndedClient() {
		for (Actor actor : panels.getChildren()) {
			actor.setTouchable(Touchable.disabled);
			actor.addAction(Actions.fadeOut(1));
			actor.addAction(Actions.delay(1, Actions.run(new Runnable() {
				@Override
				public void run() {
					panels.clear();
				}
			})));
		}
	}


	public Turn getCurrentTurn() {
		return currentTurn;
	}
}

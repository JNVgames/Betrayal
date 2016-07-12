package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.actions.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Attack;
import com.jnv.betrayal.dungeon.effects.Defend;
import com.jnv.betrayal.dungeon.effects.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.Flee;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.lobby.inventory.DungeonInventory;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class YourTurn extends Turn {

	public YourTurn(Field field, Pool<Label> panelPool, Pool<Button> buttonPool, Group panels, Betrayal game) {
		super(field, panelPool, buttonPool, panels, game);
	}

	private void drawSelectBar(final EventType eventType) {
		panels.clearChildren();
		panels.addActor(createPanel("Done", 70, Panel.top, new Runnable() {
			public void run() {
				List<Card> dest = new ArrayList<Card>(field.getCardsSelected());
				if (eventType == EventType.ATTACK) {
					field.roundManager.addEvent(new Attack(field.getCurrentCard(), dest));
				}
				else {
					field.roundManager.addEvent(new Defend(field.getCurrentCard(), dest));
				}
				field.turnManager.nextTurn();
				field.endSelectMode();
			}
		}));
		panels.addActor(createPanel("Cancel", 70, Panel.bottom, new Runnable() {
			public void run() {
				draw();
				field.endSelectMode();
			}
		}));
	}

	@Override
	public void draw() {
		panels.clearChildren();
		panels.addActor(createPanel("Items", 70, Panel.bottomLeft, new Runnable() {
			public void run() {
				new DungeonInventory(gsm.game, field.getCurrentCard());
				// todo change input to what is needed by the specific item
			}
		}));
		panels.addActor(createPanel("Attack", 70, Panel.topLeft, new Runnable() {
			public void run() {
				field.beginSelectMode(1);
				drawSelectBar(EventType.ATTACK);
			}
		}));
		panels.addActor(createPanel("Defend", 70, Panel.topRight, new Runnable() {
			public void run() {
				field.beginSelectMode(1);
				drawSelectBar(EventType.DEFEND);
			}
		}));
		panels.addActor(createPanel("Flee", 70, Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.game, "Flee? 25% Chance") {
					@Override
					public void doAction() {
						if (PlayerCard.canFlee(1)) {
							field.roundManager.addEvent(new Flee(field.getCurrentCard()));
						} else {
							field.roundManager.addEvent(new FailedToFlee(field.getCurrentCard()));
						}
					}
				};
			}
		}));
	}
}

package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.actions.Attack;
import com.jnv.betrayal.dungeon.effects.actions.Defend;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.lobby.inventory.DungeonInventory;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.resources.FontManager;
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
		panels.addActor(createPanel("Done", FontManager.getFont70(), Panel.top, new Runnable() {
			public void run() {
				List<Card> dest = new ArrayList<Card>(field.getCardsSelected());
				if (eventType == EventType.ATTACK) {
					Effect effect = new Attack(field.getCurrentCard(), dest);
					field.roundManager.addEvent(effect, effect.getStartType());
				} else {
					Effect effect = new Defend(field.getCurrentCard(), dest);
					field.roundManager.addEvent(effect, effect.getStartType());
				}
				field.turnManager.nextTurn();
				field.endSelectMode();
			}
		}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			public void run() {
				draw();
				field.endSelectMode();
			}
		}));
	}

	@Override
	public void draw() {
		panels.clearChildren();
		panels.addActor(createGrayPanel("Items", FontManager.getDarkFont70(), Panel.bottomLeft));
		panels.addActor(createGrayPanel("Attack", FontManager.getDarkFont70(), Panel.topLeft));
		panels.addActor(createGrayPanel("Defend", FontManager.getDarkFont70(), Panel.topRight));
		panels.addActor(createGrayPanel("Flee", FontManager.getDarkFont70(), Panel.bottomRight));
		panels.addAction(Actions.delay(1.5f, Actions.run(new Runnable() {
			@Override
			public void run() {
				panels.clearChildren();
				panels.addActor(createPanel("Items", FontManager.getFont70(), Panel.bottomLeft, new Runnable() {
					public void run() {
						new DungeonInventory(gsm.game, field.getCurrentCard());
						// todo change input to what is needed by the specific item
					}
				}));
				panels.addActor(createPanel("Attack", FontManager.getFont70(), Panel.topLeft, new Runnable() {
					public void run() {
						field.beginSelectMode(1);
						drawSelectBar(EventType.ATTACK);
					}
				}));
				panels.addActor(createPanel("Defend", FontManager.getFont70(), Panel.topRight, new Runnable() {
					public void run() {
						field.beginSelectMode(1);
						drawSelectBar(EventType.DEFEND);
					}
				}));
				panels.addActor(createPanel("Flee", FontManager.getFont70(), Panel.bottomRight, new Runnable() {
					@Override
					public void run() {
						new Confirmation(gsm.game, "Flee? 25% Chance") {
							@Override
							public void doAction() {
								if (PlayerCard.canFlee(1)) {
									Effect effect = new Flee(field.getCurrentCard());
									field.roundManager.addEvent(effect, effect.getStartType());
								} else {
									Effect effect = new FailedToFlee(field.getCurrentCard());
									field.roundManager.addEvent(effect, effect.getStartType());
								}
							}
						};
					}
				}));
			}
		})));
	}
}

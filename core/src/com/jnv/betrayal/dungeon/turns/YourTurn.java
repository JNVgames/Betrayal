package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.Field;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;
import com.jnv.betrayal.dungeon.effects.actions.Attack;
import com.jnv.betrayal.dungeon.effects.actions.Defend;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;
import com.jnv.betrayal.dungeon.effects.specials.PriestAttackSpecial;
import com.jnv.betrayal.dungeon.effects.specials.PriestDefenseSpecial;
import com.jnv.betrayal.dungeon.effects.specials.PriestHealSpecial;
import com.jnv.betrayal.dungeon.effects.specials.ThiefSpecial;
import com.jnv.betrayal.dungeon.effects.specials.WarriorSpecial;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.lobby.inventory.DungeonInventory;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class YourTurn extends Turn {

	private boolean isFirstAppearance = true;

	public YourTurn(Field field, Pool<Label> panelPool, Pool<Button> buttonPool, Group panels, Betrayal game) {
		super(field, panelPool, buttonPool, panels, game);
	}

	@Override
	public void draw() {
		panels.clearChildren();
		if (isFirstAppearance) {
			panels.addActor(createGrayPanel("Items", FontManager.getDarkFont70(), Panel.bottomLeft));
			panels.addActor(createGrayPanel("Attack", FontManager.getDarkFont70(), Panel.topLeft));
			panels.addActor(createGrayPanel("Defend", FontManager.getDarkFont70(), Panel.topRight));
			panels.addActor(createGrayPanel("Flee", FontManager.getDarkFont70(), Panel.bottomRight));
			panels.addAction(Actions.delay(1.5f, Actions.run(new Runnable() {
				@Override
				public void run() {
					drawMainBar();
				}
			})));
			isFirstAppearance = false;
		} else {
			drawMainBar();
		}
	}

	private void drawMainBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Items", FontManager.getFont70(), Panel.bottomLeft, new Runnable() {
			public void run() {
				new DungeonInventory(gsm.game, field.getCurrentCard());
				// todo change input to what is needed by the specific item
			}
		}));
		panels.addActor(createPanel("Attack", FontManager.getFont70(), Panel.topLeft, new Runnable() {
			public void run() {
				drawAttackBar();
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
						panels.clearChildren();
						panels.addActor(createGrayPanel("Attempting to flee...", FontManager.getDarkFont70(), Panel.full));
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

	private void drawAttackBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Normal Attack", FontManager.getFont50(), Panel.topLeft, new Runnable() {
			@Override
			public void run() {
				field.beginSelectMode(1);
				drawSelectBar(EventType.ATTACK);
			}
		}));
		panels.addActor(createPanel("Special Attack", FontManager.getFont50(), Panel.topRight, new Runnable() {
			@Override
			public void run() {
				switch (field.getClientCharacter().job.getJob()) {
					case WARRIOR:
						field.beginSelectMode(1);
						drawWarriorSpecialBar();
						break;
					case THIEF:
						field.beginSelectMode(1);
						drawThiefSpecialBar();
						break;
					case KNIGHT:
						field.beginSelectMode(2);
						drawKnightSpecialBar();
						break;
					case PRIEST:
						field.beginSelectMode(1);
						drawPriestSpecialBar();
						break;
				}
			}
		}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			@Override
			public void run() {
				drawMainBar();
			}
		}));
	}

	private void drawKnightSpecialBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Select up to 2\ntargets to defend.", FontManager.getFont50(),
				Panel.top, new Runnable() {
					@Override
					public void run() {
						if (doesTargetExist()) {
							doEvent(new Defend(field.getCurrentCard(),
									new ArrayList<Card>(field.getCardsSelected())), EventType.PRIEST_ATTACK_SPECIAL);
							field.endSelectMode();
						}
					}
				}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			@Override
			public void run() {
				field.endSelectMode();
				drawMainBar();
			}
		}));
	}

	private void drawPriestSpecialBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Boost target's\nattack", FontManager.getFont50(),
				Panel.topLeft, new Runnable() {
					@Override
					public void run() {
						if (doesTargetExist()) {
							// Deal damage to dest cards
							doEvent(new PriestAttackSpecial(field.getCurrentCard(),
									new ArrayList<Card>(field.getCardsSelected())), EventType.PRIEST_ATTACK_SPECIAL);
							field.endSelectMode();
						}
					}
				}));
		panels.addActor(createPanel("Boost target's\ndefense", FontManager.getFont50(),
				Panel.topRight, new Runnable() {
					@Override
					public void run() {
						if (doesTargetExist()) {
							// Deal damage to dest cards
							doEvent(new PriestDefenseSpecial(field.getCurrentCard(),
									new ArrayList<Card>(field.getCardsSelected())), EventType.PRIEST_DEFENSE_SPECIAL);
							field.endSelectMode();
						}
					}
				}));
		panels.addActor(createPanel("Heal target", FontManager.getFont50(),
				Panel.bottomLeft, new Runnable() {
					@Override
					public void run() {
						if (doesTargetExist()) {
							// Deal damage to dest cards
							doEvent(new PriestHealSpecial(field.getCurrentCard(),
									new ArrayList<Card>(field.getCardsSelected())), EventType.PRIEST_HEAL_SPECIAL);
							field.endSelectMode();
						}
					}
				}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				field.endSelectMode();
				drawMainBar();
			}
		}));
	}

	private void drawWarriorSpecialBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Deal 150% damage to target", FontManager.getFont50(), Panel.top, new Runnable() {
			@Override
			public void run() {
				if (doesTargetExist() && !isTargetSelf()) {
					// Deal damage to dest cards
					doEvent(new WarriorSpecial(field.getCurrentCard(),
							new ArrayList<Card>(field.getCardsSelected())), EventType.WARRIOR_SPECIAL);
					field.endSelectMode();
				}
			}
		}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			@Override
			public void run() {
				field.endSelectMode();
				drawMainBar();
			}
		}));
	}

	private void drawThiefSpecialBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Deal 50% damage to\ntarget as true damage", FontManager.getFont50(), Panel.top, new Runnable() {
			@Override
			public void run() {
				if (doesTargetExist() && !isTargetSelf()) {
					// Deal damage to dest cards
					doEvent(new ThiefSpecial(field.getCurrentCard(),
							new ArrayList<Card>(field.getCardsSelected())), EventType.THIEF_SPECIAL);
					field.endSelectMode();
				}
			}
		}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			@Override
			public void run() {
				field.endSelectMode();
				drawMainBar();
			}
		}));
	}

	private void drawSelectBar(final EventType eventType) {
		panels.clearChildren();
		panels.addActor(createPanel("Done", FontManager.getFont70(), Panel.top, new Runnable() {
			public void run() {
				List<Card> dest = new ArrayList<Card>(field.getCardsSelected());
				// If no cards are selected, show OKPopup saying to select a target
				if (doesTargetExist() && !isTargetSelf()) {
					if (eventType == EventType.ATTACK) {
						Effect effect = new Attack(field.getCurrentCard(), dest);
						doEvent(effect, effect.getStartType());
					} else {
						Effect effect = new Defend(field.getCurrentCard(), dest);
						doEvent(effect, effect.getStartType());
					}
					field.endSelectMode();
				}
			}
		}));
		panels.addActor(createPanel("Cancel", FontManager.getFont70(), Panel.bottom, new Runnable() {
			public void run() {
				draw();
				field.endSelectMode();
			}
		}));
	}

	private boolean doesTargetExist() {
		// If no cards are selected, show OKPopup saying to select a target
		if (field.getCardsSelected().isEmpty()) {
			new OKPopup(field.game, "Please select a target.");
			return false;
		}
		return true;
	}

	private boolean isTargetSelf() {
		if (field.getCardsSelected().size() == 1 && field.getCardsSelected().get(0) == field.getCurrentCard()) {
			new OKPopup(field.game, "You are not allowed\nto target yourself.");
			return true;
		}
		return false;
	}

	private void doEvent(Effect effect, EventType type) {
		field.roundManager.addEvent(effect, type);
		field.turnManager.nextTurn();
	}
}

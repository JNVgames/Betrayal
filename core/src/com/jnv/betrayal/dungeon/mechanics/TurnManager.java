package com.jnv.betrayal.dungeon.mechanics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.CardEffects;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

/**
 * Keeps track of user/party member/monster's turns.
 */
public class TurnManager {

	public final Group panels = new Group();
	private Field field;
	private GameStateManager gsm;
	private Pool<Label> panelPool;
	private Pool<Button> buttonPool;
	private BetrayalAssetManager res;

	public TurnManager(Field field) {
		this.field = field;
		gsm = field.gsm;
		res = field.res;
		buttonPool = new Pool<Button>() {
			public Button obtain() {
				Button tmp = super.obtain();
				ClickListener clickListener = tmp.getClickListener();
				// Remove all listeners except for the one needed for button to work
				for (EventListener listener : tmp.getListeners())
					if (listener != clickListener) tmp.removeListener(listener);
				return tmp;
			}

			protected Button newObject() {
				return new Button();
			}
		};
		panelPool = new Pool<Label>() {
			public Label obtain() {
				Label tmp = super.obtain();
				tmp.clearListeners();
				return tmp;
			}

			protected Label newObject() {
				return new Label(null, new Label.LabelStyle(FontManager.getFont(70)));
			}
		};
		drawMainMenu();
		field.addActor(panels);
	}

//	public ArrayList<Card> selectNextPlayerTurn() {
//
//	}

	private void drawMainMenu() {
		panels.clearChildren();
		panels.addActor(createPanel("Items", 70, Panel.bottomLeft, new Runnable() {
			public void run() {
				// todo stub
			}
		}));
		panels.addActor(createPanel("Attack", 70, Panel.topLeft, new Runnable() {
			public void run() {
				field.beginSelectMode(1);
				drawAttackBar();
			}
		}));
		panels.addActor(createPanel("Defend", 70, Panel.topRight, new Runnable() {
			public void run() {
				// todo stub
			}
		}));
		panels.addActor(createPanel("Flee", 70, Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.game, "Are you sure you want to flee?" + "\n20% Chance") {
					@Override
					public void doAction() {
						gsm.setState(GameStateManager.State.LOBBY);
					}
				};
			}
		}));
	}

	private void drawAttackBar() {
		panels.clearChildren();
		panels.addActor(createPanel("Done", 70, Panel.top, new Runnable() {
			public void run() {
				field.endSelectMode();
			}
		}));
		panels.addActor(createPanel("Cancel", 70, Panel.bottom, new Runnable() {
			public void run() {
				drawMainMenu();
			}
		}));
	}

	protected Group createGrayPanel(String panelText, int fontSize, Dimension dimension) {
		Group group = new Group();
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		Label.LabelStyle font = FontManager.getFont(fontSize);
		font.fontColor = Color.LIGHT_GRAY;
		panel.setStyle(font);
		panel.setBounds(dimension);
		panel.setAlignment(Align.center);
		panel.layout();
		Button border = buttonPool.obtain();
		border.setStyle(new Button.ButtonStyle(
				new TextureRegionDrawable(new TextureRegion(
						res.getTexture("actionBarButtonDown" + (int) dimension.getWidth() + "x"
								+ (int) dimension.getHeight()))), null, null));
		border.setBounds(dimension);
		group.addActor(panel);
		group.addActor(border);
		panels.addActor(group);
		return group;
	}

	/**
	 * Function to make creating 4-button action bars easier
	 *
	 * @param panelText text you want your button to say
	 * @param fontSize  text size
	 */
	protected Group createPanel(String panelText, int fontSize, Dimension dimension,
								final Runnable action) {
		Group group = new Group();
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		panel.setStyle(FontManager.getFont(fontSize));
		panel.setBounds(dimension);
		panel.setAlignment(Align.center);
		panel.layout();
		Button border = buttonPool.obtain();
		border.setStyle(new Button.ButtonStyle(
				new TextureRegionDrawable(new TextureRegion(
						res.getTexture("actionBarButtonUp" + (int) dimension.getWidth() + "x"
								+ (int) dimension.getHeight()))),
				new TextureRegionDrawable(new TextureRegion(
						res.getTexture("actionBarButtonDown" + (int) dimension.getWidth() + "x"
								+ (int) dimension.getHeight()))), null));
		border.setBounds(dimension);
		border.addListener(new InputListener(border) {
			@Override
			public void doAction() {
				action.run();
			}
		});
		group.addActor(panel);
		group.addActor(border);
		panels.addActor(group);
		return group;
	}

}

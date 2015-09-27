package com.jnv.betrayal.dungeon.phases;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

public abstract class Phase {

	public final Group group = new Group();
	public final Pool<Label> panelPool;
	public final Pool<Button> buttonPool;
	public final GameStateManager gsm;
	public final BetrayalAssetManager res;
	public final Field field;
	public final PhaseManager pm;

	protected Phase(PhaseManager pm) {
		this.pm = pm;
		field = pm.field;
		gsm = pm.field.gsm;
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
	}

	protected void clearActionBar() {
		// Clear action bar
		for (Actor actor : group.getChildren()) {
			if (actor instanceof Label) panelPool.free((Label) actor);
			if (actor instanceof Button) buttonPool.free((Button) actor);
		}
		group.clear();
	}

	protected Group createGrayPanel(String panelText, int fontSize, Dimension dimension) {
		Group group = new Group();
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle font = FontManager.getFont(fontSize);
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
		this.group.addActor(group);
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
		this.group.addActor(group);
		return group;
	}

	public abstract int getPhaseNum();
}

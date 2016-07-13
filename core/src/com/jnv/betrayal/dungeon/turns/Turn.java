package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

public abstract class Turn {

	protected Field field;
	protected Pool<Label> panelPool;
	protected Pool<Button> buttonPool;
	protected Group panels;
	protected GameStateManager gsm;
	protected BetrayalAssetManager res;

	protected Turn(Field field, Pool<Label> panelPool, Pool<Button> buttonPool, Group panels, Betrayal game) {
		this.field = field;
		this.panelPool = panelPool;
		this.buttonPool = buttonPool;
		this.panels = panels;
		res = game.res;
		gsm = game.gsm;
	}

	public abstract void draw();

	/**
	 * Function to make creating 4-button action bars easier
	 *
	 * @param panelText text you want your button to say
	 */
	protected Group createPanel(String panelText, Label.LabelStyle font, Dimension dimension,
								final Runnable action) {
		Group group = new Group();
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		panel.setStyle(font);
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

	protected Group createGrayPanel(String panelText, Label.LabelStyle font, Dimension dimension) {
		Group group = new Group();
		Label panel = panelPool.obtain();
		panel.setText(panelText);
		Label.LabelStyle fontStyle = font;
		fontStyle.fontColor = Color.DARK_GRAY;
		panel.setStyle(fontStyle);
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
}

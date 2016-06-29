/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.InputListener;

public abstract class Popup {

	protected Betrayal game;
	protected BetrayalAssetManager res;
	protected Actor mask;
	public final Group popup;
	private Runnable maskAction;

	protected Popup(Betrayal game) {
		this.game = game;
		res = game.res;
		popup = new Group();
		maskAction = new Runnable() {
			@Override
			public void run() {
			}
		};
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new InputListener(mask) {
			@Override
			public void doAction() {
				maskAction.run();
				remove();
			}
		});
		popup.addActor(mask);
		game.getStage().addActor(popup);
	}

	public void remove() {
		popup.remove();
	}

	protected void setMaskAction(Runnable r) {
		this.maskAction = r;
	}
}

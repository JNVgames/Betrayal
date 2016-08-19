package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.BetrayalAssetManager;
import com.jnv.betrayal.scene2d.Actor;
import com.jnv.betrayal.scene2d.InputListener;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public abstract class Popup {

	public final Group popup;
	protected Betrayal game;
	protected BetrayalAssetManager res;
	protected Actor mask;
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

	public Group getGroup() {
		return popup;
	}
}

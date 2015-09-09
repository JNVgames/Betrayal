package com.jnv.betrayal.popup;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class FriendsList extends Popup {

	private Image lobbyButton, background, background2;
	private Label title, title2;
	private Actor mask;

	public FriendsList(Betrayal game) {
		super(game);
		loadButtons();
	}

	public void loadButtons() {
		loadMask();
		loadBackground();
		loadReturnToLobbyButton();
		loadTitle();
	}

	private void loadMask() {
		mask = new Actor();
		mask.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		mask.addListener(new InputListener(mask) {
			@Override
			public void doAction() {
				removeFriendsList();
			}
		});
		stage.addActor(mask);
	}

	private void loadBackground() {
		background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(35, 100, Betrayal.WIDTH / 2 - 45, Betrayal.HEIGHT - 250);
		stage.addActor(background);

		background2 = new Image(res.getTexture("shop-background"));
		background2.layout();
		background2.setBounds(Betrayal.WIDTH / 2 + 10, 100, Betrayal.WIDTH / 2 - 45, Betrayal.HEIGHT - 250);
		stage.addActor(background2);
	}

	private void loadTitle() {
		title = new Label("Party", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 4);
		title.setY(Betrayal.HEIGHT - 250);
		stage.addActor(title);

		title2 = new Label("Friends", FontManager.getFont(40));
		title2.setHeight(100);
		title2.setX((Betrayal.WIDTH - title.getWidth()) / 4 * 3);
		title2.setY(Betrayal.HEIGHT - 250);
		stage.addActor(title2);
	}

	private void loadReturnToLobbyButton() {
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 390, 110, 180, 60);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				removeFriendsList();
			}
		});
		stage.addActor(lobbyButton);
	}

	private void removeFriendsList() {
		mask.remove();
		lobbyButton.remove();
		background.remove();
		background2.remove();
		title.remove();
		title2.remove();
	}
}

package com.jnv.betrayal.lobby.social;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

public class FriendsList extends Popup {

	public FriendsList(Betrayal game) {
		super(game);
		loadButtons();
	}

	public void loadButtons() {
		loadBackground();
		loadReturnToLobbyButton();
		loadTitle();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("shop-background"));
		background.layout();
		background.setBounds(35, 100, Betrayal.WIDTH / 2 - 45, Betrayal.HEIGHT - 250);
		popup.addActor(background);

		Image background2 = new Image(res.getTexture("shop-background"));
		background2.layout();
		background2.setBounds(Betrayal.WIDTH / 2 + 10, 100, Betrayal.WIDTH / 2 - 45, Betrayal.HEIGHT - 250);
		popup.addActor(background2);
	}

	private void loadTitle() {
		Label title = new Label("Party", FontManager.getFont(40));
		title.setHeight(100);
		title.setX((Betrayal.WIDTH - title.getWidth()) / 4);
		title.setY(Betrayal.HEIGHT - 250);
		popup.addActor(title);

		Label title2 = new Label("Friends", FontManager.getFont(40));
		title2.setHeight(100);
		title2.setX((Betrayal.WIDTH - title.getWidth()) / 4 * 3);
		title2.setY(Betrayal.HEIGHT - 250);
		popup.addActor(title2);
	}

	private void loadReturnToLobbyButton() {
		Image lobbyButton = new Image(res.getTexture("back-to-lobby"));
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 390, 110, 180, 60);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}
}

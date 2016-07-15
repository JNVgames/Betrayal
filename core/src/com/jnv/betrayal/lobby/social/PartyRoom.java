package com.jnv.betrayal.lobby.social;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.CreateRoomPopup;
import com.jnv.betrayal.popup.JoinRoomPopup;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Dimension;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Image;
import com.jnv.betrayal.scene2d.ui.Label;
import com.jnv.betrayal.scene2d.ui.ScrollPane;

import java.util.List;

public class PartyRoom extends Popup {

	public final Image partyBackground, lobbyButton, createRoom, leaveRoom, joinRoom;
	public final Label partyTitle;
	private Character currentCharacter;
	private Room room;

	public PartyRoom(Betrayal game) {
		super(game);
		currentCharacter = game.getCurrentCharacter();
		joinRoom = new Image(res.getTexture("ok"));
		createRoom = new Image(res.getTexture("create-room"));
		leaveRoom = new Image(res.getTexture("leave-room"));
		partyBackground = new Image(res.getTexture("shop-background"));
		partyTitle = new Label("", FontManager.getFont60());            //VINCENT DO NOT DELETE THIS. USING IT IN RELATION
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		loadStage();
	}

	public void loadStage() {
		loadBackground();
		loadReturnToLobbyButton();
		loadTitle();
		loadRoomButtons();
	}

	private void loadBackground() {
		partyBackground.layout();
		partyBackground.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		popup.addActor(partyBackground);

	}

	private void loadTitle() {
		partyTitle.layout();
		partyTitle.setBounds(new Dimension(partyBackground.getX() + partyBackground.getWidth() / 2,
				partyBackground.getTop() - 20 - partyTitle.getPrefHeight(), partyTitle.getPrefWidth(),
				partyTitle.getPrefHeight(), true, false));
		popup.addActor(partyTitle);


	}

	private void loadReturnToLobbyButton() {
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - lobbyButton.getWidth()) / 2 + 100, 110, 312, 100);
		lobbyButton.addListener(new InputListener(lobbyButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(lobbyButton);
	}

	private void loadRoomButtons() {
		createRoom.layout();
		createRoom.setBounds(142, partyTitle.getY() - 20, 200, 65);
		createRoom.addListener(new InputListener(createRoom) {
			@Override
			public void doAction() {
				new CreateRoomPopup(game, "Create Room") {
					@Override
					public void doAction() {
						doCreateRoom(getPasswordString());
					}
				};
			}
		});
		popup.addActor(createRoom);

		joinRoom.layout();
		joinRoom.setBounds(372, partyTitle.getY() - 220, 200, 65);
		joinRoom.addListener(new InputListener(joinRoom) {
			@Override
			public void doAction() {
				new JoinRoomPopup(game, "Join Room") {
					@Override
					public void doAction() {
						doJoinRoom(getPasswordString(), getRoomID());
					}
				};
			}
		});
		popup.addActor(joinRoom);

		leaveRoom.layout();
		leaveRoom.setBounds(372, partyTitle.getY() - 20, 200, 65);
		leaveRoom.addListener(new InputListener(leaveRoom) {
			@Override
			public void doAction() {
				doLeaveRoom();
				refresh();
			}
		});
		popup.addActor(leaveRoom);
	}

	public void refresh() {
		if (room.getRoomID() == -1) {
			createRoom.setColor(Color.WHITE);
			createRoom.setTouchable(Touchable.enabled);
			leaveRoom.setColor(Color.GRAY);
			leaveRoom.setTouchable(Touchable.disabled);
		}
		else {
			createRoom.setColor(Color.GRAY);
			createRoom.setTouchable(Touchable.disabled);
			leaveRoom.setColor(Color.WHITE);
			leaveRoom.setTouchable(Touchable.enabled);
		}
	}

	private void doLeaveRoom() {
		room.leaveRoom();


	}

	private void doCreateRoom(String password) {
		room = new Room(currentCharacter);
		room.connectToServer();
		room.createRoom(password);
		// Emit createRoom event

		// Send Character data

	}

	private void doJoinRoom(String password, int roomID) {
		room = new Room(currentCharacter);
		room.connectToServer();
		room.joinRoom(password, roomID);
	}

	/**
	 * Given a list of usernames, this method prints out every name in a column.
	 *
	 * @param listOfFriends list of usernames
	 */
	private void loadFriendsList(List<String> listOfFriends) {
		VerticalGroup verticalGroup = new VerticalGroup();
		for (String name : listOfFriends) {
			Label label = new Label(name, FontManager.getFont50());
			verticalGroup.addActor(label);
		}
		ScrollPane scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setScrollingDisabled(true, false);
		verticalGroup.align(Align.topLeft);
		verticalGroup.pad(0, 20, 0, 20);
		popup.addActor(scrollPane);
	}
}

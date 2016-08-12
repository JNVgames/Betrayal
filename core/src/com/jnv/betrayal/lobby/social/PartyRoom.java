package com.jnv.betrayal.lobby.social;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gamestates.Lobby;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.CreateRoomPopup;
import com.jnv.betrayal.popup.JoinRoomPopup;
import com.jnv.betrayal.popup.OKPopup;
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
		room = currentCharacter.getRoom();
		joinRoom = new Image(res.getTexture("join-room"));
		createRoom = new Image(res.getTexture("create-room"));
		leaveRoom = new Image(res.getTexture("leave-room"));
		partyBackground = new Image(res.getTexture("shop-background"));
		partyTitle = new Label("Online", FontManager.getFont40());            //VINCENT DO NOT DELETE THIS. USING IT IN RELATION
		lobbyButton = new Image(res.getTexture("back-to-lobby"));
		loadStage();

		refresh();
	}

	public void loadStage() {
		loadBackground();
		loadTitle();
		loadRoomButtons();
		loadReturnToLobbyButton();
	}

	private void loadBackground() {
		partyBackground.layout();
		partyBackground.setBounds(150, 200, Betrayal.WIDTH - 300, Betrayal.HEIGHT - 400);
		popup.addActor(partyBackground);
	}

	private void loadTitle() {
		partyTitle.setBounds(new Dimension(partyBackground.getX() + partyBackground.getWidth() / 2,
				partyBackground.getTop() - 20 - partyTitle.getPrefHeight(), partyTitle.getPrefWidth(),
				partyTitle.getPrefHeight(), true, false));
		popup.addActor(partyTitle);

		//add white line under
		com.jnv.betrayal.scene2d.ui.Image whiteLine = new com.jnv.betrayal.scene2d.ui.Image(res.getTexture("white-pixel"));
		whiteLine.setWidth(partyTitle.getWidth()+20);
		whiteLine.setHeight(2);
		whiteLine.setX(partyTitle.getX()-10);
		whiteLine.setY(partyTitle.getY() - whiteLine.getHeight());
		popup.addActor(whiteLine);

	}

	private void loadReturnToLobbyButton() {
		lobbyButton.layout();
		lobbyButton.setBounds((Betrayal.WIDTH - 312) / 2 ,leaveRoom.getY() - 180 , 312, 100);
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
		createRoom.setBounds((Betrayal.WIDTH - 312) / 2 , partyTitle.getY() - 120, 312, 100);
		createRoom.addListener(new InputListener(createRoom) {
			@Override
			public void doAction() {
				new CreateRoomPopup(game, "Create Room") {
					@Override
					public void doAction() {
						doCreateRoom(getPasswordString());
						remove();
						PartyRoom.this.remove();
					}
				};
			}
		});
		popup.addActor(createRoom);

		joinRoom.layout();
		joinRoom.setBounds((Betrayal.WIDTH - 312) / 2 , createRoom.getY() - 180, 312, 100);
		joinRoom.addListener(new InputListener(joinRoom) {
			@Override
			public void doAction() {
				new JoinRoomPopup(game, "Join Room") {
					@Override
					public void doAction() {
						// If room string is empty, show OKPopup saying enter a room number
						if (!isRoomIDFieldEmpty()) {
							doJoinRoom(getPasswordString(), getRoomID());
							remove();
							PartyRoom.this.remove();
						} else {
							new OKPopup(game, "Please enter\na room number");
						}
					}
				};
			}
		});
		popup.addActor(joinRoom);

		leaveRoom.layout();
		leaveRoom.setBounds((Betrayal.WIDTH - 312) / 2 , joinRoom.getY() - 180, 312, 100);
		leaveRoom.addListener(new InputListener(leaveRoom) {
			@Override
			public void doAction() {
				doLeaveRoom();
				remove();
				PartyRoom.this.remove();
			}
		});
		popup.addActor(leaveRoom);
	}

	public void refresh() {
		if (room.getRoomID() == -1) {
			createRoom.setColor(Color.WHITE);
			createRoom.setTouchable(Touchable.enabled);
			joinRoom.setColor(Color.WHITE);
			joinRoom.setTouchable(Touchable.enabled);
			leaveRoom.setColor(Color.GRAY);
			leaveRoom.setTouchable(Touchable.disabled);
		}
		else {
			createRoom.setColor(Color.GRAY);
			createRoom.setTouchable(Touchable.disabled);
			joinRoom.setColor(Color.GRAY);
			joinRoom.setTouchable(Touchable.disabled);
			leaveRoom.setColor(Color.WHITE);
			leaveRoom.setTouchable(Touchable.enabled);
		}
	}

	private void doLeaveRoom() {
		room.leaveRoom();
		refresh();
	}

	private void doCreateRoom(String password) {
		room.connectToServer();
		room.createRoom(password);
		refresh();
	}

	private void doJoinRoom(String password, int roomID) {
		room.connectToServer();
		room.joinRoom(password, roomID);
		refresh();
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

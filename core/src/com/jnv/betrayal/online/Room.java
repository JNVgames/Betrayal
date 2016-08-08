package com.jnv.betrayal.online;

import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.gamestates.Lobby;
import com.jnv.betrayal.popup.OKPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Room {

	private int roomID;
	private Character currentCharacter;
	private List<Character> characters = new ArrayList<Character>();
	private Socket socket;
	private Lobby lobby;

	public Room(Character character) {
		roomID = -1;
		currentCharacter = character;
	}

	public int getRoomID() {
		return roomID;
	}

	public void connectToServer() {
		try {
			// If socket is already connected, don't make a new socket
			if (socket == null || !socket.connected()) {
				socket = IO.socket("http://localhost:8080");
				socket.connect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		configSocket();
	}

	private void configSocket() {
		socket.on("roomCreated", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					roomID = data.getInt("roomID");
					System.out.println("Room ID: " + roomID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				characters.clear();
				characters.add(currentCharacter);
				refreshLobby();
				printCharacters();
			}
		}).on("joinedRoom", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				System.out.println("Someone joined the room!");
				JSONObject data = (JSONObject) args[0];
				List<Character> characters = new ArrayList<Character>();
				int counter = 0;
				try {
					JSONArray players = data.getJSONArray("players");
					while (!players.isNull(counter)) {
						Character c = new Character(currentCharacter.res);
						c.fromJson(players.getJSONObject(counter));
						c.preview.update();
						// If the character is currentCharacter, add currentCharacter to array instead
						if (c.getId() == currentCharacter.getId()) {
							characters.add(currentCharacter);
						} else {
							characters.add(c);
						}
						counter++;
					}
					roomID = data.getInt("roomID");

					// Emit the players back to server so server can update the list of players
					JSONObject emitData = new JSONObject();
					emitData.put("players", data.getJSONArray("players"));
					socket.emit("joinedRoom", emitData);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Room.this.characters = characters;
				refreshLobby();
				printCharacters();
			}
		}).on("readyChanged", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				System.out.println("readyChanged");
				JSONObject data = (JSONObject) args[0];
				try {
					for (Character character : characters) {
						if (data.getInt("playerID") == character.getId()) {
							character.setReady(data.getBoolean("ready"));
							break;
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				refreshLobby();
			}
		}).on("failedJoinRoom", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				if (lobby != null) {
					new OKPopup(lobby.getGame(), "Failed to join room");
				}
			}
		}).on("roomNull", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				if (lobby != null) {
					new OKPopup(lobby.getGame(), "Room doesn't exist.");
				}
			}
		}).on("startDungeonCountdown", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				//new OKPopup(lobby.getGame(),"GOING TO DUNGEON");

				if (lobby != null) {
					lobby.enterDungeonCountDown();
				}
			}
		}).on("enterDungeon", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				if (lobby != null) {
					lobby.getGSM().setState(GameStateManager.State.DUNGEON);
					lobby = null;
				}
			}
		}).on("playerLeftRoom", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];

				int indexToRemove = -1;
				try {
					for (int i = 0; i < characters.size(); i++) {
						if (characters.get(i).getId() == data.getInt("id")) {
							indexToRemove = i;
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// In case something weird happens and indexToRemove is still -1
				if (indexToRemove != -1) {
					characters.remove(indexToRemove);
				}
				refreshLobby();
			}
		});
	}

	private void refreshLobby() {
		if (lobby != null) {
			lobby.refresh();
		}
	}

	public void createRoom(String password) {
		currentCharacter.setReady(false);
		JSONObject data = new JSONObject();
		try {
			data.put("password", password);
			data.put("character", currentCharacter.toJson());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.emit("createRoom", data);
	}

	public void leaveRoom() {
		roomID = -1;
		characters.clear();
		currentCharacter.setReady(false);
		socket.disconnect();
	}

	public void joinRoom(String password, int roomID) {
		currentCharacter.setReady(false);
		JSONObject data = new JSONObject();
		try {
			data.put("password", password);
			data.put("roomID", roomID);
			data.put("character", currentCharacter.toJson());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.emit("joinRoom", data);
	}

	public void ready(boolean isReady) {
		JSONObject data = new JSONObject();
		currentCharacter.setReady(isReady);
		try {
			data.put("ready", isReady);
			data.put("playerID", currentCharacter.getId());
			data.put("room", roomID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Object[] readyData = {data};
		socket.emit("clientReady", readyData, new Ack() {
			@Override
			public void call(Object... args) {
				refreshLobby();
			}
		});
	}

	// Whenever someone makes a change to their character that changes their preview
	public void updateCharactersInfo() {
// todo
	}

	// Emit event to server on this character preview change
	public void updateServerCharacters() {
// todo

	}

	public void printCharacters() {
		System.out.print("Characters: ");
		for (Character character : characters) {
			System.out.print(character.getId() + ", ");
		}
		System.out.println();
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public Socket getSocket() {
		return socket;
	}
}

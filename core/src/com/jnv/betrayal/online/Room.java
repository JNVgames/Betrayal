package com.jnv.betrayal.online;

import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.gamestates.GameStateManager;
import com.jnv.betrayal.gamestates.Lobby;
import com.jnv.betrayal.popup.OKPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class Room {

	private int roomID;
	private Character currentCharacter;
	private List<Character> characters = new ArrayList<Character>();
	private Socket socket;
	private Lobby lobby;
	private int monsterID, monsterTier;
	private boolean isServerOnline;
	private static final boolean testLocal = false;
	private static final String ACTUAL_SERVER = "http://betrayal-server-jnvgames.herokuapp.com/";
	private static final String MY_SERVER = "http://localhost:8080";
	private static final int appVersion = 2;

	public Room(Character character) {
		roomID = -1;
		currentCharacter = character;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public void connectToServer() {
		isServerOnline = true;
		try {
			URL url;
			if (testLocal) url = new URL(MY_SERVER);
			else url = new URL(ACTUAL_SERVER);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
		} catch (Exception e) {
			isServerOnline = false;
		}
		try {
			// If socket is already connected, don't make a new socket
			if (socket == null || !socket.connected()) {
				if (testLocal) socket = IO.socket(MY_SERVER);
				else socket = IO.socket(ACTUAL_SERVER);
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
				} catch (JSONException e) {
					e.printStackTrace();
				}
				characters.clear();
				characters.add(currentCharacter);
				refreshLobby();
			}
		}).on("joinedRoom", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
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
					socket.emit("joinedRoom");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Room.this.characters = characters;
				refreshLobby();
			}
		}).on("readyChanged", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
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
				JSONObject data = (JSONObject) args[0];
				try {
					monsterID = data.getInt("monsterID");
					monsterTier = data.getInt("monsterTier");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (lobby != null) {
					lobby.enterDungeonCountDown();
				}
			}
		}).on("enterDungeon", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				if (lobby != null) {
					currentCharacter.setReady(false);
					ready(currentCharacter.isReady());
					lobby.getGSM().setState(GameStateManager.State.DUNGEON);
					lobby = null;
				}
			}
		}).on("playerLeftRoomInLobby", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				// Stop countdown
				for (Character character : characters) {
					character.setReady(false);
				}
				try {
					removeCharacter(((JSONObject) args[0]).getJSONObject("character").getInt("id"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				endLobbyCountdownTimer();
				refreshLobby();
			}
		}).on("stopDungeonCountdown", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				// Stop countdown
				endLobbyCountdownTimer();
				refreshLobby();
			}
		}).on("updateCharacter", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				try {
					updateCharacter(((JSONObject) args[0]).getJSONObject("character"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				refreshLobby();
			}
		}).on("removeCharacter", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					removeCharacter(data.getJSONObject("player").getInt("id"));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}).on("gameNotUpdated", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				if (lobby != null) {
					new OKPopup(lobby.getGame(), "Please update\nyour game.");
				}
			}
		});
	}

	public void removeCharacter(int id) {
		// Loop through and find character with id
		for (int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getId() == id) {
				characters.remove(i);
			}
		}
	}

	private void updateCharacter(JSONObject data) {
		try {
			// Extract id
			int id = data.getInt("id");

			// Loop through and find character with id
			for (Character character : characters) {
				if (character.getId() == id) {
					character.fromJson(data);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void refreshLobby() {
		if (lobby != null) {
			lobby.refresh();
		}
	}

	private void endLobbyCountdownTimer() {
		if (lobby != null) {
			lobby.endTimer();
		}
	}

	public void createRoom(String password) {
		currentCharacter.setReady(false);
		JSONObject data = new JSONObject();
		try {
			data.put("password", password);
			data.put("character", currentCharacter.toJson());
			data.put("appVersion", appVersion);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.emit("createRoom", data);
		if (!isServerOnline) {
			new OKPopup(lobby.getGame(), "Connection failed\n Try again later");
		}
	}

	public void leaveRoom() {
		roomID = -1;
		characters.clear();
		currentCharacter.setReady(false);
		endLobbyCountdownTimer();
		socket.disconnect();
	}

	public int getMonsterID() {
		return monsterID;
	}

	public int getMonsterTier() {
		return monsterTier;
	}

	public void joinRoom(String password, int roomID) {
		currentCharacter.setReady(false);
		JSONObject data = new JSONObject();
		try {
			data.put("password", password);
			data.put("roomID", roomID);
			data.put("character", currentCharacter.toJson());
			data.put("appVersion", appVersion);
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

	// Emit event to server on this character preview change
	public void updateServerCharacters() {
		if (socket != null && socket.connected()) {
			JSONObject data = new JSONObject();
			try {
				data.put("character", currentCharacter.toJson());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			socket.emit("characterChanged", data);
		}
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

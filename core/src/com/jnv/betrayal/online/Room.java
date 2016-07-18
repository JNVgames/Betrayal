package com.jnv.betrayal.online;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.Character;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Room implements Json.Serializable {

	private int roomID;
	private Character currentCharacter;
	private List<Character> characters = new ArrayList<Character>();
	private Socket socket;

	public Room(Character character) {
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
				characters.add(currentCharacter);
				printCharacters();
			}
		}).on("joinedRoom", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				System.out.println("Someone joined the room!");
				JSONArray data = (JSONArray) args[0];
				List<Character> characters = new ArrayList<Character>();
				int counter = 0;
				try {
					while (!data.isNull(counter)) {
						Character c = new Character(currentCharacter.res);
						c.fromJson(data.getJSONObject(counter));
						c.preview.update();
						// If the character is currentCharacter, add currentCharacter to array instead
						if (c.getId() == currentCharacter.getId()) {
							characters.add(currentCharacter);
						} else {
							characters.add(c);
						}
						counter++;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Room.this.characters = characters;
				printCharacters();
			}
		});
	}

	public void createRoom(String password) {
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
		JSONObject player = new JSONObject();
		try {
			player.put("id", currentCharacter.getId());
			player.put("roomID", roomID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.emit("leaveRoom", player);
		roomID = -1;
		// todo get disconnect to work
		//socket.disconnect();
	}

	public void joinRoom(String password, int roomID) {
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

	// Whenever someone makes a change to their character that changes their preview
	public void updateCharactersInfo() {

	}

	// Emit event to server on this character preview change
	public void updateServerCharacters() {

	}

	// When someone joins/leaves the room
	public void updateCharactersArray() {

	}

	// Remove room from server when last person leaves
	public void removeRoom() {

	}

	@Override
	public void write(Json json) {

	}

	@Override
	public void read(Json json, JsonValue jsonData) {

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
}

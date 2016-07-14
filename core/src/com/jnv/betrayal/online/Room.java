package com.jnv.betrayal.online;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.jnv.betrayal.character.Character;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Room implements Json.Serializable {

	private int roomID;
	private Character currentCharacter;
	private List<Character> characters;
	private Socket socket;

	public Room(Character character) {
		currentCharacter = character;
	}

	public Room(int roomID, List<Character> characters) {
		this.roomID = roomID;
		this.characters = characters;
	}

	public void connectToServer() {
		try {
			socket = IO.socket("http://localhost:8080");
			socket.connect();
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
				@Override
				public void call(Object... args) {
					System.out.println("Connected");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createRoom() {
		JSONObject data = new JSONObject();
		currentCharacter.write(data);
		socket.emit("createRoom", data);
	}

	public void joinRoom(int id) {

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

	// Check if room number is open
	private int findAvailableRoom() {
		return 0;
	}

	@Override
	public void write(Json json) {

	}

	@Override
	public void read(Json json, JsonValue jsonData) {

	}
}

package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.main.Betrayal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedDataHandler {
	Betrayal game;

	public SavedDataHandler(Betrayal game) {
		this.game = game;
	}

	public void load() {
		JSONArray alivePlayers = null;
		JSONArray deadPlayers = null;

		String s = readFile("game.sav");

		JSONObject data = null;
		try {
			data = new JSONObject(s);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		if (data == null)
			return;
		List<Character> aliveCharacters = new ArrayList<Character>();
		/*********************Getting Alive Players*********************/
		int counter = 0;
		try {
			alivePlayers = data.getJSONArray("alivePlayers");

			while (!alivePlayers.isNull(counter)) {
				Character c = new Character(game.res);
				c.fromJson(alivePlayers.getJSONObject(counter));
				c.preview.update();
				aliveCharacters.add(c);
				counter++;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		/*********************Getting Dead Players*********************/
		List<Character> deadCharacters = new ArrayList<Character>();
		counter = 0;
		try {
			deadPlayers = data.getJSONArray("deadPlayers");

			System.out.println(deadPlayers);
			while (!deadPlayers.isNull(counter)) {
				Character c = new Character(game.res);
				c.fromJson(deadPlayers.getJSONObject(counter));
				c.preview.update();
				deadCharacters.add(c);
				counter++;
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------LOADING---------------------");
		System.out.println(alivePlayers);
		game.loadCharacters(aliveCharacters, deadCharacters);
	}

	public boolean retreiveOpenFirstTime() {
		String s = readFile("game.sav");
		boolean b = false;

		if (s == "" )
			return false;
		else {
			JSONObject data = null;
			try {
				data = new JSONObject(s);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			try {
				b = data.getBoolean("firstTimeTrue");

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return b;
	}

	public void save() {
		JSONArray alivePlayers = new JSONArray();
		int counter = 0;
		for (Character character : game.characters) {
			try {
				alivePlayers.put(counter, character.toJson());
				counter++;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		JSONArray deadPlayers = new JSONArray();
		counter = 0;
		for (Character character : game.fools) {
			try {
				deadPlayers.put(counter, character.toJson());
				counter++;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		JSONObject o = new JSONObject();
		try {
			o.put("alivePlayers", alivePlayers);
			o.put("deadPlayers", deadPlayers);
			o.put("firstTimeTrue", true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		FileHandle file = Gdx.files.local("game.sav");
		file.writeString(Base64Coder.encodeString(o.toString()), false);
		System.out.println("----------SAVING--------");
		System.out.println(alivePlayers);


		//todo put this all in key and value into the json file
		for (int i = 0; i < game.characters.size(); i++) {
			game.characters.remove(i);      //todo TESTING
		}
	}

	public static String readFile(String filename) {
		FileHandle file = Gdx.files.local(filename);
		if (file != null && file.exists()) {
			String s = file.readString();
			if (s != null) {
				return com.badlogic.gdx.utils.Base64Coder.decodeString(s);
			}
		}
		return "";
	}


}

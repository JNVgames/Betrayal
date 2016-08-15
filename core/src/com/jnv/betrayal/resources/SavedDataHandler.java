package com.jnv.betrayal.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.main.Betrayal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedDataHandler {
    Betrayal game;
    FileHandle file = Gdx.files.local("bin/Saved.json");
    public SavedDataHandler(Betrayal game) {
        this.game = game;
    }

    private void load( JSONObject object) {
        String s = Base64Coder.decodeString(file.readString());
        JSONObject data = data = new JSONObject();
        List<Character> aliveCharacters = new ArrayList<Character>();
        /*********************Getting Alive Players*********************/
        int counter = 0;
        try {
            JSONArray alivePlayers = data.getJSONArray("alivePlayers");

            System.out.println(alivePlayers);
            while (!alivePlayers.isNull(counter)) {
                Character c = new Character(game.res);
                c.fromJson(alivePlayers.getJSONObject(counter));
                c.preview.update();
                aliveCharacters.add(c);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*********************Getting Dead Players*********************/
        List<Character> deadCharacters = new ArrayList<Character>();
        counter = 0;
        try {
            JSONArray deadPlayers = data.getJSONArray("deadPlayers");

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

        game.loadCharacters(aliveCharacters, deadCharacters);

    }
    public boolean retreiveOpenFirstTime(){
        return false;       //todo change
    }

    public void save(){
        JSONArray alivePlayers = new JSONArray();
        int counter = 0;
        for (Character character : game.characters){
            try {
                alivePlayers.put(counter,character.toJson());
                counter++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONArray deadPlayers = new JSONArray();
        counter = 0;
        for (Character character : game.fools){
            try {
                deadPlayers.put(counter,character.toJson());
                counter++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        boolean openFirstTIme = true;       //will always be true when you need to save

        JSONObject o = new JSONObject();
        try {
            o.put("alivePlayers",alivePlayers);
            o.put("deadPlayers", deadPlayers);
            o.put("openFirstTime", openFirstTIme);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        file.writeString(Base64Coder.encodeString(o.toString()),false);

        //todo put this all in key and value into the json file

    }



}

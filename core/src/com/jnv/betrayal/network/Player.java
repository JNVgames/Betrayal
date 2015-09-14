package com.jnv.betrayal.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Player implements Json.Serializable {
    private int playerID, currentCharacterID;
    private static final String GET_PLAYER_ID_URL = "https://betrayal-backend.herokuapp.com/new_player";
    //private static final String GET_PLAYER_ID_URL = "http://localhost:5000/new_player";

    public final List<Character> characters;
    private Character currentCharacter;
    private String username;

    public Player() {
        characters = new ArrayList<Character>();
    }

    public void setPlayerID() {
        FindPlayerIDTask task = new FindPlayerIDTask();
    }

    /*
     * Class used to asynchronously send an HTTP GET request and check if player ID matches one on
     * the server. If not, creates a new player ID and sends a POST request to the server
     */
    private class FindPlayerIDTask implements Net.HttpResponseListener {
        final FileHandle idFile = Gdx.files.local("id.sav"); //File that stores the player's ID

        public FindPlayerIDTask() {

            final String requestContent;
            if(idFile.exists()) {
                //playerID = Integer.parseInt(idFile.readString());
                Gdx.app.log("Player", "idFile = " + playerID);
            } else {
                //If there is no local ID file, send a -1
                requestContent = "-1";
                Timer.Task task = new Timer.Task() {
                    @Override
                    public void run() {
                        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
                        request.setUrl(GET_PLAYER_ID_URL);
                        request.setContent(requestContent);
                        Gdx.app.log("FindPlayerIDTask", "Http Post request content: " + requestContent);
                        Gdx.net.sendHttpRequest(request, FindPlayerIDTask.this);
                    }
                };

                task.run();
            }

        }
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            //Returns 1 means that ID exists in db
            String response = httpResponse.getResultAsString();
            Gdx.app.log("Player.java", "Http Response: " + response);
            idFile.writeString(response, false);
            playerID = Integer.parseInt(response);
        }
        @Override
        public void failed(Throwable t) {
            Gdx.app.error("Player.java", "Http response listener failed");
            t.printStackTrace();
        }

        @Override
        public void cancelled() {
            Gdx.app.log("Player.java", "Http Request cancelled.");
        }
    }

	public String toJson() {
		Json json = new Json();
        return json.prettyPrint(this);
	}

    // Getters
    public Character getCurrentCharacter() { return currentCharacter; }

    public int getPlayerID() {
        return this.playerID;
    }

    // Setters
    public void setCurrentCharacter(Character character) {
        currentCharacter = character;
        currentCharacterID = characters.indexOf(character);
    }

    public void setCurrentCharacterIndex(int currentCharacterID) {
        this.currentCharacterID = currentCharacterID;
    }

    public void addCharacter(Character character) {
        characters.add(character);
        currentCharacter = character;
    }

    public boolean deleteCharacter(Character character) {
        return characters.remove(character);
    }

    // Json methods
    public void write(Json json) {
        json.writeField(this, "playerID", Integer.class);
        json.writeField(this, "currentCharacterID", Integer.class);
		json.writeObjectStart("allCharacters");
        // Ask jarnin about all characters
		for (Character character : characters) {
            character.write(json);
        }
		json.writeObjectEnd();
    }

    public void read(Json json, JsonValue jsonData) {

    }
}

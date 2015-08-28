package com.jnv.betrayal.Network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int playerID;
    private static final String GET_PLAYER_ID_URL = "https://betrayal-backend.herokuapp.com/get_player";
    //private static final String GET_PLAYER_ID_URL = "http://localhost:5000/get_player";

    /*
     * If array this was private, a getter would get this array and other classes would
     * still be able to edit it. Either way, this array will function as a public array
     */
    public List<Character> characters;
    public Character currentCharacter;
    public String username;

    public Player() {
        characters = new ArrayList<Character>();
    }

    public void setPlayerID() {
        FindPlayerIDTask task = new FindPlayerIDTask();
    }

    public Character getCurrentCharacter() {
        return this.currentCharacter;
    }
    public void addCharacter(Character character) {
        characters.add(character);
        currentCharacter = character;
    }

    /*
     * Class used to asynchronously send an HTTP GET request and check if player ID matches one on
     * the server. If not, creates a new player ID and sends a POST request to the server
     */
    private class FindPlayerIDTask implements Net.HttpResponseListener {

        public FindPlayerIDTask() {
            final FileHandle idFile = Gdx.files.local("id.sav"); //File that stores the player's ID
            final String requestContent;
            if(idFile.exists()) {
                requestContent = idFile.readString();
            } else {
                //If there is no local ID file, send a -1
                requestContent = "-1";
            }
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
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            //Returns 1 means that ID exists in db
            String response = httpResponse.getResultAsString();
            Gdx.app.log("Player.java", "Http Response: " + response);
            playerID = Integer.getInteger(response);
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
}

package com.jnv.betrayal.Network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Timer;
import com.jnv.betrayal.entities.Character;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private PlayerID playerID;
    private static final String GET_PLAYER_ID_URL = "https://betrayal-backend.herokuapp.com/get_player";

    /*
     * If array this was private, a getter would get this array and other classes would
     * still be able to edit it. Either way, this array will function as a public array
     */
    public List<Character> characters = new ArrayList<Character>();
    public Character currentCharacter;
    private String username;

    public Player() {

    }

    public Character getCurrentCharacter() {
        return this.currentCharacter;
    }

    /*
     * Class used to asynchronously send an HTTP GET request and check if player ID matches one on
     * the server. If not, creates a new player ID and sends a POST request to the server
     */
    private class FindPlayerID implements Net.HttpResponseListener {

        public FindPlayerID() {
            PlayerID id;
            Timer timer = new Timer();
            final FileHandle idFile = Gdx.files.local("id.sav"); //File that stores the player's ID

            //If the file exists load it and search db for matching ID
            if(idFile.exists()) {
                Timer.Task task = new Timer.Task() {
                    @Override
                    public void run() {
                        String uniqueID = idFile.readString();
                        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
                        request.setUrl(GET_PLAYER_ID_URL);

                        Json json = new Json();

                        request.setContent(json.toJson(uniqueID));
                        Gdx.app.log("Player.java", "uniqueID in json: " + request.getContent());
                        Gdx.net.sendHttpRequest(request, FindPlayerID.this);
                        Gdx.app.log("FindPlayerID", "Sending Http Request");

                    }
                };
            } else {
                //If the file does not exist, send nothing to the backend and backend will return
                //with a new ID for the user. Write it to the local file
                Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
                request.setUrl(GET_PLAYER_ID_URL);
                request.setContent(""); //Send empty string literal
                Gdx.net.sendHttpRequest(request, FindPlayerID.this);
            }

        }
        @Override
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            Gdx.app.log("Player.java", "handleHttpResponse");
            //Returns 1 means that ID exists in db
            if(httpResponse.getResultAsString() == "1") {
                Gdx.app.log("Player.java", "http response = 1");
            }

            //Returns -1 means that ID does not exist in db
            else if(httpResponse.getResultAsString() == "-1") {
                Gdx.app.log("Player.java", "http response = -1");
            }

            else {
                Gdx.app.log("Player.java", "http response = " + httpResponse.getResultAsString());
            }
        }

        @Override
        public void failed(Throwable t) {
            t.printStackTrace();
        }

        @Override
        public void cancelled() {
            Gdx.app.log("Player.java", "Http Request cancelled.");
        }
    }
}

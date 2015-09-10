package com.jnv.betrayal.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by jarnin on 9/1/15.
 */
public class PlayerStateHandler implements Net.HttpResponseListener {
    private static final String GET_PLAYER_ID_URL = "";


    private static final String OFFLINE_URL = "https://betrayal-backend.herokuapp.com/player/offline";
    private static final String ONLINE_URL = "https://betrayal-backend.herokuapp.com/player/online";
    private Player player;

    public PlayerStateHandler(Player player) {
        this.player = player;
    }

    public void goOffline(int playerID) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.PUT);
        request.setUrl(OFFLINE_URL);
        request.setContent(Integer.toString(player.getPlayerID()));
        Gdx.net.sendHttpRequest(request, PlayerStateHandler.this);
        Gdx.app.log("PlayerStateHandler", "SendHttpRequest to go offline");
    }

    public void goOnline(int playerID) {
        Timer timer = new Timer();
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.PUT);
                request.setUrl(ONLINE_URL);
                request.setContent(Integer.toString(player.getPlayerID()));
                Gdx.net.sendHttpRequest(request, PlayerStateHandler.this);
                Gdx.app.log("PlayerStateHandler", "SendHttpRequest to go online");
            }
        };

        timer.scheduleTask(task, 5);

    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        String response = httpResponse.getResultAsString();
        if(response.equals("1")) {
            Gdx.app.log("PlayerStateHandler", "Http response success");
        } else {
            Gdx.app.log("PlayerStateHandler", "Http response failed: " + response);
        }
    }

    @Override
    public void failed(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {
        Gdx.app.log("PlayerStateHandler", "Http request goOffline cancelled");
    }
}

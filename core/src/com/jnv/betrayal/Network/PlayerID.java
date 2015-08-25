package com.jnv.betrayal.Network;

import java.util.UUID;

/**
 * Created by jarnin on 8/20/15.
 */
public class PlayerID {
    private UUID playerID;

    public PlayerID() {
        playerID = UUID.randomUUID();
    }

    public UUID getPlayerID() {
        return this.playerID;
    }
}

package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.Player;

/**
 * Created by swandelow on 5/28/16.
 */
public class PlayerIsInRoomRule extends Rule {

    private Player player;
    private String roomName;

    public PlayerIsInRoomRule(Player player, String roomName) {
        this.player = player;
        this.roomName = roomName;
    }

    @Override
    public boolean doVerify() {
        return player.getCurrentStage().equalsIgnoreCase(roomName);
    }
}

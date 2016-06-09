package ar.fiuba.tdd.tp.games.rules;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Player;

/**
 * Created by swandelow on 5/28/16.
 */
public class PlayerIsInRoomRule extends Rule {

    private ConcreteGame game;
    private String roomName;

    public PlayerIsInRoomRule(ConcreteGame game, String roomName) {
        this.roomName = roomName;
        this.game = game;
    }

    @Override
    public boolean doVerify() {
        return this.game.getPlayer().getCurrentStage().equalsIgnoreCase(roomName);
    }
}

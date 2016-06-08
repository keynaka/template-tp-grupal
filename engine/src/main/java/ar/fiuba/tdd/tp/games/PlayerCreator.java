package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebass on 07/06/16.
 */
public class PlayerCreator {

    private String playerName = "player";
    private String initialRoom;
    private Map<String, String> states = new HashMap<>();
    private Collection<Item> items = new ArrayList<>();

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setInitialRoom(String initialRoom) {
        this.initialRoom = initialRoom;
    }

    public void setStates(Map<String, String> states) {
        this.states = states;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Player create() {
        Player player = new Player();
        player.setName(this.playerName);
        player.setCurrentStage(this.initialRoom);
        // agrego items al inventario
        this.items.stream().forEach(item -> player.addToInventory(item));
        // agrego los estados al player
        this.states.entrySet().stream().forEach(entry -> player.addState(entry.getKey(), entry.getValue()));
        return player;
    }
}

package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.Door;
import ar.fiuba.tdd.tp.games.Item;

/**
 * Created by swandelow on 4/22/16.
 */
public class Key extends Item {

    public Key() {
        super("key", "it's a key.");
    }

    public void openDoor(Door door) {
        door.open();
    }
}

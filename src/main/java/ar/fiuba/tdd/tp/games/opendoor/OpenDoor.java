package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor extends AbstractOpenDoor {

    public OpenDoor() {
        super("OpenDoor", "You enter room 2. You won the game!");
    }

    @Override
    protected void registerKnownActions() {
        super.registerKnownActions();
        this.addKnownActions();
    }

    private String openAction(String itemName) {
        Item item = this.room.getItem(itemName);
        if (this.door.equals(item)) {
            return this.openDoor();
        }
        return "Invalid item.";
    }

    @Override
    protected void buildRoom() {
        super.buildRoom();
        this.room.addItem(this.key);
    }

    private void addKnownActions() {
        this.knownActions.put(Action.OPEN, (itemName) -> this.openAction(itemName));
    }
}


package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Fede on 07/05/2016.
 */
public class OpenDoor2New extends AbstractOpenDoorNew {

    private ItemContainer box;

    public OpenDoor2New() {
        super("OpenDoor2", "You enter room 2. You won the game!");
    }

    @Override
    protected void registerKnownActions() {
        super.registerKnownActions();
        this.addKnownActions();

    }

    private void addKnownActions() {
        this.knownActions.put(Action.OPEN, (itemName, args) -> openAction(itemName));
        this.knownActions.put(Action.EXAMINE, (itemName, args) -> this.examineItem(itemName));

    }

    private String openAction(String itemName) {
        Item item = this.room.getItem(itemName);
        String result;
        if (this.door.equals(item)) {
            return this.openAction();
        }
        if (this.box.equals(item)) {
            this.box.getEstados().remove(State.CLOSED);
            this.box.getEstados().add(State.OPEN);
            Collection<Item> extractedItems = this.box.extractAll();
            for (Item extractedItem : extractedItems) {
                this.room.addItem(extractedItem);
            }
            return "The box is opened!";
        }
        return "Open action not executed. Invalid item.";
    }

    @Override
    protected void buildRoom() {
        super.buildRoom();
        this.box = new ItemContainer("box", "You can open/close the box.", 1);
        this.box.addItem(this.key);
        this.room.addItem(this.box);
    }

    private String examineItem(String itemName) {
        Optional<Item> item = Optional.ofNullable(this.room.getItem(itemName));
        return item.isPresent() ? item.get().getDescription() : "Item not found.";
    }
}

package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.Key;
import ar.fiuba.tdd.tp.games.items.LockedDoor;

/**
 * Created by swandelow on 4/23/16.
 */
public abstract class AbstractOpenDoor /*extends AbstractGame*/ {
/*
    protected Character character;
    protected Stage room;
    protected LockedDoor door;
    protected Item key;


    protected AbstractOpenDoor(String gameName, String endGameMessage) {
        super(gameName, endGameMessage);
    }

    @Override
    public void doStart() {
        this.createTheCharacter();
        this.buildRoom();
    }

    @Override
    public boolean isFinished() {
        return !this.door.isClosed();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(Action.LOOK_AROUND, (itemName, args) -> this.room.lookAround());
        this.knownActions.put(Action.PICK, (itemName, args) -> this.pickAction(itemName));
    }

    protected String pickAction(String itemName) {
        Item item = this.room.getItem(itemName);
        if (this.key.equals(item)) {
            return this.pickKey(itemName);
        }
        return "Pick action not executed. Invalid item.";
    }

    protected void buildRoom() {
        this.room = new Stage();
        this.key = new Key();
        this.door = new LockedDoor(this.key.getName());
        this.room.addItem(this.door);
    }

    protected String openDoor() {
        if (this.hasTheCharacterTheKey()) {
            return this.door.openWithKey(this.key.getName());
        } else {
            return this.door.open();
        }
    }

    protected void createTheCharacter() {
        this.character = new Character();
    }

    private String pickKey(String keyName) {
        Item key = this.room.pickItem(keyName);
        this.character.getInventory().addItem(key);
        return "There you go!";
    }

    private boolean hasTheCharacterTheKey() {
        return this.character.hasItem(this.key.getName());
    }
    */
}

package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor extends AbstractGame {

    private Character character;
    private Stage room;
    private LockedDoor door;
    private Item key;

    public OpenDoor() {
        super("OpenDoor");
    }

    @Override
    public void doStart() {
        this.character = new Character();
        this.buildRoom();
    }

    @Override
    protected void buildKnownActions() {
        this.knownActions.put(Action.LOOK_AROUND, () -> this.room.lookAround());
    }


    @Override
    public String play(Command command) {
        String result = null;
        Item item;
        switch (command.getAction()) {
            case LOOK_AROUND:
                result = this.room.lookAround();
                break;
            case OPEN:
                result = this.openAction(command);
                break;
            case PICK:
                result = this.pickAction(command);
                break;
            default:
                result = "Unknown command.";
        }
        if (this.isFinished()) {
            result = "You enter room 2. You won the game!";
        }
        return result;
    }

    @Override
    public boolean isFinished() {
        return !this.door.isClosed();
    }

    @Override
    public String getDescription() {
        return null;
    }

    private String pickAction(Command command) {
        Item item = this.room.getItem(command.getItemName());
        if (this.key.equals(item)) {
            return this.pickKey(command.getItemName());
        }
        return "Invalid item.";
    }

    private String openAction(Command command) {
        Item item = this.room.getItem(command.getItemName());
        if (this.door.equals(item)) {
            return this.openDoor();
        }
        return "Invalid item.";
    }

    private void buildRoom() {
        this.room = new Stage();
        this.key = new Key();
        this.room.addItem(this.key);
        this.door = new LockedDoor(this.key.getName());
        this.room.addItem(this.door);
    }

    private boolean hasTheCharacterTheKey() {
        return this.character.hasItem(this.key);
    }

    private String openDoor() {
        if (this.hasTheCharacterTheKey()) {
            return this.door.openWithKey(this.key.getName());
        } else {
            return this.door.open();
        }
    }

    private String pickKey(String keyName) {
        Item key = this.room.pickItem(keyName);
        this.character.getInventory().addItem(key);
        return "There you go!";
    }
}


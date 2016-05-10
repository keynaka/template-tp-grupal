package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by Fede on 08/05/2016.
 */
public class AbstractOpenDoorNew extends AbstractGame {


    protected Player player;
    protected Stage room;
    protected Item door;
    protected Item key;

    protected AbstractOpenDoorNew(String gameName, String endGameMessage) {
        super(gameName, endGameMessage);
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(Action.PICK, (itemName, args) -> this.pickAction(itemName));
        this.knownActions.put(Action.OPEN, (itemName, args) -> this.openAction());
        this.knownActions.put(Action.LOOK_AROUND, (itemName, args) -> this.room.lookAround());

    }

    protected String openAction() {
        if (this.hasThePlayerTheItem(this.key)) {
            this.door.getEstados().remove(State.LOCKED);
            this.door.getEstados().add(State.OPEN);
            return "Open door.";
        } else {
            return "Ey! Where do you go?! Room 2 is locked.";
        }
    }

    private boolean hasThePlayerTheItem(Item item) {
        return this.player.hasItem(item.getName());
    }

    @Override
    public void doStart() {
        this.createThePlayer();
        this.buildRoom();
    }

    @Override
    public boolean isFinished() {
        return this.door.getEstados().contains(State.OPEN);
    }

    @Override
    public String getDescription() {
        return null;
    }

    protected void createThePlayer() {
        this.player = new Player();
    }

    protected String pickAction(String itemName) {
        Item item = this.room.getItem(itemName);
        if (this.key.equals(item)) {
            return this.pickKey(itemName);
        }
        return "Pick action not executed. Invalid item.";
    }

    private String pickKey(String keyName) {
        Item key = this.room.pickItem(keyName);
        this.player.getInventory().addItem(key);
        return "There you go!";
    }

    protected void buildRoom() {
        this.room = new Stage();
        this.key = new Item("key", "The key");
        this.door = new Item("door", "The door");
        this.room.addItem(this.door);
        this.door.getEstados().add(State.LOCKED);
    }
}

package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/21/16.
 */
public class Player {

    private Inventory inventory;
    private String currentStage;
    private CharacterState state;

    public Player() {
        this.inventory = new Inventory();
        this.state = CharacterState.HEALTHY;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public boolean hasItem(String itemName) {
        return this.inventory.contains(itemName);
    }

    public String getCurrentStage() {
        return this.currentStage;
    }

    public void setCurrentStage(String stageName) {
        this.currentStage = stageName;
    }

    public CharacterState getState() {
        return state;
    }

    public void modifyState(CharacterState state) {
        this.state = state;
    }

    public String pickFromStage(Stage stage, String itemName) {
        Item pickedItem = stage.pickItem(itemName);
        Collectible collectibleItem = null;
        try {
            collectibleItem = (Collectible) pickedItem;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        String resultPick = collectibleItem.pick(this);
        String resultAdd = this.inventory.addItem(pickedItem);
        return resultAdd.concat(" ").concat(resultPick);
    }

    public String openFromStage(Stage stage, String itemName) {
        Item item = stage.getItem(itemName);
        Openable openable = null;
        try {
            openable = (Openable) item;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        return openable.open(this);
    }

    public String talkTo(Stage currentRoom, String itemName, String message) {
        Item item = currentRoom.getItem(itemName);
        Talking talking = null;
        try {
            talking = (Talking) item;
        } catch (ClassCastException e) {
            throw new GameException("Unsupported action.");
        }
        return talking.talk(this, message);
    }

}

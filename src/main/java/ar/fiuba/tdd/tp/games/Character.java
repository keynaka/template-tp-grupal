package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/21/16.
 */
public class Character {

    private Inventory inventory;
    private String currentStage;
    private CharacterState state;

    public Character() {
        this.inventory = new Inventory();
        this.state = CharacterState.HEALTHY;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public boolean hasItem(Item item) {
        return this.inventory.contains(item.getName());
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
        Collectible collectibleItem = (Collectible) pickedItem;
        String resultPick = collectibleItem.pick(this);
        String resultAdd = this.inventory.addItem(pickedItem);
        return resultAdd.concat(" ").concat(resultPick);
    }

    public String openFromStage(Stage stage, String itemName) {
        Item item = stage.getItem(itemName);
        Openable openable = (Openable) item;
        return openable.open(this);
    }
}

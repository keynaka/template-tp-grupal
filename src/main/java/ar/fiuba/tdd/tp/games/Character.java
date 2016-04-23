package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/21/16.
 */
public class Character {

    private Inventory inventory;

    public Character() {
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void addToInventory(Item item) {
        this.inventory.addItem(item);
    }

    public boolean hasItem(Item item) {
        return this.inventory.contains(item);
    }
}

package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.Collection;
import java.util.List;

/**
 * Created by swandelow on 4/21/16.
 */
public class Inventory {

    private ItemContainer items;

    public Inventory() {
        this.items = new ItemContainer("inventory", "it's the inventory", 4);
    }

    public boolean contains(String itemName) {
        return this.items.contains(itemName);
    }

    public String addItem(Item item) {
        return this.items.addItem(item);
    }

    public Item dropItem(String itemName) {
        return this.items.extract(itemName);
    }

    public Collection<Item> dropAllItems() {
        return this.items.extractAll();
    }

    public int size() {
        return this.items.getSize();
    }

    public Item getItem(String itemName) {
        return this.items.getItem(itemName);
    }

    public Collection<Item> getAllItems() {
        return this.items.getAllItems();
    }
}

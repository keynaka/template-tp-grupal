package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.ArrayList;
import java.util.Collection;

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
}

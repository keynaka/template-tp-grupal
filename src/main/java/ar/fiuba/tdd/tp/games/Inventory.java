package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by swandelow on 4/21/16.
 */
public class Inventory {

    private Collection<Item> items;

    public Inventory() {
        this.items = new ArrayList<Item>();
    }

    public boolean contains(Item item) {
        return this.items.contains(item);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public int size() {
        return this.items.size();
    }
}

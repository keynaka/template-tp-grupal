package ar.fiuba.tdd.tp.engine.models;

import ar.fiuba.tdd.tp.engine.models.item.Item;

import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Nico on 21/05/2016.
 */
public class ItemBag {

    protected Set<Item> itemsBag;
    protected int maxItems; // -1 for no limit

    public ItemBag(int maxItems) {
        this.maxItems = maxItems;
    }

    public ItemBag() {
        this(-1);
    }

    public boolean isFull() {
        return (maxItems != -1 && maxItems == itemsBag.size());
    }

    public void addItem(Item item) {
        if (!this.hasItem(item) && !isFull()) {
            itemsBag.add(item);
        }
    }

    public boolean hasItem(Item item) {
        return itemsBag.contains(item);
    }

    public boolean hasItem(int idItem) {
        for (Item item : itemsBag) {
            if (item.getId().equals(idItem)) {
                return true;
            }
        }
        return false;
    }

    public Item fetchItem(int idItem) throws NoSuchElementException {
        if (!hasItem(idItem)) {
            throw new NoSuchElementException();
        }
        for (Item item : itemsBag) {
            if (item.getId().equals(idItem)) {
                return item;
            }
        }
        return null;
    }

    public Item removeItem(int idItem) throws NoSuchElementException {
        Item item = fetchItem(idItem);
        itemsBag.remove(item);
        return item;
    }
}

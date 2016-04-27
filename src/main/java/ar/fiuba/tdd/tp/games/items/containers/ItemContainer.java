package ar.fiuba.tdd.tp.games.items.containers;

import ar.fiuba.tdd.tp.games.items.Item;

import java.util.*;

/**
 * Created by swandelow on 4/23/16.
 */
public class ItemContainer extends Item {
    private String name;
    private int maxSize;
    protected Map<String, Item> items;

    public ItemContainer(String name, String description, int maxSize) {
        super(name, description);
        this.name = name;
        this.maxSize = maxSize;
        this.items = new HashMap<>();
    }

    public String addItem(Item item) {
        if (this.items.size() < this.maxSize) {
            this.items.put(item.getName(), item);
            return String.format("%s saved in %s.", item.getName(), this.name);
        } else {
            return String.format("It's not possible to add %s. Container is full.", item.getName());
        }
    }

    public Item extract(String itemName) {
        return this.items.remove(itemName);
    }

    public Collection<Item> extractAll() {
        Collection<Item> extractedItems = new ArrayList<>();
        Iterator<Item> it = this.items.values().iterator();
        while (it.hasNext()) {
            extractedItems.add(it.next());
            it.remove();
        }
        return extractedItems;
    }

    public boolean isFull() {
        return this.items.size() == this.maxSize;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    protected int getMaxSize() {
        return this.maxSize;
    }

    public Item getItem(String itemName) {
        return this.items.get(itemName);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean contains(String itemName) {
        Item item = this.items.get(itemName);
        return item != null;
    }

    public Collection<Item> getAllItems() {
        return this.items.values();
    }

    public int getSize() {
        return this.items.size();
    }
}

package ar.fiuba.tdd.tp.engine.models.item;

import ar.fiuba.tdd.tp.engine.models.Stage;

import java.util.Set;

/**
 * Created by Nico on 20/05/2016.
 */
public class Player {
    protected String name;
    protected Stage currentStage;
    protected Set<Item> itemsBag;
    protected int maxItems = -1; // No limit

    public Player(String name) {
        this.name = name;
    }

    public void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    public boolean hasItem(Item item) {
        return itemsBag.contains(item);
    }

    public boolean itemsBagIsFull() {
        return (maxItems != -1 && maxItems == itemsBag.size());
    }

    public void addItem(Item item) {
        if (!this.hasItem(item) && !itemsBagIsFull()) {
            itemsBag.add(item);
        }
    }

    public void dropItem(Item item) {
        if (this.hasItem(item)) {
            this.itemsBag.remove(item);
        }
    }
}

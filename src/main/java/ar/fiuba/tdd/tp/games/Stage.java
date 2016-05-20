package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.*;

/**
 * Created by swandelow on 4/21/16.
 */
public class Stage extends GameObject {

    private static final int DEFAULT_STAGE_SIZE = 20;

    private String name;

    private ItemContainer itemContainer;

    public Stage() {
        this.name = "room";
        this.itemContainer = new ItemContainer(this.name, "", DEFAULT_STAGE_SIZE);
    }

    public Stage(String name) {
        this();
        this.name = name;
        this.itemContainer = new ItemContainer(name, "", DEFAULT_STAGE_SIZE);
    }

    public String getName() {
        return this.name;
    }

    public void addItem(Item item) {
        this.itemContainer.addItem(item);
    }

    public void addItems(Item... items) {
        for (Item item : items) {
            this.addItem(item);
        }
    }

    public Item pickItem(String itemName) {
        return this.itemContainer.extract(itemName);
    }

    public String lookAround() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Items in the %s: ", this.getName()));
        Iterator<Item> it = itemContainer.getAllItems().iterator();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            if (it.hasNext()) {
                sb.append(", ");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public Iterator lookAroundIt() {

        Iterator<Item> it = itemContainer.getAllItems().iterator();
        return it;
    }

    public Item getItem(String itemName) {
        return this.itemContainer.getItem(itemName);
    }

    public ItemContainer getItemContainer() {
        return itemContainer;
    }

}

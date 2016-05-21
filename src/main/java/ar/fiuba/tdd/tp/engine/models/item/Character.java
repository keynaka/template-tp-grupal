package ar.fiuba.tdd.tp.engine.models.item;

import java.util.Set;

/**
 * Created by Nico on 20/05/2016.
 */
public class Character implements IIdentificable<String> {
    protected String name;
    protected Set<Item> itemsBag;

    public Character(String name) {
        setId(name);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return getName();
    }

    public void setId(String id) {
        name = id;
    }

    public boolean hasItem(Item item) {
        return itemsBag.contains(item);
    }

    public void giveItem(Item item) {
        if (!this.hasItem(item)) {
            itemsBag.add(item);
        }
    }
}

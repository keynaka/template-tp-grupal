package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ActionFunction;
import ar.fiuba.tdd.tp.games.traits.AbstractTrait;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swandelow on 4/21/16.
 */
public class Item {

    protected String name;
    protected String description;
    private AbstractTrait trait;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Item(String name, String description, AbstractTrait trait) {
        this.name = name;
        this.description = description;
        this.trait = trait;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String process(Action action) {
        return this.trait.execute(action, this.name, "");
    }

    public String process(Action action, String args) {
        return this.trait.execute(action, this.name, args);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return this.getName().equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }
}

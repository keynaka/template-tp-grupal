package ar.fiuba.tdd.tp.games.items.containers;

import ar.fiuba.tdd.tp.games.Openable;
import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/23/16.
 */
public class Box extends ItemContainer implements Openable {

    private State state;

    public static Box createEmptyAndClosed(int size) {
        Box box = new Box(size);
        box.close();
        return box;
    }

    public static Box createWithItemsAndClosed(int size, Item... items) {
        if (items.length <= size) {
            Box box = new Box(size);
            for (Item item : items) {
                box.addItem(item);
            }
            box.close();
            return box;
        }
        throw new GameException("Error trying to create box. Size lower than amount of items.");
    }

    public Box(int size) {
        super("box", "You can open/close the box.", size);
        this.state = State.OPEN;
    }

    public String close() {
        if (this.isClosed()) {
            return String.format("%s it's already closed.", this.getName());
        } else {
            this.state = State.CLOSED;
            return String.format("Closed %s.", this.getName());
        }
    }

    public String open() {
        if (this.isClosed()) {
            this.state = State.OPEN;
            return String.format("Open %s.", this.getName());
        } else {
            return String.format("%s it's already open.", this.getName());
        }
    }

    @Override
    public String open(Player player) {
        return this.open();
    }

    public boolean isClosed() {
        return State.CLOSED.equals(this.state);
    }


    @Override
    public String addItem(Item item) {
        if (this.isClosed()) {
            return String.format("%s it's closed. You can't add items.", item.getName());
        } else {
            return super.addItem(item);
        }
    }

    @Override
    public Item extract(String itemName) {
        if (this.isClosed()) {
            throw new GameException(String.format("%s it's closed. You can't extract items.", this.getName()));
        } else {
            return super.extract(itemName);
        }

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

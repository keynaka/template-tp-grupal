package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Openable;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/22/16.
 */
public class Door extends Item implements Openable {

    private State state;

    public Door(State state) {
        super("door", "it's a door.");
        this.state = state;
    }

    public Door(String doorName, State state) {
        super(doorName, "it's a door.");
        this.state = state;
    }

    public boolean isClosed() {
        return State.CLOSED.equals(this.state);
    }

    public String open() {
        if (this.isClosed()) {
            this.state = State.OPEN;
            return "Open door.";
        } else {
            return "Door it's already open.";
        }
    }

    @Override
    public String open(Character character) {
        return this.open();
    }

    public String close() {
        if (this.isClosed()) {
            return "Door it's already closed.";
        } else {
            this.state = State.CLOSED;
            return "Closed door.";
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

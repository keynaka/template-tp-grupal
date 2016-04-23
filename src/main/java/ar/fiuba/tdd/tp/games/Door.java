package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/22/16.
 */
public class Door extends Item {

    private State state;

    public Door(State state) {
        super("door", "it's a door.");
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
}

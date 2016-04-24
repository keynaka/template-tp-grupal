package ar.fiuba.tdd.tp.games.traits;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ActionFunction;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.traits.AbstractTrait;

/**
 * Created by swandelow on 4/24/16.
 */
public class TraitOpenable extends AbstractTrait {

    private State state;

    public TraitOpenable(State state) {
        this.state = state;
    }

    public boolean isClosed() {
        return State.CLOSED.equals(this.state);
    }

    protected String open(String itemName) {
        if (this.isClosed()) {
            this.state = State.OPEN;
            return String.format("Open %s.", itemName);
        } else {
            return String.format("%s is already open.", itemName);
        }
    }

    protected String close(String itemName) {
        if (this.isClosed()) {
            return String.format("%s is already closed.", itemName);
        } else {
            this.state = State.CLOSED;
            return String.format("Closed %s.", itemName);
        }
    }

    @Override
    protected void registerActions() {
        this.knownActions.put(Action.OPEN, (itemName, args) -> this.open(itemName));
        this.knownActions.put(Action.CLOSE, (itemName, args) -> this.close(itemName));
    }
}

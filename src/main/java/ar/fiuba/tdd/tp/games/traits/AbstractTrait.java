package ar.fiuba.tdd.tp.games.traits;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ActionFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by swandelow on 4/24/16.
 */
public abstract class AbstractTrait {

    protected Map<Action, TraitMethod> knownActions = new HashMap<>();

    protected AbstractTrait() {
        this.registerActions();
    }

    public Map<Action, TraitMethod> getKnownActions() {
        return this.knownActions;
    }

    public String execute(Action action, String itemName, String... args) {
        Optional<TraitMethod> method = Optional.ofNullable(this.getKnownActions().get(action));
        return method.isPresent() ? method.get().execute(itemName, args) : "Unknown action.";
    }

    abstract protected void registerActions();

}

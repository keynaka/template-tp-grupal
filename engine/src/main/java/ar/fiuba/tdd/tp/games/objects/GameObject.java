package ar.fiuba.tdd.tp.games.objects;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.exceptions.GameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Gonzalo on 16/05/2016.
 */
public abstract class GameObject {

    protected String name;
    protected Map<String, String> statesMap = new HashMap<>();
    protected Map<String, Behavior> behaviorMap = new HashMap<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addState(String key, String state) {
        this.statesMap.put(key, state);
    }

    public String getState(String stateKey) {
        return this.statesMap.get(stateKey);
    }

    public void addBehavior(Behavior behavior) {
        this.behaviorMap.put(behavior.getActionName(), behavior);
    }

    public Behavior getBehavior(String actionName) {
        return this.behaviorMap.get(actionName);
    }

    public String execute(ConcreteGame game, ActionOld action) {
        Behavior behavior = this.behaviorMap.get(action.getActionName());
        return behavior.execute(game);
    }

    public String executeAction(String actionName) {
        Optional<Behavior> behavior = Optional.ofNullable(this.behaviorMap.get(actionName));
        if (behavior.isPresent()) {
            return behavior.get().execute();
        }
        throw new GameException(String.format("%s does not support this command.", getName()));
    }

    public String executeActionWith(String actionName, String parameter) {
        Optional<Behavior> behavior = Optional.ofNullable(this.behaviorMap.get(actionName));
        if (behavior.isPresent()) {
            return behavior.get().executeWith(parameter);
        }
        throw new GameException(String.format("%s does not support this command.", getName()));
    }

}

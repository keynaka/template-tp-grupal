package ar.fiuba.tdd.tp.games.objects;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.behavior.Behavior;

import java.util.HashMap;
import java.util.Map;

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
        Behavior behavior = this.behaviorMap.get(actionName);
        return behavior.execute();
    }

    public String executeActionWith(String actionName, String parameter) {
        Behavior behavior = this.behaviorMap.get(actionName);
        return behavior.executeWith(parameter);
    }

}

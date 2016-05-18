package ar.fiuba.tdd.tp.games.objects;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.behavior.Behavior;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gonzalo on 16/05/2016.
 */
public abstract class GameObject {

    protected Map<String, String> statesMap = new HashMap<>();
    protected Map<String, Behavior> behaviorMap = new HashMap<>();


    public void addState(String key, String state) {
        this.statesMap.put(key, state);
    }

    public void addBehavior(Behavior behavior) {
        this.behaviorMap.put(behavior.getActionName(), behavior);
    }

    public String getState(String key) {
        return this.statesMap.get(key);
    }

    public Behavior getBehavior(String actionName) {
        return this.behaviorMap.get(actionName);
    }

    public String execute(ConcreteGame game, Action action) {
        Behavior behavior = this.behaviorMap.get(action.getActionName());
        return behavior.execute(game);
    }

}

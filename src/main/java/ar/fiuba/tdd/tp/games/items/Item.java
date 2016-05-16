package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Examinable;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by swandelow on 4/21/16.
 */
public class Item  extends GameObject implements Examinable {

    private static final String EXAMINE_MSG = "You can %s: %s.";

    protected String name;
    protected String description;
    protected Map<Action, String> supportedActions;
    private Map<String, Behavior> behaviors;

    protected List<State> estados;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.supportedActions = new HashMap<>();
        this.estados = new ArrayList<State>();
        this.behaviors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public Map<Action, String> getSupportedActions() {
        return this.supportedActions;
    }

    public void registerActionAndHelp(Action action, String help) {
        this.supportedActions.put(action, help);
    }

    /**
     * @return A message about supported actions and help. If not supports actions returns the description.
     */
    @Override
    public String examine() {
        if (this.supportedActions.isEmpty()) {
            return this.getDescription();
        } else {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Action, String> entry : supportedActions.entrySet()) {
                Action action = entry.getKey();
                String helpText = entry.getValue();
                sb.append(String.format(EXAMINE_MSG, action.getActionName(), helpText));
            }
            return sb.toString();
        }
    }


    public List<State> getEstados() {
        return estados;
    }

    public void addBehavior(Behavior behavior) {
        this.behaviors.put(behavior.getActionName(), behavior);
    }

    public String execute(ConcreteGame game, Action action) {
        Behavior behavior = this.behaviors.get(action.getActionName());
        return behavior.execute(game);
    }

}

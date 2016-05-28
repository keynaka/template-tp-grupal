package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.ActionOld;
import ar.fiuba.tdd.tp.games.Examinable;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swandelow on 4/21/16.
 */
public class Item extends GameObject implements Examinable {

    private static final String EXAMINE_MSG = "You can %s: %s.";

    protected String name;
    protected String description;
    protected Map<ActionOld, String> supportedActions;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.supportedActions = new HashMap<>();
        this.behaviorMap = new HashMap<>();
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

    public Map<ActionOld, String> getSupportedActions() {
        return this.supportedActions;
    }

    public void registerActionAndHelp(ActionOld action, String help) {
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
            for (Map.Entry<ActionOld, String> entry : supportedActions.entrySet()) {
                ActionOld action = entry.getKey();
                String helpText = entry.getValue();
                sb.append(String.format(EXAMINE_MSG, action.getActionName(), helpText));
            }
            return sb.toString();
        }
    }

}
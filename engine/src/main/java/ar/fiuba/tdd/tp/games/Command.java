package ar.fiuba.tdd.tp.games;

import java.io.Serializable;

/**
 * Created by swandelow on 4/22/16.
 */
public class Command implements Serializable {
    private ActionOld action;
    private String itemName;
    private String argument;

    public Command() {
        this(ActionOld.UNKNOWN_ACTION, "", "");
    }

    public Command(ActionOld action) {
        this(action, "", "");
    }

    public Command(ActionOld action, String itemName) {
        this(action, itemName, "");
    }

    public Command(ActionOld action, String itemName, String argument) {
        this.action = action;
        this.itemName = itemName;
        this.argument = argument;
    }

    public ActionOld getAction() {
        return this.action;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getArgument() {
        return this.argument;
    }

    public boolean isSystemAction() {
        return Command.checkSystemAction(this.action);
    }

    public static boolean checkSystemAction(ActionOld action) {
        for (ActionOld systemAction : Command.getSystemActions()) {
            if (systemAction == action) {
                return true;
            }
        }
        return false;
    }

    public static ActionOld checkSystemAction(String actionName) {
        for (ActionOld systemAction : Command.getSystemActions()) {
            if (actionName.contains(systemAction.getActionName())) {
                return systemAction;
            }
        }
        return ActionOld.UNKNOWN_ACTION;
    }

    public static ActionOld[] getSystemActions() {
        return new ActionOld[]{ActionOld._EXIT, ActionOld._HELP};
    }
}

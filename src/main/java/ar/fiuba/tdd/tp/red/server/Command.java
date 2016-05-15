package ar.fiuba.tdd.tp.red.server;

import ar.fiuba.tdd.tp.games.Action;

import java.io.Serializable;

/**
 * Created by swandelow on 4/22/16.
 */
public class Command implements Serializable {
    private Action action;
    private String itemName;
    private String argument;

    public Command() {
        this(Action.unknow(), "", "");
    }

    public Command(Action action) {
        this(action, "", "");
    }

    public Command(Action action, String itemName) {
        this(action, itemName, "");
    }

    public Command(Action action, String itemName, String argument) {
        this.action = action;
        this.itemName = itemName;
        this.argument = argument;
    }

    public Action getAction() {
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

    public static boolean checkSystemAction(Action action) {
        for (Action systemAction : Command.getSystemActions()) {
            if (systemAction == action) {
                return true;
            }
        }
        return false;
    }

    public static Action checkSystemAction(String actionName) {
        for (Action systemAction : Command.getSystemActions()) {
            if (actionName.contains(systemAction.getActionName())) {
                return systemAction;
            }
        }
        return Action.unknow();
    }

    public static Action[] getSystemActions() {
        return new Action[]{Action.exit(), Action.help()};
    }
}

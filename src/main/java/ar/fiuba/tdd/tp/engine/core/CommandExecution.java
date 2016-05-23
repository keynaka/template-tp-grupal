package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.core.actions.Action;
import ar.fiuba.tdd.tp.engine.core.rules.Rule;

/**
 * Created by Nico on 21/05/2016.
 */
public class CommandExecution {

    private String commandName;
    private Rule rule;
    private Action action;
    private String successMessage;

    public CommandExecution(String commandName, String successMessage) {
        this.commandName = commandName;
        this.successMessage = successMessage;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String run() {
        String result;
        if (rule.verify()) {
            action.doAction();
            result = successMessage;
        } else {
            result = "Error: action not verifies\n";
            System.out.print(result);
        }
        return result;
    }
}

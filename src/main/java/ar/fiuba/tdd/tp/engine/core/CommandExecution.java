package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.core.actions.Action;
import ar.fiuba.tdd.tp.engine.core.rules.Rule;

import java.util.ArrayList;

/**
 * Created by Nico on 21/05/2016.
 */
public class CommandExecution {

    private String commandName;
    private Rule rule;
    private ArrayList<Action> actions;
    private String successMessage;

    public CommandExecution(String commandName, String successMessage) {
        this.commandName = commandName;
        this.successMessage = successMessage;
        this.actions = new ArrayList<>();
    }

    public String getCommandName() {
        return commandName;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void addAction(Action action) {
        this.actions.add(action);
    }

    public String run() {
        String result;
        if (rule.verify()) {
            for (Action action : actions) {
                action.doAction();
            }
            result = successMessage;
        } else {
            result = rule.getErrorMessage()+"\n";
            System.out.print(result);
        }
        return result;
    }
}

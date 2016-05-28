package ar.fiuba.tdd.tp.games.behavior;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.rules.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by swandelow on 5/11/16.
 */
public class Behavior {

    private static final String DEFAULT_FAIL_MSG = "Unsuccessful execution. Execution condition not met.";

    private String actionName;
    private String resultMessage;
    private Predicate<ConcreteGame> executionCondition;
    private BehaviorAction behaviorAction;
    private BehaviorView view;
    private String failMessage;

    private Rule executionRule;
    private List<Action> actions = new ArrayList<>();

    public String execute(ConcreteGame game) {
        if (executionCondition.test(game)) {
            this.behaviorAction.execute(game);
            //return this.resultMessage;
            return this.view.print(game);
        }
//        throw new GameException("Unsuccessful execution. Execution condition not met.");
        return this.getFailMessage();
    }

    public String execute() {
        if (executionRule.verify()) {
            for (Action action : actions) {
                action.doAction();
            }
            return this.resultMessage;
        }
        return this.getFailMessage();
    }

    public void setView(BehaviorView view) {
        this.view = view;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    @Deprecated
    public void setExecutionCondition(Predicate<ConcreteGame> executionCondition) {
        this.executionCondition = executionCondition;
    }

    @Deprecated
    public void setBehaviorAction(BehaviorAction behaviorAction) {
        this.behaviorAction = behaviorAction;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        return this.failMessage != null ? this.failMessage : DEFAULT_FAIL_MSG;
    }

    public void setExecutionRule(Rule executionRule) {
        this.executionRule = executionRule;
    }

    public void addActions(Action action) {
        this.actions.add(action);
    }
}
package ar.fiuba.tdd.tp.games.behavior;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.actions.ParametizedAction;
import ar.fiuba.tdd.tp.games.rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by swandelow on 5/11/16.
 */
public class Behavior {

    private static final String DEFAULT_FAIL_MSG = "Unsuccessful execution. Execution condition not met.";

    private String actionName;
    private String resultMessage;
    private String alternativeResultMessage;
    private Predicate<ConcreteGame> executionCondition;
    private BehaviorAction behaviorAction;
    private BehaviorView view;
    private String failMessage;

    private Rule executionRule;
    private Rule alternativeExcecutionRule;
    private List<Action> actions = new ArrayList<>();
    private List<Action> alternativeActions = new ArrayList<>();
    private List<ParametizedAction> parametizedActions = new ArrayList<>();

    public static BehaviorBuilder builder() {
        return new BehaviorBuilder();
    }

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
        if (getExecutionRule().verify()) {
            for (Action action : actions) {
                action.doAction();
            }
            return this.resultMessage;
        }

        return this.performAlternativeActions();
    }

    public String executeWith(String parameter) {
        if (getExecutionRule().verifyWith(parameter)) {
            for (ParametizedAction action : parametizedActions) {
                action.doActionWith(parameter);
            }
            return this.resultMessage;
        }
        return this.getExecutionRule().getErrorMessage();
    }

    private String performAlternativeActions() {
        if (getAlternativeExecutionRule().verify()) {
            for (Action alternativeAction : alternativeActions) {
                alternativeAction.doAction();
            }
            return this.alternativeResultMessage;
        }
        return this.getExecutionRule().getErrorMessage();
    }

    private Rule getExecutionRule() {
        return this.executionRule != null ? this.executionRule : Rule.TRUE;
    }

    private Rule getAlternativeExecutionRule() {
        if (alternativeActions == null) {
            return Rule.FALSE;
        }
        return this.alternativeExcecutionRule != null ? this.alternativeExcecutionRule : Rule.TRUE;
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

    public void setAlternativeResultMessage(String alternativeResultMessage) {
        this.alternativeResultMessage = alternativeResultMessage;
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

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public void setAlternativeExcecutionRule(Rule alternativeExcecutionRule) {
        this.alternativeExcecutionRule = alternativeExcecutionRule;
    }

    public void setAlternativeActions(List<Action> alternativeActions) {
        this.alternativeActions = alternativeActions;
    }

    /*
     * Behavior builder class
     */
    public static class BehaviorBuilder {
        private String actionName;
        private String resultMessage;
        private String alternativeResultMessage;
        private String failMessage;
        private Rule executionRule;
        private Rule alternativeExecutionRule;
        private List<Action> actions;
        private List<Action> alternativeActions;

        private BehaviorBuilder() {
        }

        public BehaviorBuilder actionName(String actionName) {
            this.actionName = actionName;
            return this;
        }

        public BehaviorBuilder resultMessage(String resultMessage) {
            this.resultMessage = resultMessage;
            return this;
        }


        public BehaviorBuilder alternativeResultMessage(String alternativeResultMessage) {
            this.alternativeResultMessage = alternativeResultMessage;
            return this;
        }

        public BehaviorBuilder failMessage(String failMessage) {
            this.failMessage = failMessage;
            return this;
        }

        public BehaviorBuilder executionRule(Rule executionRule) {
            this.executionRule = executionRule;
            return this;
        }

        public BehaviorBuilder actions(Action... actions) {
            this.actions = Arrays.asList(actions);
            return this;
        }

        public BehaviorBuilder alternativeExecutionRule(Rule alternativeExecutionRule) {
            this.alternativeExecutionRule = alternativeExecutionRule;
            return this;
        }

        public BehaviorBuilder alternativeActions(Action... failActions) {
            this.alternativeActions = Arrays.asList(failActions);
            return this;
        }

        public Behavior build() {
            Behavior behavior = new Behavior();
            behavior.setActionName(this.actionName);
            behavior.setResultMessage(this.resultMessage);
            behavior.setAlternativeResultMessage(this.alternativeResultMessage);
            behavior.setFailMessage(this.failMessage);
            behavior.setExecutionRule(this.executionRule);
            behavior.setActions(this.actions);
            behavior.setAlternativeExcecutionRule(this.alternativeExecutionRule);
            behavior.setAlternativeActions(this.alternativeActions);
            return behavior;
        }
    }
}
package ar.fiuba.tdd.tp.games.behavior;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.exceptions.GameException;

import java.util.function.Predicate;

/**
 * Created by swandelow on 5/11/16.
 */
public class Behavior {

    private String actionName;
    private Predicate<ConcreteGame> executionCondition;
    private BehaviorAction behaviorAction;
    private BehaviorView view;

    public String execute(ConcreteGame game) {
        if (executionCondition.test(game)) {
            this.behaviorAction.execute(game);
            return this.view.print(game);
        }
        throw new GameException("Unsuccessful execution. Execution condition not met.");
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

    public void setExecutionCondition(Predicate<ConcreteGame> executionCondition) {
        this.executionCondition = executionCondition;
    }

    public void setBehaviorAction(BehaviorAction behaviorAction) {
        this.behaviorAction = behaviorAction;
    }
}
package ar.fiuba.tdd.tp.games.actions;

import java.util.List;

/**
 * Created by swandelow on 6/8/16.
 */
public class CombinedAction implements Action {

    private List<Action> actions;

    public CombinedAction(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void doAction() {
        for (Action action : this.actions) {
            action.doAction();
        }
    }
}

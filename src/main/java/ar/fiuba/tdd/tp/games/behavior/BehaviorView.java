package ar.fiuba.tdd.tp.games.behavior;

import ar.fiuba.tdd.tp.games.ConcreteGame;
/**
 * Created by Eze on 16/05/2016.
 */
public class BehaviorView {

    private BehaviorViewAction action;

    public void setAction(BehaviorViewAction action) {
        this.action = action;
    }

    public String print(ConcreteGame game) {
        return this.action.print(game);
    }

}

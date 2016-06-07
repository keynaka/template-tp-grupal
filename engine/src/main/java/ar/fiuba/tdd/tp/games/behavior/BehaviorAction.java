package ar.fiuba.tdd.tp.games.behavior;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by swandelow on 5/11/16.
 */
@FunctionalInterface
public interface BehaviorAction {

    void execute(ConcreteGame game);
}

package ar.fiuba.tdd.tp.games.behavior;
import ar.fiuba.tdd.tp.games.ConcreteGame;
/**
 * Created by Eze on 16/05/2016.
 */
@FunctionalInterface
public interface BehaviorViewAction {

    String print(ConcreteGame game);
}
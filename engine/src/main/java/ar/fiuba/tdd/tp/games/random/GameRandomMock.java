package ar.fiuba.tdd.tp.games.random;

import java.util.List;

/**
 * Created by sebass on 14/06/16.
 */
public class GameRandomMock<T> implements GameRandom<T> {

    private T objectToReturn;

    public GameRandomMock(T objectToReturn) {
        this.objectToReturn = objectToReturn;
    }

    @Override
    public T getRandomFrom(List<T> objects) {
        return this.objectToReturn;
    }
}

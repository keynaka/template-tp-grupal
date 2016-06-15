package ar.fiuba.tdd.tp.games.random;

import java.util.List;

/**
 * Created by sebass on 14/06/16.
 */
public interface GameRandom<T> {

    T getRandomFrom(List<T> objects);
}

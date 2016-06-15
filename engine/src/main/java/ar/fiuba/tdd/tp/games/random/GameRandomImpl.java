package ar.fiuba.tdd.tp.games.random;

import java.util.List;
import java.util.Random;

/**
 * Created by sebass on 14/06/16.
 */
public class GameRandomImpl<T> implements GameRandom<T> {

    @Override
    public T getRandomFrom(List<T> objects) {
        Random random = new Random();
        int randomInt = random.nextInt(objects.size());
        return objects.get(randomInt);
    }
}

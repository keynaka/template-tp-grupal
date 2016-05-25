package ar.fiuba.tdd.tp.engine.core;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Nico on 25/05/2016.
 */
public class Utils {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static <T> T getRandomFromIterator(Iterator<T> it, int amountOfElements) {
        int random = Utils.getRandomInt(0, amountOfElements);
        T element = null;
        int cont = 0;
        while (it.hasNext()) {
            element = it.next();
            if (cont == random) {
                return element;
            }
            cont++;
        }
        return element;
    }
}

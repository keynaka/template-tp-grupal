package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

/**
 * Created by Fede on 23/04/2016.
 */

public class Wolf implements Transportable {

    public boolean eats(Transportable transportable) {
        if (transportable instanceof Sheep) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return "Wolf";
    }
}

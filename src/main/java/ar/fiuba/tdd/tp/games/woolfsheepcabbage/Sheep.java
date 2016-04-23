package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

/**
 * Created by Fede on 23/04/2016.
 */
public class Sheep implements Transportable {

    public boolean eats(Transportable transportable) {
        if (transportable instanceof Cabbage) {
            return true;
        }
        return false;
    }

    public String getDescription (){
        return "Sheep";
    }
}

package ar.fiuba.tdd.tp.games.woolfsheepcabbage;

/**
 * Created by Fede on 23/04/2016.
 */
public class Cabbage implements Transportable {

    public boolean eats(Transportable transportable) {
        return false;
    }

    public String getDescription (){
        return "Cabbage";
    }
}
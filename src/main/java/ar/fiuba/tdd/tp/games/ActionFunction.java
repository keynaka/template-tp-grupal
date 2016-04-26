package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/23/16.
 */
@FunctionalInterface
public interface ActionFunction {

    String execute(String itemName, String... arguments);
}

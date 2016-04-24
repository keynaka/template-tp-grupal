package ar.fiuba.tdd.tp.games.traits;

/**
 * Created by swandelow on 4/24/16.
 */
@FunctionalInterface
public interface TraitMethod {

    String execute(String itemName, String... args);

}

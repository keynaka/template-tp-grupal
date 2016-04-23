package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/18/16.
 */
public interface Game {

    /**
     * Initializes all objects and sets the logic of the game.
     * @return A message saying Welcome to the game.
     */
    String start();

    String play(Command command);

    /**
     * Verifies if the conditions to end the game are met.
     * @return True if all conditions are met.
     */
    boolean isFinished();

    /**
     * @return A description of the game.
     */
    String getDescription();
}

package ar.fiuba.tdd.tp.driver;

/**
 * Created by Fede on 19/05/2016.
 */

public interface GameDriver {
    /*void initGame(String jarPath) throws GameLoadFailedException;
    String sendCommand(String cmd);
    GameState getCurrentState();*/

    void initGame(String gameName);

    String sendCommand(String cmd);

    GameState getCurrentState();

}

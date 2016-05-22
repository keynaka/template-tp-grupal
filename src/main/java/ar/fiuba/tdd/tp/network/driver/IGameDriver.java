package ar.fiuba.tdd.tp.network.driver;

public interface IGameDriver {
    void initGame(String jarPath) throws GameLoadFailedException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}

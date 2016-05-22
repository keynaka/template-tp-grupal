package ar.fiuba.tdd.tp.network.driver;

import ar.fiuba.tdd.tp.engine.core.Game;
import ar.fiuba.tdd.tp.engine.core.GameBuilder;
import ar.fiuba.tdd.tp.network.server.BuilderLoader;

/**
 * Created by Nico on 22/05/2016.
 */
public class GameDriver implements IGameDriver {
    private Game game;
    private GameState currentGameState;
    private GameBuilder gameBuilder;

    public void initGame(String jarPath) throws GameLoadFailedException {
        try {
            gameBuilder = BuilderLoader.load(jarPath);
            if (gameBuilder == null) {
                throw new GameLoadFailedException();
            }
            this.game = gameBuilder.build();
            this.currentGameState = GameState.Ready;
        } catch (Exception e) {
            // Possible exceptions:
            // ClassNotFoundException, IOException, IllegalAccessException, InstantiationException
            e.printStackTrace();
        }
    }

    public String sendCommand(String cmd) {
        // Executes a command and wait for feedback from the game
        String feedback = this.game.executeCommand(cmd);

        // Update the game status
        this.updateGameStatus();

        return feedback;
    }

    private void updateGameStatus() {
        this.currentGameState = GameState.InProgress;
        if (this.game.isWon()) {
            this.currentGameState = GameState.Won;
        } else if (this.game.isLost()) {
            this.currentGameState = GameState.Lost;
        }
    }

    public GameState getCurrentState() {
        return this.currentGameState;
    }
}

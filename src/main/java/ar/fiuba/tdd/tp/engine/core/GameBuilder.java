package ar.fiuba.tdd.tp.engine.core;

import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.IIdentificable;
import ar.fiuba.tdd.tp.engine.models.item.Player;

import java.util.ArrayList;

/**
 * Created by Nico on 20/05/2016.
 */
public abstract class GameBuilder implements IGameBuilder {
    protected Game game;
    protected ArrayList<Stage> stages;
    protected Player player;
    protected ArrayList<String> knownCommands;

    public Game build() {
        // Starts a new game
        game = new Game();

        // Sets a parser to the game
        // TODO
        Parser parser = new Parser();
        game.setParser(parser);

        // Sets specific environment of a game
        buildEnvironment();

        // Set the stages to the game
        game.setStages(stages);

        // Set the player to the game
        game.setPlayer(player);

        // Set the commands supported by the game
        setKnownCommands();

        return game;
    }

    // Every concrete game builder should build its own environment
    protected abstract void buildEnvironment();

    protected abstract void setKnownCommands();

    protected void createPlayer() {
        player = new Player();
        player.setCurrentStage(getStageById(Game.MAIN_ROOM));
    }

    protected Stage getStageById(String id) {
        for (IIdentificable<String> it : stages) {
            if (it.getId().equals(id)) {
                return (Stage)it;
            }
        }
        return null;
    }
}

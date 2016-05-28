package ar.fiuba.tdd.tp.games;

import java.util.ArrayList;

/**
 * Created by swandelow on 5/27/16.
 */
public abstract class AbstractGameBuilder implements GameBuilder {
    protected ConcreteGame game;
    protected ArrayList<Stage> stages;
    protected Player player;
    protected ArrayList<String> knownCommands;

    public Game build() {
        // Starts a new game
        game = new ConcreteGame();

        // Sets empty stages
        stages = new ArrayList<>();

        // Sets empty known commands
        knownCommands = new ArrayList<>();

        // Sets specific environment of a game
        buildEnvironment();

        // Set the stages to the game
        game.setStages(stages);

        // Set the player to the game
        game.setPlayer(player);

        // Set the commands supported by the game
        setKnownActions();

        return game;
    }

    // Every concrete game builder should build its own environment
    protected abstract void buildEnvironment();

    protected abstract void setKnownActions();

    protected abstract void createPlayer();
}

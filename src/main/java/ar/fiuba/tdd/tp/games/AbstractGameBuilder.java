package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.objects.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by swandelow on 5/27/16.
 */
public abstract class AbstractGameBuilder implements GameBuilder {
    protected ConcreteGame game;
    protected List<Stage> stages = new ArrayList<>();
    protected Player player;
    private Map<String, Item> items = new HashMap<>();

    public Game build() {
        // Starts a new game
        game = new ConcreteGame();

        // Create a player
        player = new Player();

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

    protected abstract void configurePlayer();

    protected void addItem(Item item) {
        String key = item.getName().toLowerCase();
        this.items.put(key, item);
    }

    protected Item getItem(String itemName) {
        return this.items.get(itemName.toLowerCase());
    }
}

package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.actions.Action;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;
import ar.fiuba.tdd.tp.games.objects.GameObject;
import ar.fiuba.tdd.tp.games.rules.Rule;

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
    protected Map<String, Item> items = new HashMap<>();
    protected Map<String, Rule> rules = new HashMap<>();
    private Map<String, Action> actions = new HashMap<>();

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

    protected void addStage(Stage stage) {
        this.stages.add(stage);
    }

    protected Stage getStage(String stageName) {
        String key = stageName.toLowerCase();
        return this.stages.stream()
                .filter(stage -> stage.getName().equalsIgnoreCase(key))
                .findFirst()
                .get();
    }

    protected void addAction(String key, Action action) {
        this.actions.put(key.toLowerCase(), action);
    }

    protected Action getAction(String key) {
        return this.actions.get(key.toLowerCase());
    }

    protected void addRule(String ruleName, Rule rule) {
        this.rules.put(ruleName.toLowerCase(), rule);
    }

    protected Rule getRule(String ruleName) {
        return this.rules.get(ruleName.toLowerCase());
    }

    protected ItemKeeper getItemKeeper(String itemName) {
        return (ItemKeeper) this.getItem(itemName);
    }
}

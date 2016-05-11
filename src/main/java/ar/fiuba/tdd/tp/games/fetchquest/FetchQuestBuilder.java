package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.function.Predicate;

/**
 * Created by sebass on 10/05/16.
 */
public class FetchQuestBuilder implements GameBuilder {

    private ConcreteGame fetchQuest;

    @Override
    public Game build() {
        fetchQuest = new ConcreteGame();

        fetchQuest.setName("Fetch Quest");
        fetchQuest.setEndGameMessage("You won the game!");
        fetchQuest.setGameDescription("This is Fetch Quest. Pick the stick to win the game.");
        fetchQuest.setPlayer(this.buildPlayer());
        fetchQuest.addStage(this.buildRoom1());
        fetchQuest.setWinningCondition(this.buildWinningCondition());
        registerKnownActions(fetchQuest);
        return fetchQuest;
    }

    private void registerKnownActions(ConcreteGame game) {
        game.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler(game));
        game.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(game, itemName));
    }

    private String lookAroundHandler(ConcreteGame game) {
        return game.getCurrentStage().lookAround();
    }

    private String pickHandler(ConcreteGame game, String itemName) {
        Stage currentStage = game.getCurrentStage();
        Item item = currentStage.pickItem(itemName);
        game.getPlayer().addToInventory(item);
        return "There you go.";
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            return concreteGame.getPlayer().hasItem("stick");
        };
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room");
        return player;
    }

    private Stage buildRoom1() {
        Stage stage = new Stage("room");
        stage.addItem(new Item("stick", "it's a stick."));
        return stage;
    }
}

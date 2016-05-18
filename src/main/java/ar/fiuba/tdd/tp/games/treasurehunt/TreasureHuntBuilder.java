package ar.fiuba.tdd.tp.games.treasurehunt;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.cursedobject.CursedDoor;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.function.Predicate;

/**
 * Created by keynaka on 16/05/16.
 */
@SuppressWarnings("CPD-START")

public class TreasureHuntBuilder implements GameBuilder{
    private ConcreteGame treasureHunt;

    @Override
    public Game build() {
        treasureHunt = new ConcreteGame();
        treasureHunt.setName("Treasure Hunt");
        treasureHunt.setEndGameMessage("You won the game!");
        treasureHunt.setGameDescription("This is Treasure Hunt. Try to get out of the last room to win the game.");
        treasureHunt.setPlayer(this.buildPlayer());
        treasureHunt.addStage(this.buildRoom1());
        treasureHunt.addStage(this.buildRoom2());
        treasureHunt.setWinningCondition(this.buildWinningCondition());
        registerKnownActions();
        return treasureHunt;
    }

    private void registerKnownActions() {
        treasureHunt.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        treasureHunt.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        treasureHunt.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
    }

    private String lookAroundHandler() {
        return treasureHunt.getCurrentStage().lookAround();
    }

    private String pickHandler(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(treasureHunt, Action.PICK);
    }

    private String openHandler(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        return treasureHunt.getPlayer().openFromStage(currentStage, itemName);
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            return concreteGame.getPlayer().hasItem("key2");
        };
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room1");
        return player;
    }

    //Seteo de items y piezas
    private Stage buildRoom1() {
        Item item = this.buildKey1();
        item.registerActionAndHelp(Action.PICK, "pick key1");
        Door door = this.buildDoor1();
        Stage room1 = buildStage("room1", door, item);
        return room1;
    }

    private Stage buildStage(String stageName, Item... itemsToAdd) {
        Stage stage = new Stage(stageName);
        stage.addItems(itemsToAdd);
        return stage;
    }

    private Item buildKey1() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key1"); });
        Item stick = new Item("key1", "it's a key1.");
        stick.addBehavior(behavior);
        return stick;
    }

    private void pickBehavior(String itemName) {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.pickItem(itemName);
        treasureHunt.getPlayer().addToInventory(item);
    }

    private Door buildDoor1() {
        Door door = this.buildDoor("door1", "room2");
        door.setOpeningCondition((player) -> player.hasItem("key1"));
        door.registerActionAndHelp(Action.OPEN, "open door1");
        return door;
    }

    private Item buildKey2() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> { this.pickBehavior("key2"); });
        Item stick = new Item("key2", "it's a key2.");
        stick.addBehavior(behavior);
        return stick;
    }

    private Stage buildRoom2() {
        Stage stage = new Stage("room2");
        stage.addItem(this.buildKey2());
        return stage;
    }

    @SuppressWarnings("CPD-END")

    private Door buildDoor(String name, String nextStageName) {
        Door door = new Door(name, State.CLOSED);
        door.setNextStageName(nextStageName);
        return door;
    }

    /*
    private Item buildKey2() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> {
            this.pickBehavior();
        });
        Item stick = new Item("key2", "it's a key.");
        stick.addBehavior(behavior);
        return stick;
    }*/
}


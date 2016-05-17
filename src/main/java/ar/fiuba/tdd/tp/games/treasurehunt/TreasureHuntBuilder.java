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
/*
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
        treasureHunt.setWinningCondition(this.buildWinningCondition());
        registerKnownActions();
        return treasureHunt;
    }
    private void registerKnownActions() { //TODO: los actions deberia reemplazarse por strings
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
            return concreteGame.getPlayer().hasItem("stick");
        };
    }
    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room1");
        return player;
    }
    //Seteo de items y piezas
    private Stage buildRoom1() {
        Stage stage = new Stage("room1");
        stage.addItem(this.buildKey1());
        return stage;
    }
    private Item buildKey1() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        behavior.setResultMessage("There you go.");
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> {
            this.pickBehavior();
        });
        Item stick = new Item("key1", "it's a key.");
        stick.addBehavior(behavior);
        return stick;
    }
    private Door buildDoor1() {
        Door door1 = this.buildDoor1("door1", "room2");
        door1.setOpenCondition((someCharacter) -> someCharacter.hasItem("key1"));
        door1.registerActionAndHelp(Action.OPEN, "open door1");
        return door1;
    }
    private Door buildDoor1(String name, String nextStageName) {
        Door door1 = new CursedDoor(name);
        door1.setNextStageName(nextStageName);
        return door1;
    }
    private Stage buildRoom2() {
        Stage stage = new Stage("room2");
        stage.addItem(this.buildKey2());
        return stage;
    }
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
    }
    private void pickBehavior() {
        Stage currentStage = treasureHunt.getCurrentStage();
        Item item = currentStage.pickItem("key");
        treasureHunt.getPlayer().addToInventory(item);
    }
}
*/

package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by sebass on 10/05/16.
 */
public class CursedObjectGameBuilder implements GameBuilder {


    @Override
    public Game build() {
        ConcreteGame game = new ConcreteGame("Cursed Object", "You won the game!");

        game.setGameDescription("This is Cursed Object. Get the room 3 to win the game.");
        game.setPlayer(this.buildPlayer());
        buildStages(game);
        registerKnownActions(game);
        game.setWinningCondition(this.buildWinningCondition());
        return game;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            return concreteGame.getPlayer().getCurrentStage().equalsIgnoreCase("room3");
        };
    }

    private void registerKnownActions(ConcreteGame game) {
        this.registerLookAround(game);
        game.registerKnownAction(Action.EXAMINE, (itemName, args) -> this.examineHandler(game, itemName));
        game.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(game, itemName));
        game.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(game, itemName));
        game.registerKnownAction(Action.TALK, (itemName, args) -> this.talkHandler(game, itemName, args[0]));
    }

    private void registerLookAround(ConcreteGame game) {
        //game.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler(game));
    }

    private Iterator lookAroundHandler(ConcreteGame game) {
        return game.getCurrentStage().lookAround();
    }

    private String pickHandler(ConcreteGame game, String itemName) {
        Stage currentStage = game.getCurrentStage();
        return game.getPlayer().pickFromStage(currentStage, itemName);
    }

    private String openHandler(ConcreteGame game, String itemName) {
        Stage currentStage = game.getCurrentStage();
        return game.getPlayer().openFromStage(currentStage, itemName);
    }

    private String talkHandler(ConcreteGame game, String itemName, String message) {
        Stage currentStage = game.getCurrentStage();
        return game.getPlayer().talkTo(currentStage, itemName, message);
    }

    private String examineHandler(ConcreteGame game, String itemName) {
        Stage currentStage = game.getCurrentStage();
        return currentStage.getItem(itemName).examine();
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room1");
        return player;
    }

    private void buildStages(ConcreteGame game) {
        game.addStage(this.buildRoom1());
        game.addStage(this.buildRoom2());
    }

    private Stage buildRoom1() {
        Item cursedObject = new CursedObject();
        cursedObject.registerActionAndHelp(Action.PICK, "pick CursedObject");
        Door cursedDoor = this.buildCursedDoor1();
        Stage room1 = buildStage("room1", cursedObject, cursedDoor);
        return room1;
    }

    private Door buildCursedDoor1() {
        CursedDoor cursedDoor = this.buildCursedDoor("door1", "room2");
        cursedDoor.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        cursedDoor.registerActionAndHelp(Action.OPEN, "open door1");
        return cursedDoor;
    }

    private Door buildCursedDoor2() {
        CursedDoor cursedDoor = this.buildCursedDoor("door2", "room3");
        cursedDoor.setOpenCondition((someCharacter) -> !someCharacter.hasItem("CursedObject"));
        cursedDoor.registerActionAndHelp(Action.OPEN, "open door2");
        return cursedDoor;
    }

    private Stage buildRoom2() {
        Thief thief = new Thief();
        thief.registerActionAndHelp(Action.TALK, "\"Hello\", \"Bye\"");
        Stage room2 = buildStage("room2", thief, this.buildCursedDoor2());
        return room2;
    }

    private Stage buildStage(String stageName, Item... itemsToAdd) {
        Stage stage = new Stage(stageName);
        stage.addItems(itemsToAdd);
        return stage;
    }

    private CursedDoor buildCursedDoor(String name, String nextStageName) {
        CursedDoor cursedDoor = new CursedDoor(name);
        cursedDoor.setNextStageName(nextStageName);
        return cursedDoor;
    }
}

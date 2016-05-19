package ar.fiuba.tdd.tp.games.hanoitowers;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

/**
 * Created by Patri on 15/05/2016.
 */
public class HanoiTowersBuilder implements GameBuilder {
    @SuppressWarnings("CPD-START")
    private ConcreteGame hanoiTowers;
    private int numberOfDisks;

    @Override
    public Game build() {

        hanoiTowers = new ConcreteGame();
        hanoiTowers.setName("Hanoi Towers");
        hanoiTowers.setEndGameMessage("You won the game!");
        hanoiTowers.setGameDescription("This is Hanoi Towers. Move all the disks from tower1 to tower3 to win!");
        hanoiTowers.setWinningCondition(this.buildWinningCondition());
        hanoiTowers.setPlayer(this.buildPlayer());
        hanoiTowers.addStage(this.buildStage());
        registerKnownActions(hanoiTowers);
        numberOfDisks = 0;
        return hanoiTowers;

    }

    private void registerKnownActions(ConcreteGame game) {
        game.registerKnownAction(Action.SET_DISKS, (number, args) -> this.disksSetterHandler(game, number));
        game.registerKnownAction(Action.TOP_SIZE, (towerId, args) -> this.checkTopHandler(game, towerId));
        game.registerKnownAction(Action.MOVE_TOP, (itemName, args) -> this.moveTopHandler(game, itemName, args));
        game.registerKnownAction(Action.EXAMINE, (itemName, arg) -> this.examineHandler(game, itemName));
    }

    private String moveTopHandler(ConcreteGame game, String itemName, String[] args) {

        Stage stage = game.getCurrentStage();

        if (stage.getItemContainer().contains("originTowerId")) {
            stage.getItemContainer().extract("originTowerId"); // I'll replace the old origin tower with the new one
        }

        if (stage.getItemContainer().contains(args[0]) && stage.getItemContainer().contains(itemName)) {

            Item originTowerId = new Item("originTowerId", itemName);

            stage.addItem(originTowerId);

            Item destinyTower = stage.getItem(args[0]);

            return destinyTower.execute(game, Action.MOVE_TOP);

        }

        return "ERROR! Invalid command.";

    }

    private String examineHandler(ConcreteGame game, String itemName) {

        if (!diskAreSetted()) {
            return "ERROR! Game is not initialized!";
        }

        try {
            Item item = game.getCurrentStage().getItem(itemName);
            return item.examine();

        } catch (GameException gameException) {
            return "ERROR! Invalid command.";
        }

    }

    private String checkTopHandler(ConcreteGame game, String itemName) {
        Stage currentStage = game.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(game, Action.TOP_SIZE);
    }

    private String disksSetterHandler(ConcreteGame game, String number) {
        try {

            this.numberOfDisks = Integer.parseInt(number);
            Stage stage = game.getCurrentStage();
            stage.addItem(this.buildTower1());
            return "You can start playing!";

        } catch (Exception exception) {
            return "ERROR! Invalid command.";
        }
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("hanoi towers");
        return player;
    }

    private Stage buildStage() {
        Stage stage = new Stage("hanoi towers");
        stage.addItem(this.buildTower2());
        stage.addItem(this.buildTower3());
        return stage;
    }

    private Collection<Item> getTower1InitialContent() {

        Collection<Item> items = new ArrayList<Item>();

        for (int i = numberOfDisks; i >= 1; i--) {
            Item item = new Item("disk", Integer.toString(i));
            items.add(item);
        }

        return items;
    }

    private Item buildTower1() {

        Behavior checkTopBehavior = buildCheckTopBehavior("tower1");
        Behavior moveTopBehavior = buildMoveTopBehavior("tower1");

        Item tower1 = new Stacker("tower1", this.getTower1InitialContent());

        tower1.addBehavior(checkTopBehavior);
        tower1.addBehavior(moveTopBehavior);

        tower1.registerActionAndHelp(Action.EXAMINE, "You can check top/move top");

        return tower1;
    }

    private Item buildTower2() {

        Behavior checkTopBehavior = buildCheckTopBehavior("tower2");
        Behavior moveTopBehavior = buildMoveTopBehavior("tower2");

        Item tower2 = new Stacker("tower2", new ArrayList<>());

        tower2.addBehavior(checkTopBehavior);
        tower2.addBehavior(moveTopBehavior);
        tower2.registerActionAndHelp(Action.EXAMINE, "You can check top/move top");

        return tower2;
    }

    private Item buildTower3() {

        Behavior checkTopBehavior = buildCheckTopBehavior("tower3");
        Behavior moveTopBehavior = buildMoveTopBehavior("tower3");

        Item tower3 = new Stacker("tower3", new ArrayList<>());

        tower3.addBehavior(checkTopBehavior);
        tower3.addBehavior(moveTopBehavior);
        tower3.registerActionAndHelp(Action.EXAMINE, "You can check top/move top");

        return tower3;
    }

    private Behavior buildMoveTopBehavior(String towerId) {
        Behavior behavior = new Behavior();
        behavior.setActionName("move top");
        BehaviorView view = new BehaviorView();
        view.setAction((game) -> "Moved!");
        behavior.setView(view);
        behavior.setExecutionCondition((game) -> this.buildMoveTopCondition(towerId));
        behavior.setBehaviorAction((game) -> {
                this.moveTopBehavior(game, towerId);
            });
        return behavior;
    }

    private boolean towersExists(Stage currentStage, String destinyTowerId) {
        if (!currentStage.getItemContainer().contains(destinyTowerId) || !currentStage.getItemContainer().contains("originTowerId")) {
            return false;
        }

        String originTowerId = currentStage.getItem("originTowerId").getDescription();

        if (!currentStage.getItemContainer().contains(originTowerId)) {
            return false;
        }

        return true;
    }

    private boolean buildMoveTopCondition(String destinyTowerId) {

        Stage currentStage = hanoiTowers.getCurrentStage();

        if (!towersExists(currentStage, destinyTowerId)) {
            return false;
        } else {

            String originTowerId = currentStage.getItem("originTowerId").getDescription();

            Stacker originTower = (Stacker) currentStage.getItem(originTowerId);
            Stacker destinyTower = (Stacker) currentStage.getItem(destinyTowerId);

            if (originTower.isEmpty()) {

                return false;

            } else if (!destinyTower.isEmpty()) {

                int originTopSize = Integer.parseInt(originTower.checkTop().getDescription());
                int destinyTopSize = Integer.parseInt(destinyTower.checkTop().getDescription());
                if (originTopSize > destinyTopSize) {
                    return false;
                }

            }

            return true;
        }

    }

    private void moveTopBehavior(ConcreteGame game, String towerId) {

        Stage currentStage = game.getCurrentStage();
        String originTowerId = currentStage.pickItem("originTowerId").getDescription();

        Stacker originTower = (Stacker) currentStage.getItem(originTowerId);
        Stacker destinyTower = (Stacker) currentStage.getItem(towerId);

        destinyTower.stack(originTower.getTop());

    }

    private Behavior buildCheckTopBehavior(String towerId) {
        Behavior behavior = new Behavior();
        behavior.setActionName("check top");
        BehaviorView view = new BehaviorView();
        view.setAction((game) -> this.getTopSize(game, towerId));
        behavior.setView(view);
        behavior.setExecutionCondition((game) -> this.buildCheckTopCondition(towerId));
        behavior.setBehaviorAction((game) -> {
                this.checkTopBehavior();
            });
        return behavior;
    }

    private String getTopSize(ConcreteGame game, String towerId) {
        Stage currentStage = game.getCurrentStage();
        Stacker stacker = (Stacker) currentStage.getItem(towerId);
        String message = "Top size is %s.";
        return String.format(message,stacker.checkTop().getDescription());
    }

    private boolean buildCheckTopCondition(String towerId) {
        Stacker tower = (Stacker) hanoiTowers.getCurrentStage().getItem(towerId);
        return diskAreSetted() && !tower.isEmpty();
    }

    private void checkTopBehavior() {
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return concreteGame -> this.tower3IsFull();
    }


    private boolean diskAreSetted() {
        if (numberOfDisks == 0) {
            return false;
        }
        return true;
    }

    @SuppressWarnings("CPD-END")

    private boolean tower3IsFull() {
        if (!diskAreSetted()) {
            return false;
        }
        Stacker tower3 = (Stacker) hanoiTowers.getCurrentStage().getItem("tower3");
        return tower3.getSize() == this.numberOfDisks;
    }
}
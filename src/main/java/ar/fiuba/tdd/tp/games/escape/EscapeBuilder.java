package ar.fiuba.tdd.tp.games.escape;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.items.Item;
import ar.fiuba.tdd.tp.games.items.containers.ItemContainer;

import java.util.function.Predicate;

/**
 * Created by swandelow on 5/19/16.
 */

public class EscapeBuilder implements GameBuilder {

    ConcreteGame escape;

    @SuppressWarnings("CPD-START")
    @Override
    public Game build() {
        escape = new ConcreteGame();
        escape.setName("Escape");
        escape.setWinningCondition(this.buildWinningCondition());
        escape.setPlayer(this.buildPlayer());
        escape.addStage(this.buildHall());
        escape.addStage(this.buildRoom1());
        escape.addStage(this.buildRoom3());
        registerKnownActions();
        return escape;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (game) -> game.getPlayer().getCurrentStage().equalsIgnoreCase("FinalRoom");
    }

    private void registerKnownActions() {
        escape.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        escape.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        escape.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
        escape.registerKnownAction(Action.MOVE, (itemName, args) -> this.moveHandler(itemName));
    }

    private String pickHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.PICK);
    }

    private String moveHandler(String itemName) {
        return escape.getCurrentStage().getItem(itemName).execute(escape, Action.MOVE);
    }

    private String lookAroundHandler() {
        return escape.getCurrentStage().lookAround();
    }

    private String openHandler(String itemName) {
        Stage currentStage = escape.getCurrentStage();
        return currentStage.getItem(itemName).execute(escape, Action.OPEN);
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("hall");
        player.addToInventory(new Item("picture", "picture"));
        player.addToInventory(new Item("pen", "pen"));
        return player;
    }

    private Item buildKey1() {
        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go.");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> {
            this.pickBehavior("key1");
        });
        Item key = new Item("key1", "it's a key1.");
        key.addBehavior(behavior);
        key.addBehavior(this.buildDropKeyBehavior());
        return key;
    }


    private void pickBehavior(String itemName) {
        Stage currentStage = escape.getCurrentStage();
        Item item = currentStage.pickItem(itemName);
        escape.getPlayer().addToInventory(item);
    }

    private Behavior buildDropKeyBehavior() {
        Behavior behavior = new Behavior();
        behavior.setActionName("drop");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "key dropped");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((treasureHunt) -> {
        });
        return behavior;
    }

    private Item buildDoor1() {
        Item door = new Item("door1", "it's a door1.");
        door.addState("stage1", "hall");
        door.addState("stage2", "room1");

        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "Door1 opened.");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            this.behaviorDoor(door);
        });
        door.addBehavior(behavior);
        return door;
    }

    private void behaviorDoor(Item door) {
        String stage1 = door.getState("stage1");
        String stage2 = door.getState("stage2");
        Player player = escape.getPlayer();
        if (player.getCurrentStage().equalsIgnoreCase(stage1)) {
            player.setCurrentStage(stage2);
        } else if (player.getCurrentStage().equalsIgnoreCase(stage2)) {
            player.setCurrentStage(stage1);
        }
    }

    private Stage buildRoom1() {
        Stage room1 = new Stage("room1");
        room1.addItem(this.buildDoor1());
        room1.addItem(new Item("table", "it's a table."));
        room1.addItem(new Item("chair1", "it's a chair."));
        room1.addItem(new Item("chair2", "it's a chair."));
        room1.addItem(new Item("liquor", "it's a liquor."));
        room1.addItem(new Item("trainPicture", "it's a picture of a train."));
        room1.addItem(this.buildBoatPicture());
        return room1;
    }

    private Item buildBoatPicture() {
        Item boatPicture = new Item("boatPicture", "it's a picture of a boat.");
        Behavior behavior = new Behavior();
        behavior.setActionName("move");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            this.movePicture();
        });
        boatPicture.addBehavior(behavior);
        return boatPicture;
    }

    private void movePicture() {
        Stage currentStage = escape.getCurrentStage();
        currentStage.addItem(this.buildSecurityBox());
    }

    private Item buildSecurityBox() {
        ItemContainer securityBox = new ItemContainer("securityBox", "'it's a security box.", 1);
        Item idCard = this.buildIdCard();
        securityBox.addItem(idCard);

        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        BehaviorView behaviorView = new BehaviorView();
        behaviorView.setAction((game) -> "Security box opened.");
        behavior.setView(behaviorView);
        behavior.setFailMessage("you can't open the box without key.");
        behavior.setExecutionCondition((game) -> game.getPlayer().hasItem("key"));
        behavior.setBehaviorAction((game) -> {
            Item extractedItem = securityBox.extract(idCard.getName());
            game.getCurrentStage().addItem(extractedItem);
        });
        securityBox.addBehavior(behavior);
        return securityBox;
    }

    private Item buildIdCard() {
        Item idCard = new Item("idCard", "it's an id card.");
        idCard.addState("picture", "stranger");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView idCardView = new BehaviorView();
        idCardView.setAction((game) -> "There you go!");
        behavior.setView(idCardView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(idCard.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        idCard.addBehavior(behavior);
        return idCard;
    }

    private Stage buildRoom3() {
        Stage room3 = new Stage("room3");
        room3.addItem(this.buildDoor3());
        room3.addItem(this.buildKey());
        return room3;
    }

    private Item buildKey() {
        Item key = new Item("key", "it's a key.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(key.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        key.addBehavior(behavior);
        return key;
    }

    private Item buildDoor3() {
        Item door = new Item("door3", "it's a door3.");
        door.addState("stage1", "hall");
        door.addState("stage2", "room3");

        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "Door3 opened.");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            this.behaviorDoor(door);
        });
        door.addBehavior(behavior);
        return door;
    }

    @SuppressWarnings("CPD-END")
    private Stage buildHall() {
        Stage hall = new Stage("hall");
        hall.addItem(this.buildDoor1());
        hall.addItem(this.buildDoor3());
        return hall;
    }
}

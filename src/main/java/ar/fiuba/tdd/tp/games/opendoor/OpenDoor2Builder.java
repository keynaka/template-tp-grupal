package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by Eze on 19/05/2016.
 */
public class OpenDoor2Builder implements GameBuilder {
    private ConcreteGame openDoor2;

    @SuppressWarnings("CPD-START")
    @Override
    public Game build() {

        openDoor2 = new ConcreteGame();
        openDoor2.setPlayer(this.buildPlayer());
        openDoor2.addStage(this.buildRoom());
        openDoor2.setWinningCondition(this.buildWinningCondition());
        openDoor2.setName("Open Door 2");
        openDoor2.setEndGameMessage("You enter room 2. You won the game!");
        openDoor2.setGameDescription("Welcome to Open Door 2. Open the door to win the game!");

        registerKnownActions();

        return openDoor2;
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.setCurrentStage("room");
        return player;
    }

    private Stage buildRoom() {
        Stage stage = new Stage("room");
        stage.addItem(this.buildKey());
        stage.addItem(this.buildDoor());
        Behavior roomB = new Behavior();
        roomB.setActionName("look around");
        roomB.setExecutionCondition((game) -> true);
        roomB.setBehaviorAction((game) -> {
            this.lookAroundAction();
        });
        BehaviorView stageView = new BehaviorView();
        stageView.setAction((game) -> {
            return this.stageViewBehavior();
        });
        roomB.setView(stageView);
        stage.addBehavior(roomB);
        return stage;
    }

    private Item buildDoor() {
        Behavior open = new Behavior();
        open.setActionName("open");
        open.setExecutionCondition((game) -> this.testOpenDoor());
        open.setBehaviorAction((game) -> {
            this.openDoorBehavior();
        });
        Item door = new Item("door", "It's a door.");
        door.addBehavior(open);
        door.addState("door", "closed");
        BehaviorView doorView = new BehaviorView();
        doorView.setAction((game) -> {
            return this.openDoorBehaviorView();
        });
        open.setView(doorView);
        return door;
    }

    private Item buildKey() {
        Behavior pick = new Behavior();
        pick.setActionName("pick");
        pick.setExecutionCondition((game) -> true);
        pick.setBehaviorAction((game) -> {
            this.pickBehavior();
        });

        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        pick.setView(keyView);

        Item key = new Item("key", "pick key.");
        key.addBehavior(pick);
        return key;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            try {
                return openDoor2.getCurrentStage().getItemContainer().getItem("door").getState("door").equals("opened");
            } catch (NullPointerException e) {
                return false;
            }
        };
    }

    private void registerKnownActions() {
        openDoor2.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        openDoor2.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        openDoor2.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
    }

    private String lookAroundHandler() {
        Stage gameCurrentStage = openDoor2.getCurrentStage();
        return gameCurrentStage.execute(openDoor2, Action.LOOK_AROUND);
    }

    private String pickHandler(String itemName) {
        Stage currentStage = openDoor2.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(openDoor2, Action.PICK);
    }

    private String openDoorBehaviorView() {
        Stage stage = openDoor2.getCurrentStage();
        Item currentDoor = stage.getItem("door");
        if (currentDoor.getState("door").equals("opened")){
            return "You enter room 2. You won the game!";
        }
        return "Ey! Where do you go?! Room 2 is locked.";
    }

    private void lookAroundAction() {
        openDoor2.getCurrentStage().lookAround();
    }

    private void pickBehavior() {
        Stage currentStage = openDoor2.getCurrentStage();
        Item item = currentStage.pickItem("key");
        openDoor2.getPlayer().addToInventory(item);
    }

    private boolean testOpenDoor() {
        return openDoor2.getPlayer().hasItem("key");
    }

    private String stageViewBehavior() {
        return "Items in the room: " + this.roomObjects();
    }

    private String roomObjects() {
        Iterator<Item> it = openDoor2.getCurrentStage().lookAround();
        StringBuilder builder = new StringBuilder();
        while (it.hasNext()) {
            builder.append(it.next().getName());
            if (it.hasNext()) {
                builder.append(", ");
            } else {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    private void openDoorBehavior() {
        Stage currentStage = openDoor2.getCurrentStage();
        Item door = currentStage.getItem("door");
        door.addState("door", "opened");
    }

    @SuppressWarnings("CPD-END")

    private String openHandler(String itemName){
        Item item = openDoor2.getCurrentStage().getItem(itemName);
        return item.toString();
    }
}

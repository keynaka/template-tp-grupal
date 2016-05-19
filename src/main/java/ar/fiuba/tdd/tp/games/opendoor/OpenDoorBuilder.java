package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.behavior.Behavior;
import ar.fiuba.tdd.tp.games.behavior.BehaviorView;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by gonzalo on 17/05/2016.
 */
public class OpenDoorBuilder implements GameBuilder {

    private ConcreteGame openDoor;

    @SuppressWarnings("CPD-START")
    @Override
    public Game build() {
        openDoor = new ConcreteGame();
        openDoor.setName("Open Door");
        openDoor.setEndGameMessage("You enter room 2. You won the game!");
        openDoor.setGameDescription("Welcome to Open Door. Open the door to win the game!");
        openDoor.setPlayer(this.buildPlayer());
        openDoor.addStage(this.buildRoom());
        openDoor.setWinningCondition(this.buildWinningCondition());
        registerKnownActions();

        return openDoor;
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

    @SuppressWarnings("CPD-END")

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

    private Item buildDoor() {
        Behavior open = new Behavior();
        open.setActionName("open");
        open.setExecutionCondition((game) -> this.testOpenDoor());
        open.setBehaviorAction((game) -> {
                this.openBehavior();
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

    private void openBehavior() {
        Stage currentStage = openDoor.getCurrentStage();
        Item door = currentStage.getItem("door");
        door.addState("door", "opened");
    }

    private void pickBehavior() {
        Stage currentStage = openDoor.getCurrentStage();
        Item item = currentStage.pickItem("key");
        openDoor.getPlayer().addToInventory(item);
    }

    private boolean testOpenDoor() {
        return openDoor.getPlayer().hasItem("key");
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (concreteGame) -> {
            try {
                return openDoor.getCurrentStage().getItemContainer().getItem("door").getState("door").equals("opened");
            } catch (NullPointerException e) {
                return false;
            }
        };
    }


    private void registerKnownActions() {
        openDoor.registerKnownAction(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        openDoor.registerKnownAction(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
        openDoor.registerKnownAction(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
    }

    private String lookAroundHandler() {
        Stage gameCurrentStage = openDoor.getCurrentStage();
        return gameCurrentStage.execute(openDoor, Action.LOOK_AROUND);
    }

    private String pickHandler(String itemName) {
        Stage currentStage = openDoor.getCurrentStage();
        Item item = currentStage.getItem(itemName);
        return item.execute(openDoor, Action.PICK);
    }

    private String openHandler(String itemName) {
        Stage currentStage = openDoor.getCurrentStage();
        Item item = currentStage.getItem(itemName);
/*        String resultMessage;
        try {
            item.execute(openDoor, Action.OPEN);
            resultMessage = item.getBehavior("open").getResultMessage();
        } catch (GameException e) {
            resultMessage = "Ey! Where do you go?! Room 2 is locked.";
        }*/
        return item.execute(openDoor, Action.OPEN);
    }

    private String openDoorBehaviorView() {
        Stage stage = openDoor.getCurrentStage();
        Item currentDoor = stage.getItem("door");
        if (currentDoor.getState("door").equals("opened")){
            return "You enter room 2. You won the game!";
        }
        return "Ey! Where do you go?! Room 2 is locked.";
    }

    private void lookAroundAction() {
        openDoor.getCurrentStage().lookAround();
    }

    @SuppressWarnings("CPD-START")
    private String roomObjects() {
        Iterator<Item> it = openDoor.getCurrentStage().lookAround();
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
    @SuppressWarnings("CPD-END")

    private String stageViewBehavior() {
        return "Items in the room: " + this.roomObjects();
    }


}

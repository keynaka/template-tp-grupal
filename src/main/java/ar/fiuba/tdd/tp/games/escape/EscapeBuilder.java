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
        escape.setLoosingCondition(this.buildLoosingCondition());
        escape.setPlayer(this.buildPlayer());
        escape.addStage(this.buildHall());
        escape.addStage(this.buildRoom1());
        escape.addStage(this.buildRoom2());
        escape.addStage(this.buildRoom3());
        escape.addStage(this.buildLibraryHall());
        registerKnownActions();
        return escape;
    }

    private Player buildPlayer() {
        Player player = new Player();
        player.addState("lifeStatus", "alive");
        player.setCurrentStage("hall");
        player.addToInventory(new Item("picture", "picture"));
        player.addToInventory(new Item("pen", "pen"));
        return player;
    }

    private Predicate<ConcreteGame> buildWinningCondition() {
        return (game) -> game.getPlayer().getCurrentStage().equalsIgnoreCase("FinalRoom");
    }

    private Predicate<ConcreteGame> buildLoosingCondition() {
        return (game) -> game.getPlayer().getState("lifeStatus").equalsIgnoreCase("dead");
    }

    //----------------------------Handlers && Behaviors----------------------------------//
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

    //----------------------------Fin Handlers && Behaviors----------------------------------//

    //----------------------------Room1----------------------------------//
    private Stage buildRoom1() {
        Stage room1 = new Stage("room1");
        room1.addItem(this.buildDoor1());
        room1.addItem(new Item("table", "it's a table."));
        room1.addItem(new Item("cup", "it's a cup2."));
        room1.addItem(new Item("cup2", "it's a cup2."));
        room1.addItem(new Item("chair1", "it's a chair."));
        room1.addItem(new Item("chair2", "it's a chair."));
        room1.addItem(this.buildLiquor());
        room1.addItem(this.buildTrainPicture());
        room1.addItem(this.buildBoatPicture());
        return room1;
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

    private Item buildLiquor() {
        Item liquor = new Item("liquor", "it's a liquor.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(liquor.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        liquor.addBehavior(behavior);
        return liquor;
    }

    private Item buildTrainPicture() {
        Item trainPicture = new Item("trainPicture", "it's a picture of a train.");
        Behavior behavior = new Behavior();
        behavior.setActionName("move");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
        });
        trainPicture.addBehavior(behavior);
        return trainPicture;
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
    //----------------------------FinRoom1----------------------------------//

    //----------------------------Room2----------------------------------//
    private Stage buildRoom2() {
        Stage room2 = new Stage("room2");
        room2.addItem(this.buildDoor2());
        room2.addItem(this.buildHammer());
        room2.addItem(this.buildScrewdriver());
        return room2;
    }

    private Item buildDoor2() {
        Item door = new Item("door2", "it's a door2.");
        door.addState("stage1", "hall");
        door.addState("stage2", "room2");

        Behavior behavior = new Behavior();
        behavior.setActionName("open");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "Door2 opened.");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            this.behaviorDoor(door);
        });
        door.addBehavior(behavior);
        return door;
    }

    private Item buildHammer() {
        Item hammer = new Item("hammer", "it's a hammer.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(hammer.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        hammer.addBehavior(behavior);
        return hammer;
    }

    private Item buildScrewdriver() {
        Item screwdriver = new Item("screwdriver", "it's a screwdriver.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(screwdriver.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        screwdriver.addBehavior(behavior);
        return screwdriver;
    }
    //----------------------------FinRoom2----------------------------------//

    //----------------------------Room3----------------------------------//
    private Stage buildRoom3() {
        Stage room3 = new Stage("room3");
        room3.addItem(this.buildDoor3());
        room3.addItem(this.buildKey());
        return room3;
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
    //----------------------------FinRoom3----------------------------------//

    //----------------------------Library----------------------------------//
    private Stage buildLibraryHall() {
        Stage libraryHall = new Stage("libraryHall");
        libraryHall.addItem(this.buildLibrarian());
        return libraryHall;
    }

    private Item buildLibrarian() {
        Item librarian = new Item("librarian", "bla");
        return librarian;
    }

    //TODO: para que despues hagan addItem(this.buildBook("book1")), etc.
    private Item buildBook(String aBook) {
        Item book = new Item(aBook, "it's a " + aBook + ".");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            Item pickedItem = game.getCurrentStage().pickItem(book.getName());
            game.getPlayer().addToInventory(pickedItem);
        });
        book.addBehavior(behavior);
        return book;
    }

    //TODO: para el oldBook que tiene un behavior diferente al de los demas libros
    private Item buildOldBook() {
        Item book = new Item("oldBook", "it's an old book.");

        Behavior behavior = new Behavior();
        behavior.setActionName("pick");
        BehaviorView keyView = new BehaviorView();
        keyView.setAction((game) -> "There you go!");
        behavior.setView(keyView);
        behavior.setExecutionCondition((game) -> true);
        behavior.setBehaviorAction((game) -> {
            //TODO: Habria que hacer que se abra el camino al sotano
        });
        book.addBehavior(behavior);
        return book;
    }

    //----------------------------FinLibrary----------------------------------//

    @SuppressWarnings("CPD-END")
    private Stage buildHall() {
        Stage hall = new Stage("hall");
        hall.addItem(this.buildDoor1());
        hall.addItem(this.buildDoor2());
        hall.addItem(this.buildDoor3());
        return hall;
    }
}

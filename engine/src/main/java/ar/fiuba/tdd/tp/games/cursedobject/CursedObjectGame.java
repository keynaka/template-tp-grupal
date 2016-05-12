package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGame extends AbstractGame {

    private Player player;
    private Map<String, Stage> rooms;

    public CursedObjectGame() {
        super("Cursed Object", "You won the game!");
        this.rooms = new HashMap<>();
    }

    @Override
    protected void doStart() {
        this.buildCharacter();
        this.buildRoom1();
        this.buildRoom2();
    }

    @Override
    protected void registerKnownActions() {
        this.registerPickHandler();
        this.knownActions.put(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
        this.knownActions.put(Action.TALK, (itemName, args) -> this.talkHandler(itemName, args[0]));
        this.knownActions.put(Action.LOOK_AROUND, (itemName, args) -> this.lookAroundHandler());
        this.knownActions.put(Action.EXAMINE, (itemName, args) -> this.examineHandler(itemName));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return this.player.getCurrentStage().equalsIgnoreCase("room3");
    }

    private String pickHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.player.pickFromStage(currentRoom, itemName);
    }

    private String openHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.player.openFromStage(currentRoom, itemName);
    }

    private String talkHandler(String itemName, String message) {
        Stage currentRoom = this.getCurrentRoom();
        return this.player.talkTo(currentRoom, itemName, message);
    }

    private String lookAroundHandler() {
        Stage currentRoom = this.getCurrentRoom();
        return currentRoom.lookAround();
    }

    private String examineHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return currentRoom.getItem(itemName).examine();
    }

    private Stage getCurrentRoom() {
        String currentRoomName = this.player.getCurrentStage();
        return this.rooms.get(currentRoomName);
    }

    private void registerPickHandler() {
        this.knownActions.put(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
    }

    private void buildCharacter() {
        this.player = new Player();
        this.player.setCurrentStage("room1");
    }

    private void buildRoom1() {
        Stage room1 = new Stage("room1");
        Item cursedObject = new CursedObject();
        cursedObject.registerActionAndHelp(Action.PICK, "pick CursedObject");
        Door cursedDoor = this.buildCursedDoor1();
        room1.addItems(cursedObject, cursedDoor);
        this.rooms.put(room1.getName(), room1);
    }

    private void buildRoom2() {
        Stage room2 = new Stage("room2");
        Thief thief = new Thief();
        thief.registerActionAndHelp(Action.TALK, "“Hello”, “Bye”");
        Door cursedDoor = this.buildCursedDoor2();
        room2.addItems(thief, cursedDoor);
        this.rooms.put(room2.getName(), room2);
    }

    private Door buildCursedDoor1() {
        CursedDoor cursedDoor = new CursedDoor("door1");
        cursedDoor.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        cursedDoor.setNextStageName("room2");
        cursedDoor.registerActionAndHelp(Action.OPEN, "open door1");
        return cursedDoor;
    }

    private Door buildCursedDoor2() {
        CursedDoor cursedDoor = new CursedDoor("door2");
        cursedDoor.setOpenCondition((someCharacter) -> !someCharacter.hasItem("CursedObject"));
        cursedDoor.setNextStageName("room3");
        cursedDoor.registerActionAndHelp(Action.OPEN, "open door2");
        return cursedDoor;
    }
}

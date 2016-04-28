package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGame extends AbstractGame {

    private Character character;
    private Map<String, Stage> rooms;

    protected CursedObjectGame() {
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
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return this.character.getCurrentStage().equalsIgnoreCase("room3");
    }

    private String pickHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.character.pickFromStage(currentRoom, itemName);
    }

    private String openHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.character.openFromStage(currentRoom, itemName);
    }

    private String talkHandler(String itemName, String message) {
        Stage currentRoom = this.getCurrentRoom();
        return this.character.talkTo(currentRoom, itemName, message);
    }

    private String lookAroundHandler() {
        Stage currentRoom = this.getCurrentRoom();
        return currentRoom.lookAround();
    }

//    private String examineHandler(String itemName) {
//        Stage currentRoom = this.getCurrentRoom();
//    }

    private Stage getCurrentRoom() {
        String currentRoomName = this.character.getCurrentStage();
        return this.rooms.get(currentRoomName);
    }

    private void registerPickHandler() {
        this.knownActions.put(Action.PICK, (itemName, args) -> this.pickHandler(itemName));
    }

    private void buildCharacter() {
        this.character = new Character();
        this.character.setCurrentStage("room1");
    }

    private void buildRoom1() {
        Stage room1 = new Stage("room1");
        Item cursedObject = new CursedObject();
        CursedDoor cursedDoor = new CursedDoor("door1");
        cursedDoor.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        cursedDoor.setNextStageName("room2");
        room1.addItems(cursedObject, cursedDoor);
        this.rooms.put(room1.getName(), room1);
    }

    private void buildRoom2() {
        Stage room2 = new Stage("room2");
        Thief thief = new Thief();
        CursedDoor cursedDoor = new CursedDoor("door2");
        cursedDoor.setOpenCondition((someCharacter) -> !someCharacter.hasItem("CursedObject"));
        cursedDoor.setNextStageName("room3");
        room2.addItems(thief, cursedDoor);
        this.rooms.put(room2.getName(), room2);
    }
}

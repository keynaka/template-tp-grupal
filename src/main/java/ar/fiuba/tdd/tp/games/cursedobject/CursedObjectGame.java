package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.AbstractGame;
import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Stage;
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
        super("Cursed Object", "You won!");
        this.rooms = new HashMap<>();
    }

    @Override
    protected void doStart() {
        this.buildCharacter();
        this.buildRoom1();
    }

    @Override
    protected void registerKnownActions() {
        this.registerPickHandler();
        this.knownActions.put(Action.OPEN, (itemName, args) -> this.openHandler(itemName));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private String pickHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.character.pickFromStage(currentRoom, itemName);
    }

    private String openHandler(String itemName) {
        Stage currentRoom = this.getCurrentRoom();
        return this.character.openFromStage(currentRoom, itemName);
    }

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
        Door cursedDoor = new CursedDoor("door1");
        room1.addItems(cursedObject, cursedDoor);
        this.rooms.put(room1.getName(), room1);
    }
}

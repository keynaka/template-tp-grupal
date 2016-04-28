package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Openable;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.items.Door;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedDoor extends Door implements Openable {

    public CursedDoor(String name) {
        super(name, State.CLOSED);
    }

    @Override
    public String open(Character character) {
        if (character.getInventory().contains("CursedObject")) {
            character.setCurrentStage("room2");
            String result = this.open();
            return this.isClosed() ? result : result.concat(" ").concat("You have entered the room2.");
        }
        return "You can't open this door. You need the CursedObject.";
    }
}

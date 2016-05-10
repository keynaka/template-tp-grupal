package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.*;


/**
 * Created by Fede on 07/05/2016.
 */
public class OpenDoorNew extends AbstractOpenDoorNew {

    public OpenDoorNew() {
        super("OpenDoor", "You enter room 2. You won the game!");
    }

    @Override
    protected void registerKnownActions() {
        super.registerKnownActions();
        this.addKnownActions();
    }

    @Override
    protected void buildRoom() {
        super.buildRoom();
        this.room.addItem(this.key);
    }

    private void addKnownActions() {
        this.knownActions.put(Action.OPEN, (itemName, args) -> openAction());
    }


}

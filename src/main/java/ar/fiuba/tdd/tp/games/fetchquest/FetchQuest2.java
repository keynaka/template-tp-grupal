package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;

import java.util.Optional;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuest2 extends AbstractGame {

    private Stage room;
    private Character character;
    private Item stick;

    public FetchQuest2() {
        super("Fetch Quest", "You won the game!");
    }

    @Override
    protected void doStart() {
        this.character = new Character();
        this.stick = new Item("stick", "It's just a simple stick.");
        this.room = new Stage();
        this.room.addItem(this.stick);
    }

    @Override
    protected void registerKnownActions() {
        this.knownActions.put(Action.LOOK_AROUND, (itemName) -> this.room.lookAround());
        this.knownActions.put(Action.PICK, (itemName) -> this.pickAction());
    }

    private String pickAction() {
        Item stick = this.room.pickItem("stick");
        this.character.getInventory().addItem(stick);
        return "There you go.";
    }

    @Override
    public boolean isFinished() {
        return this.character.getInventory().contains(this.stick);
    }

    @Override
    public String getDescription() {
        return null;
    }
}

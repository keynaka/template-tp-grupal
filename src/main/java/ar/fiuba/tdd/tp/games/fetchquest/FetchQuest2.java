package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuest2 extends AbstractGame {

    private Stage room;
    private Character character;
    private Item stick;

    public FetchQuest2() {
        super("Fetch Quest");
    }

    @Override
    protected void doStart() {
        this.character = new Character();
        this.stick = new Item("stick", "It's just a simple stick.");
        this.room = new Stage();
        this.room.addItem(this.stick);
    }

    @Override
    public String play(Command command) {
        String result = null;
        switch (command.getAction()) {
            case LOOK_AROUND:
                result = this.room.lookAround();
                break;
            case PICK:
                Item stick = this.room.pickItem("stick");
                this.character.getInventory().addItem(stick);
                break;
            default:
                result = "Unknown command.";
        }
        if (this.isFinished()) {
            result = "You won the game!";
        }


        return result;
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

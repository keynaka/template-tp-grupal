package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.Item;
import ar.fiuba.tdd.tp.games.Stage;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuest2 implements Game {

    private Stage room;
    private Character character;
    private Item stick;

    @Override
    public String start() {
        this.character = new Character();
        this.stick = new Item("stick", "It's just a simple stick.");
        this.room = new Stage();
        this.room.addItem(this.stick);
        return "Welcome to FetchQuest";
    }

    @Override
    public String play(String command) {
        String result = null;
        if("look around".equalsIgnoreCase(command)) {
            result =  this.room.lookArond();
        } else if ("pick stick".equalsIgnoreCase(command)) {
            Item stick = this.room.pickItem("stick");
            this.character.getInventory().addItem(stick);
        }

        if(this.isFinished()) {
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

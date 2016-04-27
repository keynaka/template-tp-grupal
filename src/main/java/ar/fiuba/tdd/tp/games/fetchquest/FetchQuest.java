package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Character;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuest extends AbstractGameWithStage {

    private Character character;
    private Item stick;

    public FetchQuest() {
        super("Fetch Quest", "You won the game!");
    }

    @Override
    protected void doStart() {
        super.doStart();
        this.character = new Character();
        this.stick = new Item("stick", "It's just a simple stick.");
        this.stage.addItem(this.stick);
    }

    @Override
    protected void registerKnownActions() {
        super.registerKnownActions();
        this.knownActions.put(Action.PICK, (itemName, args) -> this.pickAction());
    }

    private String pickAction() {
        Item stick = this.stage.pickItem("stick");
        this.character.getInventory().addItem(stick);
        return "There you go.";
    }

    @Override
    public boolean isFinished() {
        return this.character.getInventory().contains(this.stick.getName());
    }

    @Override
    public String getDescription() {
        return null;
    }
}

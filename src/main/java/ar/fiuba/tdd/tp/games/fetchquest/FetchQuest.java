package ar.fiuba.tdd.tp.games.fetchquest;

import ar.fiuba.tdd.tp.games.*;
import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuest extends AbstractGameWithStage {

    private Player player;
    private Item stick;

    public FetchQuest() {
        super("Fetch Quest", "You won the game!");
    }

    @Override
    protected void doStart() {
        super.doStart();
        this.player = new Player();
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
        this.player.getInventory().addItem(stick);
        return "There you go.";
    }

    @Override
    public boolean isFinished() {
        return this.player.getInventory().contains(this.stick.getName());
    }

    @Override
    public String getDescription() {
        return null;
    }
}

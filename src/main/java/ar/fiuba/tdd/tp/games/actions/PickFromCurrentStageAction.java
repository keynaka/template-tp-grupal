package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.items.Item;

/**
 * Created by swandelow on 5/30/16.
 */
public class PickFromCurrentStageAction implements Action {

    private ConcreteGame game;
    private String newOwnerName;
    private String itemName;

    public PickFromCurrentStageAction(ConcreteGame game, String newOwnerName, String itemName) {
        this.game = game;
        this.newOwnerName = newOwnerName;
        this.itemName = itemName;
    }

    @Override
    public void doAction() {
        new SwitchItemOwnerAction(this.game.getCurrentStage(), this.game.getItemKeeper(this.newOwnerName), this.itemName).doAction();
    }
}

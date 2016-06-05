package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by sebass on 04/06/16.
 */
public class DropInCurrentStageAction implements Action {

    private ConcreteGame game;
    private String itemName;

    public DropInCurrentStageAction(ConcreteGame game, String itemName) {
        this.game = game;
        this.itemName = itemName;
    }

    @Override
    public void doAction() {
        new SwitchItemOwnerAction(this.game.getPlayer(), this.game.getCurrentStage(), this.itemName).doAction();
    }
}

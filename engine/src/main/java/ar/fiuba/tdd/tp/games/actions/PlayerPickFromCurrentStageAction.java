package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by Patri on 09/06/2016.
 */
public class PlayerPickFromCurrentStageAction implements Action {

    private ConcreteGame game;
    private String newOwnerName;
    private String itemName;

    public PlayerPickFromCurrentStageAction(ConcreteGame game, String itemName) {
        this.game = game;
        this.newOwnerName = game.getPlayer().getName();
        this.itemName = itemName;
    }

    @SuppressWarnings("CPD-END")
    @Override
    public void doAction() {
        new SwitchItemOwnerAction(this.game.getCurrentStage(),
                this.game.getGameObjectRepository().getItemKeeper(this.newOwnerName),
                this.itemName).doAction();
    }

}

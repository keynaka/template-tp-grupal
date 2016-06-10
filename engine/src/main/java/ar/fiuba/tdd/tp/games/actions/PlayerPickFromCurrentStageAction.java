package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Stage;

/**
 * Created by Patri on 09/06/2016.
 */
public class PlayerPickFromCurrentStageAction implements Action {

    private ConcreteGame game;
    private String itemName;

    public PlayerPickFromCurrentStageAction(ConcreteGame game, String itemName) {
        this.game = game;
        this.itemName = itemName;
    }

    @SuppressWarnings("CPD-END")
    @Override
    public void doAction() {
        String currentStageName = this.game.getPlayer().getCurrentStage();
        Stage currentStage = this.game.getStage(currentStageName);
        new SwitchItemOwnerAction(currentStage,
                this.game.getPlayer(),
                this.itemName).doAction();
    }

}

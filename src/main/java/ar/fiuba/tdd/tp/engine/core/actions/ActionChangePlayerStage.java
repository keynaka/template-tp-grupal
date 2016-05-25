package ar.fiuba.tdd.tp.engine.core.actions;

import ar.fiuba.tdd.tp.engine.models.Stage;
import ar.fiuba.tdd.tp.engine.models.item.Player;

/**
 * Created by Nico on 25/05/2016.
 */
public class ActionChangePlayerStage extends Action {
    private Player player;
    private Stage stage;

    public ActionChangePlayerStage(Player player, Stage stage) {
        this.player = player;
        this.stage = stage;
    }

    public void doAction() {
        try {
            player.setCurrentStage(stage);
        } catch (Exception e) {
            System.out.print("Error: " + e + "\n");
        }
    }
}

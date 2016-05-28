package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.Stage;

/**
 * Created by Nico on 28/05/2016.
 */
public class ChangePlayerStageAction implements Action {
    private Player player;
    private Stage stage;

    public ChangePlayerStageAction(Player player, Stage stage) {
        this.player = player;
        this.stage = stage;
    }

    @Override
    public void doAction() {
        player.setCurrentStage(stage.getName());
    }
}

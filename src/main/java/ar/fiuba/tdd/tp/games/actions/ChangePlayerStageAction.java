package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.Stage;

/**
 * Created by Nico on 28/05/2016.
 */
public class ChangePlayerStageAction implements Action {
    private ConcreteGame game;
    private Stage stage;

    public ChangePlayerStageAction(ConcreteGame game, Stage stage) {
        this.game = game;
        this.stage = stage;
    }

    @Override
    public void doAction() {
        this.game.getPlayer().setCurrentStage(stage.getName());
    }
}

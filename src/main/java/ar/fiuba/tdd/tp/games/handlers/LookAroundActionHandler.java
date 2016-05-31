package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.red.server.Command;

/**
 * Created by swandelow on 5/31/16.
 */
public class LookAroundActionHandler implements ActionHandler{

    private ConcreteGame game;

    public LookAroundActionHandler(ConcreteGame game) {
        this.game = game;
    }

    @Override
    public String execute(Command command) {
        return this.game.getCurrentStage().lookAround();
    }
}

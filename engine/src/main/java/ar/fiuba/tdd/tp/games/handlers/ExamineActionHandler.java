package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.Command;

/**
 * Created by Eze on 08/06/2016.
 */
public class ExamineActionHandler implements ActionHandler {
    private ConcreteGame game;

    public ExamineActionHandler(ConcreteGame game) {
        this.game = game;
    }

    @Override
    public String execute(Command command) {
        game.getCurrentStage();
        return "You can open/close the box.";
    }
}

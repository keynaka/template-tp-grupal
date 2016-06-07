package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.games.Command;
import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.games.objects.GameObject;

/**
 * Created by swandelow on 5/31/16.
 */
public class DefaultActionHandler implements ActionHandler {

    private ConcreteGame game;

    public DefaultActionHandler(ConcreteGame game) {
        this.game = game;
    }

    @Override
    public String execute(Command command) {
        try {
            GameObject gameObject = this.game.getGameObjectRepository().getGameObject(command.getItemName());
            return gameObject.executeAction(command.getAction().getActionName());
        } catch (GameException e) {
            return e.getMessage();
        }
    }
}

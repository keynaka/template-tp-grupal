package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.objects.GameObject;
import ar.fiuba.tdd.tp.red.server.Command;

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
        GameObject gameObject = this.game.getGameObjectRepository().getGameObject(command.getItemName());
        return gameObject.executeAction(command.getAction().getActionName());
    }
}

package ar.fiuba.tdd.tp.games.handlers;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.objects.GameObject;
import ar.fiuba.tdd.tp.red.server.Command;

/**
 * Created by swandelow on 6/1/16.
 */
@SuppressWarnings("CPD-START")
public class ParametizedActionHandler implements ActionHandler {

    private ConcreteGame game;

    public ParametizedActionHandler(ConcreteGame game) {
        this.game = game;
    }

    @SuppressWarnings("CPD-END")
    @Override
    public String execute(Command command) {
        GameObject gameObject = this.game.getGameObjectRepository().getGameObject(command.getItemName());
        return gameObject.executeActionWith(command.getAction().getActionName(), command.getArgument());
    }
}

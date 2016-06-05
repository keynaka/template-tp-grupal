package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.driver.GameState;
import ar.fiuba.tdd.tp.games.handlers.ActionHandler;
import ar.fiuba.tdd.tp.red.server.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by swandelow on 4/23/16.
 */
public abstract class AbstractGame implements Game {

    private static final String WELCOME_MESSAGE = "Welcome to %s!";
    private String name;
    private String endGameMessage;
    protected Map<ActionOld, ActionHandler> knownActions = new HashMap<>();

    protected AbstractGame(String gameName, String endGameMessage) {
        this.name = gameName;
        this.endGameMessage = endGameMessage;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String start() {
        this.registerKnownActions();
        this.doStart();
        return String.format(WELCOME_MESSAGE, this.name);
    }

    @Override
    public String play(Command command) {
        String result = null;
        Optional<ActionHandler> actionMethod = Optional.ofNullable(this.knownActions.get(command.getAction()));
        result = actionMethod.isPresent() ? actionMethod.get().execute(command) : "Unknown command.";

        this.updateGameState();

        if (this.isFinished()) {
            result = this.getEndGameMessage();
        }
        return result;
    }

    @Override
    public GameState getGameState() {
        // FIXME: arreglar esto!!
        return GameState.InProgress;
    }

    protected void updateGameState() {
        // FIXME: se deja vacio para no romper otros juegos.
    }

    protected String getEndGameMessage() {
        return this.endGameMessage;
    }

    protected abstract void doStart();

    protected abstract void registerKnownActions();

    public Map<ActionOld,ActionHandler> getKnownActions() {
        return knownActions;
    }
}

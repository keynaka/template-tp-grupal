package ar.fiuba.tdd.tp.games;

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
    protected Map<Action, ActionFunction> knownActions = new HashMap<>();

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
        Optional<ActionFunction> actionMethod = Optional.ofNullable(this.knownActions.get(command.getAction()));
        result = actionMethod.isPresent() ? actionMethod.get().execute(command.getItemName(),command.getArgument()) : "Unknown command.";

        if (this.isFinished()) {
            result = this.getEndGameMessage();
        }
        return result;
    }

    protected String getEndGameMessage() {
        return this.endGameMessage;
    }

    protected abstract void doStart();

    protected abstract void registerKnownActions();

    public Map<Action,ActionFunction> getKnownActions() {
        return knownActions;
    }
}

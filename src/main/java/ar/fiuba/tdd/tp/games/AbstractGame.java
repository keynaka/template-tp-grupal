package ar.fiuba.tdd.tp.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

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
        this.buildKnownActions();
        this.doStart();
        return String.format(WELCOME_MESSAGE, this.name);
    }

    @Override
    public String play(Command command) {
        String result = null;
        Optional<ActionFunction> actionMethod = Optional.ofNullable(this.knownActions.get(command.getAction()));
        result = actionMethod.isPresent() ? actionMethod.get().execute(command.getItemName()) : "Unknown command.";

        if (this.isFinished()) {
            result = this.getEndGameMessage();
        }
        return result;
    }

    protected String getEndGameMessage() {
        return this.endGameMessage;
    }

    protected abstract void doStart();

    protected abstract void buildKnownActions();

}

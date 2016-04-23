package ar.fiuba.tdd.tp.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by swandelow on 4/23/16.
 */
public abstract class AbstractGame implements Game {

    private static final String WELCOME_MESSAGE = "Welcome to %s!";
    private String name;
    protected Map<Action, CommandFunction> knownActions = new HashMap<>();

    protected AbstractGame(String gameName) {
        this.name = gameName;
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
        Optional<CommandFunction> method = Optional.ofNullable(this.knownActions.get(command.getAction()));
        result = method.isPresent() ? method.get().execute() : "Unknown command.";

        if (this.isFinished()) {
            result = "You won the game!";
        }
        return result;
    }

    protected abstract void doStart();

    protected abstract void buildKnownActions();

}

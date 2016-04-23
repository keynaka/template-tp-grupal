package ar.fiuba.tdd.tp.games;

/**
 * Created by swandelow on 4/23/16.
 */
public abstract class AbstractGame implements Game {

    private static final String WELCOME_MESSAGE = "Welcome to %s!";
    private String name;

    protected AbstractGame(String gameName) {
        this.name = gameName;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String start() {
        this.doStart();
        return String.format(WELCOME_MESSAGE, this.name);
    }

    protected abstract void doStart();
}

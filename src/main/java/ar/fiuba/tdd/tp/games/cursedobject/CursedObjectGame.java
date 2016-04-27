package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.AbstractGame;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGame extends AbstractGame {

    protected CursedObjectGame() {
        super("Cursed Object", "You won!");
    }

    @Override
    protected void doStart() {

    }

    @Override
    protected void registerKnownActions() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String getDescription() {
        return null;
    }
}

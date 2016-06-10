package ar.fiuba.tdd.tp.games.timer;

import ar.fiuba.tdd.tp.games.ConcreteGame;

/**
 * Created by swandelow on 6/8/16.
 */

// TODO borrar esta clase!!
public class GameTimerTask extends AbstractGameTimerTask {

    public GameTimerTask(ConcreteGame game, Long delay, Long period) {
        super(game, delay, period);
    }

    @Override
    protected String doRun() {
        return "Un mensaje de prueba.";
    }

}

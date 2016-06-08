package ar.fiuba.tdd.tp.games;

import java.util.TimerTask;

/**
 * Created by swandelow on 6/8/16.
 */
public class GameTimerTask extends AbstractGameTimerTask {

    public GameTimerTask(ConcreteGame game, Long delay, Long period) {
        super(game, delay, period);
    }

    @Override
    protected String doRun() {
        return "sarapatin tin tin tin tin.";
    }

}

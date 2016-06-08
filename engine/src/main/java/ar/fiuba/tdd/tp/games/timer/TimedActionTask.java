package ar.fiuba.tdd.tp.games.timer;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.actions.Action;

/**
 * Created by swandelow on 6/8/16.
 */
public class TimedActionTask extends AbstractGameTimerTask {

    private Action action;
    private String resultMessage;

    public TimedActionTask(ConcreteGame game, Long delay, Action action, String resultMessage) {
        super(game, delay);
        this.action = action;
        this.resultMessage = resultMessage;
    }

    public TimedActionTask(ConcreteGame game, Long delay, Long period, Action action, String resultMessage) {
        super(game, delay, period);
        this.action = action;
        this.resultMessage = resultMessage;
    }

    @Override
    protected String doRun() {
        System.out.println("Running scheduled action.");
        this.action.doAction();
        return this.resultMessage;
    }
}

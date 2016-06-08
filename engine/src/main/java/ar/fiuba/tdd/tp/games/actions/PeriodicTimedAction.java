package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.timer.TimedActionTask;

/**
 * Created by swandelow on 6/8/16.
 */
public class PeriodicTimedAction implements Action {
    private ConcreteGame game;
    private Long delay;
    private Long period;
    private Action scheduledAction;
    private String resultMessage;

    public PeriodicTimedAction(ConcreteGame game, Long delay, Long period, Action scheduledAction, String resultMessage) {
        this.game = game;
        this.delay = delay;
        this.period = period;
        this.scheduledAction = scheduledAction;
        this.resultMessage = resultMessage;
    }

    @Override
    public void doAction() {
        TimedActionTask task = new TimedActionTask(game, delay, period, scheduledAction, resultMessage);
        this.game.getTimer().schedulePeriodicGameTask(task);
    }
}

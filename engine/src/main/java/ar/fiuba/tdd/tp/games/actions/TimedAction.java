package ar.fiuba.tdd.tp.games.actions;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import ar.fiuba.tdd.tp.games.timer.TimedActionTask;

/**
 * Created by swandelow on 6/8/16.
 */
public class TimedAction implements Action {

    private ConcreteGame game;
    private Long delay;
    private Action scheduledAction;
    private String resultMessage;

    public TimedAction(ConcreteGame game, Long delay, Action scheduledAction, String resultMessage) {
        this.game = game;
        this.delay = delay;
        this.scheduledAction = scheduledAction;
        this.resultMessage = resultMessage;
    }

    @Override
    public void doAction() {
        TimedActionTask task = new TimedActionTask(game, delay, scheduledAction, resultMessage);
        this.game.getTimer().scheduleGameTask(task);
    }
}

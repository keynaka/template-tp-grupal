package ar.fiuba.tdd.tp.games.timer;

import ar.fiuba.tdd.tp.games.ConcreteGame;

import java.util.TimerTask;

/**
 * Created by swandelow on 6/8/16.
 */
public abstract class AbstractGameTimerTask extends TimerTask {

    private ConcreteGame game;
    private Long delay;
    private Long period;

    public AbstractGameTimerTask(ConcreteGame game, Long delay) {
        this.game = game;
        this.delay = delay;
    }

    public AbstractGameTimerTask(ConcreteGame game, Long delay, Long period) {
        this.game = game;
        this.delay = delay;
        this.period = period;
    }

    @Override
    public void run() {
        // aca se modifican el estado de los objetos
        String message = this.doRun();
        // luego setear el mensaje del evento
        this.game.setEventMessage(message);
        // notificar a todos los observadores del juego (server)
        this.game.notifyObservers();
    }

    protected abstract String doRun();

    public Long getDelay() {
        return delay;
    }

    public Long getPeriod() {
        return period;
    }
}

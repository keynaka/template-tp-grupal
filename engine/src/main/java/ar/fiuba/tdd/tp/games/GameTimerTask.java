package ar.fiuba.tdd.tp.games;

import java.util.TimerTask;

/**
 * Created by swandelow on 6/8/16.
 */
public class GameTimerTask extends TimerTask {

    private ConcreteGame game;
    private Long delay;
    private Long period;

    public GameTimerTask(ConcreteGame game, Long delay, Long period) {
        this.game = game;
        this.delay = delay;
        this.period = period;
    }

    @Override
    public void run() {
        // aca se modifican el estado de los objetos

        // luego setear el mensaje del evento
        this.game.setEventMessage("sarapatin tin tin tin tin.");
        // notificar a todos los observadores del juego (server)
        this.game.notifyObservers();
    }

    public Long getDelay() {
        return delay;
    }

    public Long getPeriod() {
        return period;
    }
}

package ar.fiuba.tdd.tp.games.timer;

/**
 * Created by swandelow on 6/9/16.
 */
public interface GameTimerInterface {

    void startTimer();

    void schedulePeriodicGameTask(AbstractGameTimerTask task);

    void scheduleGameTask(AbstractGameTimerTask task);

    void stopTimer();
}

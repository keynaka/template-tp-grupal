package ar.fiuba.tdd.tp.games.timer;

import java.util.Timer;

public class GameTimer implements GameTimerInterface {
    private Timer timer;

    public void startTimer() {
        timer = new Timer();
        //timer.schedule(new ChangeState(), this.seconds * 1000, this.seconds * 1000);
    }

    public void schedulePeriodicGameTask(AbstractGameTimerTask task) {
        this.timer.schedule(task, task.getDelay(), task.getPeriod());
    }

    public void scheduleGameTask(AbstractGameTimerTask task) {
        this.timer.schedule(task, task.getDelay());
    }

    public void stopTimer() {
        timer.cancel();
    }

}
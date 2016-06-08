package ar.fiuba.tdd.tp.games;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    Timer timer;
    int seconds;

    public GameTimer() {

    }
    
    public GameTimer(int seconds) {
        this.seconds = seconds;
    }

    public void startTimer() {
        timer = new Timer();
        //timer.schedule(new ChangeState(), this.seconds * 1000, this.seconds * 1000);
    }

    public void scheduleGameTimerTask(GameTimerTask task) {
        this.timer.schedule(task, task.getDelay(), task.getPeriod());
    }

    public void stopTimer() {
        timer.cancel();
    }

    static class ChangeState extends TimerTask {
        public void run() {
            System.out.println("OK, It's time to do something!");
        }
    }

    public static void main(String args[]) {
        System.out.println("Schedule something to do in 2 seconds.");
        GameTimer gt = new GameTimer(2);
        gt.startTimer();
        System.out.println("Waiting.");
    }
}
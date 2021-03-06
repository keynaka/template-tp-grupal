package ar.fiuba.tdd.tp.games.timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by swandelow on 6/9/16.
 */
public class GameTimerMock implements GameTimerInterface {


    private List<AbstractGameTimerTask> peridiocTasks = new ArrayList<>();
    private List<AbstractGameTimerTask> tasks = new ArrayList<>();
    private Long timeCount = 0L;

    public void startTimer() {
        this.timeCount = 0L;
    }

    public void schedulePeriodicGameTask(AbstractGameTimerTask task) {
        this.peridiocTasks.add(task);
        //this.checkExecuteTask();
    }

    public void scheduleGameTask(AbstractGameTimerTask task) {
        this.tasks.add(task);
        //this.checkExecuteTask();
    }

    public void stopTimer() {
    }

    public void forceTime(Long time) {
        this.timeCount = this.timeCount + time;
        this.checkExecuteTask();
    }

    public void forceTimeInMinutes(Long timeMinutes) {
        this.timeCount = this.timeCount + (timeMinutes * 1000 * 60);
        this.checkExecuteTask();
    }

    public void checkExecuteTask() {
        this.checkTasks();

        for (AbstractGameTimerTask task : this.peridiocTasks) {
            if (task.getDelay() <= this.timeCount) {
                task.doRun();
            }
        }
    }

    private void checkTasks() {
        Iterator<AbstractGameTimerTask> iterator = this.tasks.iterator();
        while (iterator.hasNext()) {
            AbstractGameTimerTask task = iterator.next();
            if (task.getDelay() <= this.timeCount) {
                task.doRun();
                iterator.remove();
            }
        }
    }

    public List<AbstractGameTimerTask> getTasks() {
        return tasks;
    }

    public List<AbstractGameTimerTask> getPeridiocTasks() {
        return peridiocTasks;
    }
}



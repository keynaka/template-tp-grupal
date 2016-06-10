package ar.fiuba.tdd.tp.games.timer;

import ar.fiuba.tdd.tp.games.ConcreteGame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 6/9/16.
 */
public class GameTimerMockTest {

    GameTimerMock target;

    @Before
    public void setUp() {
        this.target = new GameTimerMock();
    }

    @Test
    public void testScheduleTask() {
        GameTimerTask task1 = new GameTimerTask(new ConcreteGame(), 100L, 0L);
        GameTimerTask task2 = new GameTimerTask(new ConcreteGame(), 200L, 0L);
        GameTimerTask task3 = new GameTimerTask(new ConcreteGame(), 300L, 0L);
        target.scheduleGameTask(task1);
        target.scheduleGameTask(task2);
        target.scheduleGameTask(task3);

        target.forceTime(150L);
        assertEquals(2, target.getTasks().size());
        target.forceTime(100L);
        assertEquals(1, target.getTasks().size());
    }

    @Test
    public void testSchedulePeriodicTask() {
        GameTimerTask task1 = new GameTimerTask(new ConcreteGame(), 100L, 0L);
        target.schedulePeriodicGameTask(task1);

        target.forceTime(150L);
        assertEquals(1, target.getPeridiocTasks().size());
        target.forceTime(100L);
        assertEquals(1, target.getPeridiocTasks().size());
    }
}

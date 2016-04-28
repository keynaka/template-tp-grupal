package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGameTest {

    private CursedObjectGame target = new CursedObjectGame();

    @Test
    public void testStart() {
        assertEquals("Welcome to Cursed Object!", this.target.start());
    }

    @Test
    public void testHappyPathRoom1() {
        assertEquals("Welcome to Cursed Object!", this.target.start());

        String response = this.target.play(new Command(Action.PICK, "CursedObject"));
        assertEquals("CursedObject saved in inventory. Now you are cursed!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "door1"));
        assertEquals("Open door. You have entered the room2.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testHappyPathRoom2() {
        this.testHappyPathRoom1();

        String response = this.target.play(new Command(Action.TALK, "thief", "Hello"));
        assertEquals("Hi!. The thief has just stolen your object!", response);
        assertFalse(this.target.isFinished());
    }
}

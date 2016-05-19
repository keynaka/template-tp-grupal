package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.games.exceptions.GameException;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGameTest {

    private Game target = new CursedObjectGameBuilder().build();

    @Ignore
    @Test
    public void testStart() {
        assertEquals("Welcome to Cursed Object!", this.target.start());
    }

    @Test
    public void testHappyPathRoom1() {
        //assertEquals("Welcome to Cursed Object!", this.target.start());

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

        response = this.target.play(new Command(Action.OPEN, "door2"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testLookAroundRoom1() {
        //assertEquals("Welcome to Cursed Object!", this.target.start());

/*        String response = this.target.play(new Command(Action.LOOK_AROUND));
        assertEquals("Items in the room1: CursedObject, door1.", response);*/
    }

    @Test
    public void testLookAroundRoom2() {
        this.testHappyPathRoom1();

/*        String response = this.target.play(new Command(Action.LOOK_AROUND));
        assertEquals("Items in the room2: door2, thief.", response);*/
    }

    @Test
    public void testExamineInRoom1() {
        //assertEquals("Welcome to Cursed Object!", this.target.start());

        String response = this.target.play(new Command(Action.EXAMINE, "door1"));
        assertEquals("You can open: open door1.", response);

        response = this.target.play(new Command(Action.EXAMINE, "CursedObject"));
        assertEquals("You can pick: pick CursedObject.", response);
    }

    @Test
    public void testUnsupportedAction() {
        //assertEquals("Welcome to Cursed Object!", this.target.start());

        try {
            this.target.play(new Command(Action.PICK, "door1"));
            fail("GameException expected.");
        } catch (GameException e) {
            assertEquals("Unsupported action.", e.getMessage());
        }
    }

    @Test
    public void testExamineInRoom2() {
        this.testHappyPathRoom1();

        String response = this.target.play(new Command(Action.EXAMINE, "door2"));
        assertEquals("You can open: open door2.", response);

        response = this.target.play(new Command(Action.EXAMINE, "thief"));
        assertEquals("You can talk: \"Hello\", \"Bye\".", response);
    }

}

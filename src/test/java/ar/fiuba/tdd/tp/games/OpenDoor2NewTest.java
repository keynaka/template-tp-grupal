package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.opendoor.OpenDoor2New;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor2NewTest {

    private OpenDoor2New target = new OpenDoor2New();

    @Test
    public void testHappyPath() {

        String response =  this.testStart();

        response = this.target.play(new Command(Action.OPEN, "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.PICK, "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    private String testStart() {
        String response = this.target.start();
        assertEquals("Welcome to OpenDoor2!", response);
        assertFalse(this.target.isFinished());
        return response;
    }

    @Test
    public void testLookAround() {

        String response =  this.testStart();

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testOpenBox() {
        String response =  this.testStart();

        response = this.target.play(new Command(Action.OPEN, "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.EXAMINE, "box"));
        assertEquals("You can open/close the box.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "box"));
        assertEquals("The box is opened!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door, key.", response);
        assertFalse(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(Action.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}

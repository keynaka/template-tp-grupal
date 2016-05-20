package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.Game;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoor2Test {

    private Game target = new OpenDoor2Builder().build();

    @Test
    public void testHappyPath() {

        String response =  this.target.start();

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


    @Test
    public void testLookAround() {

        String response =  this.target.start();

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "box"));

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: box, door, key.", response);
        assertFalse(this.target.isFinished());

    }

    @Test
    public void testOpenBox() {

        String response =  this.target.start();

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

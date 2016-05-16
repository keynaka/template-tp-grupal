package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.opendoor.OpenDoor;
import ar.fiuba.tdd.tp.games.opendoor.OpenDoorNew;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Fede on 07/05/16.
 */
public class OpenDoorNewTest {

    private OpenDoorNew target = new OpenDoorNew();

    @Test
    public void testHappyPath() {

        String response = this.target.start();
        assertEquals("Welcome to Open Door!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("Items in the room: door, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.PICK, "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.OPEN, "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(Action.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}

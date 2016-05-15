package ar.fiuba.tdd.tp.games.opendoor;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoorTest {

    private OpenDoor target = new OpenDoor();

    @Test
    public void testHappyPath() {

        String response = this.target.start();
        assertEquals("Welcome to OpenDoor!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: door, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Open"), "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Pick"), "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Open"), "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(new Action("Unknow Action"), ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}

package ar.fiuba.tdd.tp.games.opendoor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/22/16.
 */
public class OpenDoorTest {

    /*private OpenDoor target = new OpenDoor();

    @Test
    public void testHappyPath() {

        String response = this.target.start();
        assertEquals("Welcome to OpenDoor!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.LOOK_AROUND, ""));
        assertEquals("Items in the room: door, key.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.OPEN, "door"));
        assertEquals("Ey! Where do you go?! Room 2 is locked.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.PICK, "key"));
        assertEquals("There you go!", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.OPEN, "door"));
        assertEquals("You enter room 2. You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }*/
}

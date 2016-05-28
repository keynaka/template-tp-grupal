package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.fetchquest.FetchQuestBuilder;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuestTest {

    private Game target = new FetchQuestBuilder().build();

    @Test
    public void testHappyPath() {

        //assertEquals("Welcome to Fetch Quest!", this.target.start());

        String response = this.target.play(new Command(ActionOld.LOOK_AROUND));
        assertEquals("Items in the room: stick.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(ActionOld.PICK, "stick"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(ActionOld.UNKNOWN_ACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}

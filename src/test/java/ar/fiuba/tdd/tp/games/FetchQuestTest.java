package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest;
import ar.fiuba.tdd.tp.red.server.Command;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuestTest {

    private FetchQuest target = new FetchQuest();

    @Test
    public void testHappyPath() {

        assertEquals("Welcome to Fetch Quest!", this.target.start());

        String response = this.target.play(new Command(new Action("Look Around"), ""));
        assertEquals("Items in the room: stick.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(new Action("Pick"), "stick"));
        assertEquals("You won the game!", response);
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

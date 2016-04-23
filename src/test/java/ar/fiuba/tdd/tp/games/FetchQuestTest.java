package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.fetchquest.FetchQuest2;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/21/16.
 */
public class FetchQuestTest {

    private FetchQuest2 target = new FetchQuest2();

    @Test
    public void testHappyPath() {

        this.target.start();

        String response = this.target.play(new Command(Action.LOOK_AROUND, ""));
        assertEquals("There's a stick in the room.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play(new Command(Action.PICK, "stick"));
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }

    @Test
    public void testInvalidCommand() {

        this.target.start();

        String response = this.target.play(new Command(Action.UNKNOWNACTION, ""));
        assertEquals("Unknown command.", response);
        assertFalse(this.target.isFinished());

    }
}

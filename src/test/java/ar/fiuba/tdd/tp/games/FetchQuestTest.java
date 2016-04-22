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

        String response = this.target.play("look around");
        assertEquals("There's a stick in the room.", response);
        assertFalse(this.target.isFinished());

        response = this.target.play("pick stick");
        assertEquals("You won the game!", response);
        assertTrue(this.target.isFinished());
    }
}

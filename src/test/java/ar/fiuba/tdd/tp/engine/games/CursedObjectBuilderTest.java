package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nico on 25/05/2016.
 */
public class CursedObjectBuilderTest {
    private Game game = new CursedObjectBuilder().build();

    @Test
    public void testHappyPath() {
        assertFalse(game.isWon());

        String feedback;

        feedback = game.executeCommand("pick gato");
        assertFalse(game.isWon());

        feedback = game.executeCommand("open door");
        assertFalse(game.isWon());

        feedback = game.executeCommand("talk thieve");
        assertFalse(game.isWon());

        feedback = game.executeCommand("open door");
        assertTrue(game.isWon());
    }
}

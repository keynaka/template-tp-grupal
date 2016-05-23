package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nico on 23/05/2016.
 */
public class OpenDoorBuilderTest {
    private Game game = new OpenDoorBuilder().build();

    @Test
    public void testHappyPath() {
        game.executeCommand("pick key");
        game.executeCommand("open door");
        assertTrue(game.isWon());
    }
}

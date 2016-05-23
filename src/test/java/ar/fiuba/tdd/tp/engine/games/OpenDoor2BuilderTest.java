package ar.fiuba.tdd.tp.engine.games;

import ar.fiuba.tdd.tp.engine.core.Game;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nico on 23/05/2016.
 */
public class OpenDoor2BuilderTest {
    private Game game = new OpenDoor2Builder().build();

    @Test
    public void testHappyPath() {
        game.executeCommand("open box");
        game.executeCommand("pick key");
        game.executeCommand("open door");
        assertTrue(game.isWon());
    }

    @Test
    public void testUnhappyPath() {
        game.executeCommand("open door");
        game.executeCommand("pick key");
        game.executeCommand("open box");
        game.executeCommand("open door");
        game.executeCommand("pick key");
        game.executeCommand("open door");
        assertTrue(game.isWon());
    }
}

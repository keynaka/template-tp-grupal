package ar.fiuba.tdd.tp.engine.games;

import org.junit.Test;
import ar.fiuba.tdd.tp.engine.core.Game;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nico on 22/05/2016.
 */
public class FetchQuestBuilderTest {

    private Game game = new FetchQuestBuilder().build();

    @Test
    public void testHappyPath() {
        game.executeCommand("pick stick");
        assertTrue(game.isWon());
    }
}

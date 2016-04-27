package ar.fiuba.tdd.tp.games.cursedobject;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedObjectGameTest {

    private CursedObjectGame target = new CursedObjectGame();

    @Test
    public void testStart() {
        assertEquals("Welcome to Cursed Object!", this.target.start());
    }
}

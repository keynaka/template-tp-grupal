package ar.fiuba.tdd.tp.games.traits;

import ar.fiuba.tdd.tp.games.Action;
import ar.fiuba.tdd.tp.games.State;
import ar.fiuba.tdd.tp.games.items.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by swandelow on 4/24/16.
 */
public class TraitOpenableTest {

    private Item itemOpenable;

    @Before
    public void setUp() {
        TraitOpenable traitOpenable = new TraitOpenable(State.CLOSED);
        this.itemOpenable = new Item("itemOpenable", "It's an item that knows how to open/close itself.", traitOpenable);
    }

    @Test
    public void testProcessOpenAndCloseActions() {
        assertEquals("itemOpenable is already closed.", this.itemOpenable.process(Action.CLOSE));
        assertEquals("Open itemOpenable.", this.itemOpenable.process(Action.OPEN));
        assertEquals("itemOpenable is already open.", this.itemOpenable.process(Action.OPEN));
    }


}

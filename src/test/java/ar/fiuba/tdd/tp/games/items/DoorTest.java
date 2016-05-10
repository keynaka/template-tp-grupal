package ar.fiuba.tdd.tp.games.items;

import ar.fiuba.tdd.tp.games.Player;
import ar.fiuba.tdd.tp.games.State;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebass on 30/04/16.
 */
public class DoorTest {

    private Door target = new Door(State.CLOSED);
    private Player player = new Player();

    @Test
    public void testDefaultOpeningCondition() {
        assertEquals("Open door.", this.target.open(player));
        assertFalse(this.target.isClosed());
    }

    @Test
    public void testSetOpeningCondition() {
        this.target.setOpeningCondition((character) -> character.hasItem("key1"));
        assertEquals("You can't open this door.", this.target.open(this.player));
        assertTrue(this.target.isClosed());

        player.addToInventory(new Item("key1", "test key."));
        assertEquals("Open door.", this.target.open(player));
        assertFalse(this.target.isClosed());
    }
}

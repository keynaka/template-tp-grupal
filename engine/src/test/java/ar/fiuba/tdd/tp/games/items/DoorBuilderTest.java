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
public class DoorBuilderTest {

    private Player player = new Player();

    @Test
    public void testDefault() {
        Door door = new Door.DoorBuilder(State.CLOSED).build();
        assertEquals("door", door.getName());
        assertTrue(door.isClosed());

        assertEquals("Open door.", door.open(this.player));
        assertFalse(door.isClosed());
    }

    @Test
    public void testName() {
        Door door = new Door.DoorBuilder(State.CLOSED).name("doorTest").build();
        assertEquals("doorTest", door.getName());
    }

    @Test
    public void testNextStageName() {
        Door door = new Door.DoorBuilder(State.CLOSED).name("doorTest").nextStageName("room2").build();
        assertEquals("room2", door.getNextStageName());
    }

    @Test
    public void testOpeningCondition() {
        Door door = new Door.DoorBuilder(State.CLOSED).name("doorTest")
                .openingCondition((character) -> character.hasItem("disk1")).build();
        assertEquals("doorTest", door.getName());
        assertEquals("You can't open this door.", door.open(this.player));

        this.player.addToInventory(new Item("disk1", "test disk."));
        assertEquals("Open door.", door.open(this.player));
        assertFalse(door.isClosed());
    }

    @Test
    public void testOpeningErrorMessage() {
        Door door = new Door.DoorBuilder(State.CLOSED).name("doorTest")
                .openingCondition((character) -> character.hasItem("disk1"))
                .openingErrorMessage("You can't open this door. You need the disk1.").build();
        assertEquals("doorTest", door.getName());
        assertEquals("You can't open this door. You need the disk1.", door.open(this.player));

        this.player.addToInventory(new Item("disk1", "test disk."));
        assertEquals("Open door.", door.open(this.player));
        assertFalse(door.isClosed());
    }
}

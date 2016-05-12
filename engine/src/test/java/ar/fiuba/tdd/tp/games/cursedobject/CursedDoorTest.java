package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedDoorTest {

    private CursedDoor target;

    @Before
    public void setUp() {
        this.target = new CursedDoor("CursedDoor");
        this.target.setNextStageName("room2");
    }

    @Test
    public void testOpenWithoutCursedObject() {
        Player player = new Player();
        this.target.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        assertTrue(this.target.isClosed());
        assertEquals("You can't open this door. You need the CursedObject.", this.target.open(player));
        assertTrue(this.target.isClosed());
    }

    @Test
    public void testOpenWithCursedObject() {
        Player player = new Player();
        this.target.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        player.addToInventory(new CursedObject());
        assertTrue(this.target.isClosed());
        assertEquals("Open door. You have entered the room2.", this.target.open(player));
        assertFalse(this.target.isClosed());
    }
}

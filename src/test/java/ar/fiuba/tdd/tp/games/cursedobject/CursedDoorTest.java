package ar.fiuba.tdd.tp.games.cursedobject;

import ar.fiuba.tdd.tp.games.Character;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by swandelow on 4/27/16.
 */
public class CursedDoorTest {

    private CursedDoor target;
/*
    @Before
    public void setUp() {
        this.target = new CursedDoor("CursedDoor");
        this.target.setNextStageName("room2");
    }

    @Test
    public void testOpenWithoutCursedObject() {
        Character character = new Character();
        this.target.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        assertTrue(this.target.isClosed());
        assertEquals("You can't open this door. You need the CursedObject.", this.target.open(character));
        assertTrue(this.target.isClosed());
    }

    @Test
    public void testOpenWithCursedObject() {
        Character character = new Character();
        this.target.setOpenCondition((someCharacter) -> someCharacter.hasItem("CursedObject"));
        character.addToInventory(new CursedObject());
        assertTrue(this.target.isClosed());
        assertEquals("Open door. You have entered the room2.", this.target.open(character));
        assertFalse(this.target.isClosed());
    }
    */
}

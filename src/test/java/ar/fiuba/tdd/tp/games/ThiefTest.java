package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.games.items.Item;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by swandelow on 4/27/16.
 */
public class ThiefTest {

    private Thief target = new Thief();
/*
    @Test
    public void testSteal() {
        Character character = new Character();
        Item item = new Item("item", "An item.");
        character.addToInventory(item);
        assertEquals("The thief has just stolen your object!", this.target.steal(character));
        assertFalse(character.hasItem(item.getName()));
    }

    @Test
    public void testTalk() {
        Character character = new Character();
        assertEquals("Hi!. The thief has just stolen your object!", this.target.talk(character, "Hello"));
        assertEquals("Bye.", this.target.talk(character, "Bye"));
    }
    */
}
